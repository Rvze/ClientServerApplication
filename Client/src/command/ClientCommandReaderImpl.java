package command;

import exceptions.CommandIsNotExistException;
import general.AbstractCommand;
import general.Ticket;

import java.util.HashSet;
import java.util.TreeMap;

public class ClientCommandReaderImpl implements ClientCommandReader {
    private final TreeMap<String, AbstractCommand> commandMap;
    private final HashSet<String> scriptSet;

    public ClientCommandReaderImpl() {
        commandMap = new TreeMap<>();
        scriptSet = new HashSet<>();
    }

    @Override
    public void executeCommand(String userCommand, Ticket ticket) throws CommandIsNotExistException {
        if (userCommand == null)
            return;
        String[] updatedUserCommand = userCommand.trim().toLowerCase().split("\\s+", 2);
        AbstractCommand command = commandMap.get(updatedUserCommand[0]);
        if (command != null) {
            if (!command.isTicketCommand())
                command.execute(updatedUserCommand);
            else
                throw new RuntimeException();
        } else if (!updatedUserCommand[0].equals(" ")) {
            throw new CommandIsNotExistException("Данной команды не существует!");
        }
    }

    @Override
    public void addCommand(String commandName, AbstractCommand abstractCommand) {
        commandMap.put(commandName, abstractCommand);
    }

    @Override
    public TreeMap<String, AbstractCommand> getCommandMap() {
        return commandMap;
    }

    public void addScript(String name) {
        scriptSet.add(name);
    }

    public void removeScript(String name) {
        scriptSet.remove(name);
    }
}
