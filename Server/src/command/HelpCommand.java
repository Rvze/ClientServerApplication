package command;

import connection.response.ResponseCreator;
import general.AbstractCommand;

import java.util.HashMap;

public class HelpCommand extends AbstractCommand {
    private final HashMap<String, AbstractCommand> commandMap;
    private final ResponseCreator responseCreator;

    public HelpCommand(HashMap<String, AbstractCommand> commandMap, ResponseCreator responseCreator) {
        super("help", " вывести справку по доступным командам");
        this.commandMap = commandMap;
        this.responseCreator = responseCreator;
    }

    @Override
    public void execute(String[] args) {
        for (String str : commandMap.keySet()) {
            responseCreator.addMessage(commandMap.get(str).getName() + commandMap.get(str).getDescription());
        }

    }
}
