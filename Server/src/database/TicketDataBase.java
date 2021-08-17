package database;

import collection.ServerTicket;
import exceptions.SQLNoDataException;
import general.User;

import java.util.concurrent.CopyOnWriteArrayList;

public interface TicketDataBase {
    ServerTicket getTicket(long id) throws SQLNoDataException;

    int getNextId() throws SQLNoDataException;

    void insertTicket(ServerTicket ticket) throws Exception;

    boolean deleteTicket();

    boolean deleteTicketOfUsers(User user) throws SQLNoDataException;

    boolean deleteTicketById(long id);

    void updateTicket(long id, ServerTicket ticket) throws SQLNoDataException;

    CopyOnWriteArrayList<ServerTicket> getAll() throws SQLNoDataException;
}
