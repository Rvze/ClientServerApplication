package command;

import connection.response.ResponseCreator;
import general.AbstractCommand;
import general.LimitedQueue;

public class HistoryCommand extends AbstractCommand {
    private final ServerCommandReader serverCommandReader;
    private final ResponseCreator responseCreator;

    public HistoryCommand(ServerCommandReader serverCommandReader, ResponseCreator responseCreator) {
        super("history", "вывести последние 11 команд (без их аргументов)");
        this.serverCommandReader = serverCommandReader;
        this.responseCreator = responseCreator;
    }

    @Override
    public void execute(String[] args) {
        LimitedQueue<String> history = serverCommandReader.getHistory();
        char h = 11;
        if (history.size() < 11) {
            h = (char) history.size();
        }
        responseCreator.addMessage("The list of last 11 commands: ");
        for (Object hstr : history) {
            responseCreator.addMessage(((String) hstr).trim());
        }
    }
}
