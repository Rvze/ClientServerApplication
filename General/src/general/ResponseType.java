package general;

import java.io.Serializable;

public enum ResponseType implements Serializable {
    BASIC_RESPONSE,
    ERROR_RESPONSE,
    TICKET_RESPONSE;
    private static final long serialVersionUID = 653653L;
}
