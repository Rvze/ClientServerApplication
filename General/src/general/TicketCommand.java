package general;

import collection.ServerTicket;

public interface TicketCommand {
    void execute(String[] args, ServerTicket ticket);

    default boolean isTicketCommand() {
        return true;
    }
}
