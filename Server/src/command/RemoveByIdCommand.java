package command;

import collection.CollectionManager;
import connection.response.ResponseCreator;
import general.AbstractCommand;
import subsidiary.InputChecker;

public class RemoveByIdCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final ResponseCreator responseCreator;

    public RemoveByIdCommand(CollectionManager collectionManager, ResponseCreator responseCreator) {
        super("remove_by_id", "удалить элемент из коллекции по его id");
        this.collectionManager = collectionManager;
        this.responseCreator = responseCreator;
    }

    @Override
    public void execute(String[] args) {
        long id;
        if (args.length != 3) {
            throw new RuntimeException("Неизвестная ошибка, не найдены все аргументы команды");
        } else if (args[1] == null)
            responseCreator.addMessage("Не указан id");
        else if (InputChecker.checkLong(args[1].trim())) {
            id = Long.parseLong(args[1]);
            if (collectionManager.containsId(id)) {
                try {
                    collectionManager.removeById(id, args[2]);
                } catch (NotOwnerException e) {
                    responseCreator.addMessage(e.getMessage());
                }
            } else
                responseCreator.addMessage("Данный id не найден");
        } else
            responseCreator.addMessage("Неверный формат id");
    }
}
