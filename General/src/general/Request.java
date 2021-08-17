package general;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = -543534L;

    private RequestType requestType;

    private String commandName;

    private String arg;

    private Ticket ticket;

    private User user;

    public Request(RequestType requestType, String commandName, String arg) {
        this.requestType = requestType;
        this.commandName = commandName;
        this.arg = arg;
    }

    public Request(RequestType requestType, User user) {
        this.requestType = requestType;
        this.user = user;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
