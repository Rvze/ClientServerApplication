package command;

import collection.CollectionManager;
import connection.response.ResponseCreator;
import general.AbstractCommand;
import general.TicketType;

public class CountLessThanTypeCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final ResponseCreator responseCreator;

    public CountLessThanTypeCommand(CollectionManager collectionManager, ResponseCreator responseCreator) {
        super("count_less_than_type", "вывести количество элементов, значение поля type которых меньше заданного");
        this.collectionManager = collectionManager;
        this.responseCreator = responseCreator;

    }

    @Override
    public void execute(String[] args) {
        if (args.length != 3) {
            responseCreator.addMessage("Pls input argument");
            return;
        }
        collectionManager.countLessThanType(TicketType.valueOf(args[1].trim()));
    }
}
