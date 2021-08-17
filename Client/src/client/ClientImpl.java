package client;

import authorizer.ClientAuthorizer;
import command.*;
import connection.ClientConnectionManager;
import connection.request.RequestSender;
import connection.response.ResponseReader;
import exceptions.CommandIsNotExistException;
import general.*;
import subsidiary.TicketBuilder;
import subsidiary.TicketBuilderImpl;
import subsidiary.TicketValidatorImpl;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.net.ConnectException;
import java.nio.BufferOverflowException;
import java.nio.channels.SocketChannel;
import java.util.Locale;
import java.util.NoSuchElementException;

public class ClientImpl implements Client, IO {
    private final ClientCommandReaderImpl commandReader;
    private final ClientConnectionManager connectionManager;
    private final RequestSender requestSender;
    private final ResponseReader responseReader;
    private final ClientAuthorizer clientAuthorizer;
    private final int port;
    private TicketBuilder ticketBuilder;
    private static User currentUser;
    private boolean isRunning;

    public ClientImpl(ClientCommandReaderImpl commandReader,
                      ClientConnectionManager connectionManager,
                      RequestSender requestSender,
                      ResponseReader responseReader,
                      ClientAuthorizer clientAuthorizer,
                      int port) {
        this.commandReader = commandReader;
        this.connectionManager = connectionManager;
        this.requestSender = requestSender;
        this.responseReader = responseReader;
        this.clientAuthorizer = clientAuthorizer;
        this.port = port;
        ticketBuilder = new TicketBuilderImpl(false, new TicketValidatorImpl(), getReader());
        addCommands();
        isRunning = true;
    }

    /**
     * Метод старта работы приложения
     *
     * @param port
     */

    @Override
    public void start(int port) {
        try {
            println(">The work is started!");
            commandReader.executeCommand("client_help", null);
        } catch (CommandIsNotExistException e) {
            e.printStackTrace();
        }
        while (isRunning) {
            String userInput = "";
            try {
                userInput = readLine().trim();
                commandReader.executeCommand(userInput, null);
            } catch (NoSuchElementException | NullPointerException e) {
                errPrint("You can't input this\nThe work will be stopped!");
            } catch (CommandIsNotExistException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Response response = communicateWithServer(userInput);
                if (response == null) {
                    println("Ответ не получен");
                    return;
                }
                println(response.getMessage());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Отправляет запрос серверу
     *
     * @param userString
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Response communicateWithServer(String userString) throws IOException, ClassNotFoundException {
        String[] fullCommand = userString.trim().split("\\s", 2);
        SocketChannel socketChannel;
        try {
            socketChannel = connectionManager.openConnection(port);
        } catch (ConnectException e) {
            println("Сервер недоступен");
            exit();
            return null;
        }
        Request request = requestSender.createBasicRequest(fullCommand[0]);
        if (fullCommand.length > 1) {
            request.setArg(fullCommand[1]);
        }
        request.setUser(currentUser);
        requestSender.sendRequest(socketChannel, request);
        Response response = null;
        try {
            response = responseReader.readResponse(socketChannel);
        } catch (BufferOverflowException e) {
            println("Данные не поместились в буфер");
        }
        connectionManager.closeConnection();
        if (response == null) {
            exit();
            println("Соединение разорвано");
            return null;
        } else if (response.getResponseType().equals(ResponseType.TICKET_RESPONSE)) {
            request.setUser(currentUser);
            return tCommunicateWithServer(request);
        }
        return response;
    }


    /**
     * Отправляет запрос серверу вместе с элементом коллекции
     *
     * @param request
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Response tCommunicateWithServer(Request request) throws IOException, ClassNotFoundException {
        String arg = request.getArg();
        SocketChannel socketChannel = connectionManager.openConnection(port);
        Ticket ticket = ticketBuilder.askTicket();
        ticket.setUserName(currentUser.getUserName());
        request = requestSender.createExecuteRequest(request.getCommandName(), ticket);
        request.setArg(arg);
        request.setUser(getCurrentUser());
        requestSender.sendRequest(socketChannel, request);
        Response response = null;
        try {
            response = responseReader.readResponse(socketChannel);
        } catch (StreamCorruptedException e) {
            return response;
        } catch (BufferOverflowException e) {
            response = new Response();
            response.setMessage("Данные не поместились в буфер");
        }
        connectionManager.closeConnection();
        return response;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser() {
        ClientImpl.currentUser = currentUser;
    }

    @Override
    public void exit() {
        isRunning = false;
    }


    public void addCommands() {
        commandReader.addCommand("client_help", new ClientHelpCommand(commandReader));
        commandReader.addCommand("auth", new AuthCommand(clientAuthorizer));
        commandReader.addCommand("register", new RegisterCommand(clientAuthorizer));
        commandReader.addCommand("current_user", new UserCommand());
        commandReader.addCommand("exit", new ClientExitCommand(this));
    }
}
