package command;

import collection.ServerTicket;
import exceptions.CommandIsNotExistException;
import general.AbstractCommand;
import general.Command;
import general.LimitedQueue;

import java.util.HashMap;

public interface ServerCommandReader {
    LimitedQueue<String> getHistory();

    void executeCommand(String userCommand, ServerTicket ticket) throws CommandIsNotExistException;

    void executeServerCommand(String userCommand) throws CommandIsNotExistException;

    void addCommand(String commandName, AbstractCommand command);

    void addServerCommand(String commandName, ServerCommand serverCommand);

    boolean isTicketCommand(Command command);

    HashMap<String, AbstractCommand> getCommandMap();

    HashMap<String, ServerCommand> getServerCommandMap();

    AbstractCommand getCommandByName(String commandName);
}
