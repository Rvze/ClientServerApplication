package command;

import collection.CollectionManager;
import collection.ServerTicket;
import exceptions.InvalidCommandTypeException;
import general.AbstractCommand;
import general.TicketCommand;

public class AddCommand extends AbstractCommand implements TicketCommand {
    private final CollectionManager collectionManagerImpl;

    public AddCommand(CollectionManager collectionManager) {
        super("add", "{element} : добавить новый элемент в коллекцию", true);
        this.collectionManagerImpl = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        throw new InvalidCommandTypeException("add command required Ticket instance");
    }

    @Override
    public void execute(String[] args, ServerTicket ticket) {
        collectionManagerImpl.addElement(ticket);
    }

    @Override
    public boolean isTicketCommand() {
        return true;
    }
}
