package connection.response;

import general.Response;
import general.ResponseType;

public interface ResponseCreator {
    public void addMessage(String message);

    public Response getResponse(String message, ResponseType responseType);

    public Response getResponse();
}
