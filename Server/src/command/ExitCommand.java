package command;

import general.AbstractCommand;
import server.Server;

public class ExitCommand implements ServerCommand {
    private final Server server;

    public ExitCommand(Server server) {
        this.server = server;
    }

    @Override
    public void execute() {
        server.exit();
    }
}
