package connection.request;

import general.Request;
import general.RequestType;
import general.Ticket;


public interface RequestCreator {
    default Request createBasicRequest(String userString) {
        userString = userString.trim();
        return new Request(RequestType.COMMAND_REQUEST, userString, null);
    }

    default Request createExecuteRequest(String userString, Ticket ticket) {
        String[] strings = userString.trim().split("\\s");
        Request request;
        if (strings.length > 1)
            request = new Request(RequestType.EXECUTE_REQUEST, strings[0], strings[0]);
        else
            request = new Request(RequestType.EXECUTE_REQUEST, strings[0], null);
        request.setTicket(ticket);
        return request;
    }
}
