package runnable;

import connection.request.RequestHandler;
import exceptions.CommandIsNotExistException;
import exceptions.NotAuthorizationException;
import exceptions.UncorrectedPasswordException;
import exceptions.UserExistenceException;
import general.Request;
import general.Response;

import java.util.concurrent.Callable;

public class HandleThread implements Callable<Response> {
    private final RequestHandler requestHandler;
    private final Request request;

    public HandleThread(RequestHandler requestHandler, Request request) {
        this.requestHandler = requestHandler;
        this.request = request;
    }

    @Override
    public Response call() throws Exception {
        try {
            return requestHandler.handleRequest(request);
        } catch (CommandIsNotExistException | UncorrectedPasswordException | NotAuthorizationException | UserExistenceException e) {
            Response response = new Response();
            response.setMessage(e.getMessage());
            return response;
        }
    }
}
