package command;

import collection.CollectionManager;
import collection.ServerTicket;
import connection.response.ResponseCreator;
import exceptions.InvalidCommandTypeException;
import general.AbstractCommand;
import general.TicketCommand;
import subsidiary.InputChecker;

public class AddIfMaxCommand extends AbstractCommand implements TicketCommand {
    private final CollectionManager collectionManager;
    private final ResponseCreator responseCreator;

    public AddIfMaxCommand(CollectionManager collectionManager, ResponseCreator responseCreator) {
        super("add_if_max", "{element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.collectionManager = collectionManager;
        this.responseCreator = responseCreator;

    }

    @Override
    public void execute(String[] args) {
        throw new InvalidCommandTypeException("add if max command required Ticket instance");
    }

    @Override
    public void execute(String[] args, ServerTicket ticket) {
        long id;
        if (args.length != 3) {
            throw new RuntimeException("Неизвестная ошибка, не найдены все аргументы команды");
        } else if (args[1] == null)
            responseCreator.addMessage("Не указан id");
        else if (InputChecker.checkLong(args[1].trim())) {
            if (ticket.compareByTicket(collectionManager.getTicketStream().max(ServerTicket.getTicketComparator()).get()) > 0) {
                collectionManager.addElement(ticket);
            } else
                System.out.println("Нет элементов в коллекции");
        }


    }
}
