package command;

import collection.CollectionManager;
import connection.response.ResponseCreator;
import general.AbstractCommand;

import java.util.NoSuchElementException;

public class PrintFieldDescendingPriceCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final ResponseCreator responseCreator;

    public PrintFieldDescendingPriceCommand(CollectionManager collectionManager, ResponseCreator responseCreator) {
        super("print_field_descending_price", "вывести значения поля price всех элементов в порядке убывания");
        this.collectionManager = collectionManager;
        this.responseCreator = responseCreator;
    }

    @Override
    public void execute(String[] args) {
        try {
            collectionManager.printFieldDescendingPrice();
        } catch (NoSuchElementException e) {
            responseCreator.addMessage(e.getMessage());
        }

    }
}
