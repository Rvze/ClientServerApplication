package command;

import general.AbstractCommand;
import client.Client;

public class ClientExitCommand extends AbstractCommand {
    Client client;

    public ClientExitCommand(Client client) {
        super("exit: ", "Finish client app work");
        this.client = client;
    }

    @Override
    public void execute(String[] args) {
        client.exit();
    }

    @Override
    public boolean isTicketCommand() {
        return false;
    }
}
