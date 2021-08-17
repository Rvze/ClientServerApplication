package connection.request;

import collection.CollectionManager;
import command.ServerCommandReader;
import connection.response.ResponseCreator;
import exceptions.CommandIsNotExistException;
import exceptions.NotAuthorizationException;
import exceptions.UncorrectedPasswordException;
import exceptions.UserExistenceException;
import general.*;
import usermanager.UserManager;

import java.util.Locale;

public class RequestHandlerImpl implements RequestHandler {
    private final UserManager userManager;
    private final ServerCommandReader commandReader;
    private final CollectionManager collectionManager;
    private final ResponseCreator responseCreator;
    private Response response;

    public RequestHandlerImpl(UserManager userManager, ServerCommandReader commandReader, CollectionManager collectionManager, ResponseCreator responseCreator) {
        this.userManager = userManager;
        this.commandReader = commandReader;
        this.collectionManager = collectionManager;
        this.responseCreator = responseCreator;
    }

    @Override
    public Response handleRequest(Request request) throws CommandIsNotExistException, UncorrectedPasswordException, NotAuthorizationException, UserExistenceException {
        if (userManager.isRegistered(request.getUser())) {
            if (request.getRequestType().equals(RequestType.AUTHORIZATION_REQUEST)) {
                response = authHandler(request);
            } else if (request.getRequestType().equals(RequestType.REGISTRATION_REQUEST)) {
                response = registerHandler(request);
            } else if (request.getRequestType().equals(RequestType.EXECUTE_REQUEST)) {
                response = executeHandler(request);
            } else if (request.getRequestType().equals(RequestType.COMMAND_REQUEST)) {
                response = commandHandler(request);
            } else
                response = errorHandler();
        } else if (request.getRequestType() == RequestType.AUTHORIZATION_REQUEST) {
            response = authHandler(request);
        } else if (request.getRequestType() == RequestType.REGISTRATION_REQUEST) {
            response = registerHandler(request);
        } else
            throw new NotAuthorizationException();

        return response;
    }

    private Response authHandler(Request request) throws UserExistenceException, UncorrectedPasswordException {
        if (userManager.isRegistered(request.getUser())) {
            response = responseCreator.getResponse();
            response.setMessage(response.getMessage() + " Auth is successful");
        } else {
            if (userManager.isExist(request.getUser())) {
                throw new UncorrectedPasswordException("Uncorrected password");
            } else
                throw new UserExistenceException("User is not exist");
        }
        return response;
    }

    private Response registerHandler(Request request) throws UserExistenceException, UncorrectedPasswordException {
        if (userManager.isRegistered(request.getUser())) {
            throw new UserExistenceException("This user is already registered");
        } else {
            if (!userManager.isRegistered(request.getUser())) {
                userManager.insertToRegister(request.getUser());
                response = responseCreator.getResponse();
                response.setMessage(response.getMessage() + " registration is successful");
            } else
                throw new UncorrectedPasswordException("Uncorrected password, already busy");
        }
        return response;
    }

    private Response executeHandler(Request request) throws CommandIsNotExistException {
        String addingString = request.getCommandName().trim();
        if (request.getArg() != null) {
            addingString = addingString + " " + request.getArg().trim();
        } else
            addingString = addingString + " null";

        addingString = addingString + " " + request.getUser().getUserName();
        System.out.println(request.getTicket().toString());
        commandReader.executeCommand(addingString, collectionManager.getServerTicket(request.getTicket()));
        response = responseCreator.getResponse();
        return response;
    }

    private Response commandHandler(Request request) throws CommandIsNotExistException {
        Command command = commandReader.getCommandByName(request.getCommandName().trim().toLowerCase());
        if (request.getCommandName().equalsIgnoreCase("execute_script")) {
            return new Response(ResponseType.BASIC_RESPONSE, "");
        }
        if (command == null) {
            response = responseCreator.getResponse();
            response.setMessage(response.getMessage() + " command is not exist");
        } else if (command.isTicketCommand()) {
            response = responseCreator.getResponse("", ResponseType.TICKET_RESPONSE);
        } else {
            String addingString = request.getCommandName();
            if (request.getArg() != null)
                addingString += " " + request.getArg();
            else
                addingString += " " + null;
            addingString = addingString + " " + request.getUser().getUserName();
            commandReader.executeCommand(addingString, null);
            response = responseCreator.getResponse();

        }
        return response;
    }

    private Response errorHandler() {
        return new Response(ResponseType.ERROR_RESPONSE, " too big message");
    }


}
