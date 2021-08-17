package connection.request;

import exceptions.CommandIsNotExistException;
import exceptions.NotAuthorizationException;
import exceptions.UncorrectedPasswordException;
import exceptions.UserExistenceException;
import general.Request;
import general.Response;

public interface RequestHandler {
    Response handleRequest(Request request) throws CommandIsNotExistException,
            UncorrectedPasswordException,
            NotAuthorizationException,
            UserExistenceException;
}
