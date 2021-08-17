package collection;

import general.Ticket;

public class ServerTicket extends Ticket {
    public ServerTicket(Ticket ticket, long id) {
        super(ticket.getName(), ticket.getCoordinates(), ticket.getPrice(), ticket.getDiscount(), ticket.isRefundable(), ticket.getType(),
                ticket.getEvent(), ticket.getUserName());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
