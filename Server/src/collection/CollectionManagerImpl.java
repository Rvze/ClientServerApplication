package collection;

import general.Event;
import general.Ticket;
import general.TicketType;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

public class CollectionManagerImpl extends AbstractCollectionManager {
    private final ZonedDateTime creationDate;
    private final ResponseCreator responseCreator;
    private final TicketDataBase dataBase;
    private final HashMap<String, HashSet<Integer>> owners;


    @Override
    public void info() {

    }

    @Override
    public void addElement(ServerTicket ticket) {

    }

    @Override
    public Stream<ServerTicket> getTicketStream() {
        return null;
    }

    @Override
    public void countLessThanType(TicketType ticketType) {

    }

    @Override
    public void filterGreaterThanEvent(Event event) {

    }

    @Override
    public void printFieldDescendingPrice() {

    }

    @Override
    public HashSet<Long> getIdList() {
        return null;
    }

    @Override
    public void removeLower(ServerTicket ticket, String username) {

    }

    @Override
    public boolean update(long id, ServerTicket tickets) {
        return false;
    }

    @Override
    public void removeById(long id, String username) {

    }

    @Override
    public void clear(String username) {

    }

    @Override
    public boolean checkId(long id) {
        return false;
    }

    @Override
    public ZonedDateTime getInitializationTime() {
        return null;
    }

    @Override
    public boolean containsId(long id) {
        return false;
    }

    @Override
    public ServerTicket getServerTicket(Ticket ticket) {
        return null;
    }

    @Override
    public CopyOnWriteArrayList<ServerTicket> getTickets() {
        return null;
    }
}
