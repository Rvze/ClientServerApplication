package collection;


import general.Event;
import general.Ticket;
import general.TicketType;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

public interface CollectionManager {
    void info();

    void addElement(ServerTicket ticket);

    Stream<ServerTicket> getTicketStream();

    void countLessThanType(TicketType ticketType);

    void filterGreaterThanEvent(Event event);

    void printFieldDescendingPrice();

    HashSet<Long> getIdList();

    void removeLower(ServerTicket ticket, String username);

    boolean update(long id, ServerTicket tickets);

    void removeById(long id, String username);

    void clear(String username);

    boolean checkId(long id);

    ZonedDateTime getInitializationTime();

    boolean containsId(long id);

    ServerTicket getServerTicket(Ticket ticket);

    CopyOnWriteArrayList<ServerTicket> getTickets();
}
