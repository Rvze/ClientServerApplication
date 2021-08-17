package command;

import authorizer.ClientAuthorizer;
import general.AbstractCommand;

import java.io.IOException;

public class RegisterCommand extends AbstractCommand {
    ClientAuthorizer clientAuthorizer;

    public RegisterCommand(ClientAuthorizer clientAuthorizer) {
        super("registration: ", "User registration at client app");
        this.clientAuthorizer = clientAuthorizer;
    }

    @Override
    public void execute(String[] args) {
        try {
            clientAuthorizer.registerClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isTicketCommand() {
        return false;
    }
}
