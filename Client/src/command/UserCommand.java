package command;

import general.AbstractCommand;
import client.ClientImpl;

public class UserCommand extends AbstractCommand {

    public UserCommand() {
        super("current_user: ", "Show the active user on the app");
    }

    @Override
    public void execute(String[] args) {
        if (ClientImpl.getCurrentUser() == null) {
            System.out.println("There is no active user on client app");
        } else
            System.out.println(ClientImpl.getCurrentUser().getUserName());
    }
}
