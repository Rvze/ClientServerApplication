package command;

import exceptions.CommandIsNotExistException;
import general.AbstractCommand;
import general.Ticket;

import java.util.TreeMap;

public interface ClientCommandReader {
    void executeCommand(String userCommand, Ticket ticket) throws ClassNotFoundException, CommandIsNotExistException;

    void addCommand(String commandName, AbstractCommand abstractCommand);

    TreeMap<String, AbstractCommand> getCommandMap();
}
