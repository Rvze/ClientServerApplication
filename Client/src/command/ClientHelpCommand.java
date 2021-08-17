package command;

import general.AbstractCommand;

import java.util.TreeMap;

public class ClientHelpCommand extends AbstractCommand {
    private final ClientCommandReader commandReader;

    public ClientHelpCommand(ClientCommandReader commandReader) {
        super("help: ", "Show all client commands names and descriptions");
        this.commandReader = commandReader;
    }

    @Override
    public void execute(String[] args) {
        TreeMap<String, AbstractCommand> map = commandReader.getCommandMap();
        for (String str : map.keySet()) {
            AbstractCommand command = map.get(str);
            System.out.printf("%-14s", command.getName());
            System.out.printf("%s", " : ", command.getDescription());
        }
    }
}
