package command;

import authorizer.ClientAuthorizer;
import general.AbstractCommand;

import java.io.IOException;

public class AuthCommand extends AbstractCommand {
    private ClientAuthorizer clientAuthorizer;

    public AuthCommand(ClientAuthorizer clientAuthorizer) {
        super("auth: ", "User authorization at client app", false);
        this.clientAuthorizer = clientAuthorizer;
    }

    @Override
    public void execute(String[] args) {
        try {
            clientAuthorizer.authorizerClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isTicketCommand() {
        return false;
    }
}
