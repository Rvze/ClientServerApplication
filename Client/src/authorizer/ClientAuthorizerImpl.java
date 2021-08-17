package authorizer;

import client.ClientImpl;
import connection.ClientConnectionManager;
import connection.request.RequestSender;
import connection.response.ResponseReader;
import exceptions.UncorrectedPasswordException;
import general.*;

import java.io.IOException;
import java.net.ConnectException;

public class ClientAuthorizerImpl implements ClientAuthorizer, IO {
    private final ClientConnectionManager clientConnectionManager;
    private final RequestSender requestSender;
    private final ResponseReader responseReader;
    private String userName;
    private String password;
    private User user;

    public ClientAuthorizerImpl(ClientConnectionManager clientConnectionManager, RequestSender requestSender, ResponseReader responseReader) {
        this.clientConnectionManager = clientConnectionManager;
        this.requestSender = requestSender;
        this.responseReader = responseReader;
    }

    @Override
    public void registerClient() throws IOException {
        try {
            inputRegistration();
            user = new User(userName, password);
            Request request = new Request(RequestType.REGISTRATION_REQUEST, user);
            clientConnectionManager.openConnection(8008);
            requestSender.sendRequest(clientConnectionManager.getSocketChannel(), request);
            Response response = responseReader.readResponse(clientConnectionManager.getSocketChannel());
            println(response.getMessage());
        } catch (UncorrectedPasswordException e) {
            errPrint(e.getMessage());
            println("Pls, try again");
            registerClient();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            clientConnectionManager.closeConnection();
        }
    }

    @Override
    public void authorizerClient() throws IOException {
        try {
            inputRegistration();
            user = new User(userName, password);
            ClientImpl.setCurrentUser();
            Request request = new Request(RequestType.AUTHORIZATION_REQUEST, user);
            clientConnectionManager.openConnection(8008);
            requestSender.sendRequest(clientConnectionManager.getSocketChannel(), request);
            Response response = responseReader.readResponse(clientConnectionManager.getSocketChannel());
            if (!response.getResponseType().equals(ResponseType.ERROR_RESPONSE)) {
                println(response.getMessage());
            } else
                println(response.getMessage());
        } catch (UncorrectedPasswordException e) {
            errPrint(e.getMessage());
            println("Please try again");
            authorizerClient();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ConnectException e) {
            println("Server is unavailable");
            System.exit(0);
        } finally {
            clientConnectionManager.closeConnection();
        }
    }

    private void inputRegistration() throws UncorrectedPasswordException, IOException {
        println(">Input username");
        userName = readLine();
        println(">Input password");
        password = readPassword();
        if (userName.length() < 4 || userName.length() > 20 || password.length() < 3 || password.length() > 20) {
            String exception = "";
            if (userName.length() < 4 || userName.length() > 20) {
                exception = exception + " The username can't be at least 4 and at max 20 characters long\n";
            }
            if (password.length() < 3 || password.length() > 20) {
                exception = exception + " The password can't be at least 3 and at max 20 characters long\n";
            }
            throw new UncorrectedPasswordException(exception);
        }
    }

}
