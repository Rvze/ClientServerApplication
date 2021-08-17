package general;

import java.io.Serializable;

public enum RequestType implements Serializable {
    COMMAND_REQUEST,
    EXECUTE_REQUEST,
    REGISTRATION_REQUEST,
    AUTHORIZATION_REQUEST,
    ERROR_REQUEST;
    private final static long serialVersionUID = 54543L;
}
