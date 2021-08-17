package general;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = 54252L;
    private final ResponseType responseType;
    private String message;

    public Response(ResponseType responseType, String message) {
        this.responseType = responseType;
        this.message = message;
    }

    public Response(){
        message = "";
        responseType = ResponseType.BASIC_RESPONSE;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
