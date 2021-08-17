package server;

import collection.CollectionManager;
import command.*;
import connection.ServerConnectionManager;
import connection.request.RequestHandler;
import connection.request.RequestReader;
import connection.response.ResponseCreator;
import connection.response.ResponseSender;
import general.IO;

public class ServerImpl implements Server, IO {
    private final CollectionManager collectionManager;
    private final ServerCommandReader serverCommandReader;
    private final ServerConnectionManager serverConnectionManager;
    private final ResponseCreator responseCreator;
    private final RequestHandler requestHandler;
    private final RequestReader requestReader;
    private final ResponseSender responseSender;
    private final int port;
    private boolean isRunning = true;

    public ServerImpl(CollectionManager collectionManager,
                      ServerCommandReader serverCommandReader,
                      ServerConnectionManager serverConnectionManager,
                      ResponseCreator responseCreator,
                      RequestHandler requestHandler,
                      RequestReader requestReader,
                      ResponseSender responseSender, int port) {
        this.collectionManager = collectionManager;
        this.serverCommandReader = serverCommandReader;
        this.serverConnectionManager = serverConnectionManager;
        this.responseCreator = responseCreator;
        this.requestHandler = requestHandler;
        this.requestReader = requestReader;
        this.responseSender = responseSender;
        this.port = port;
    }

    @Override
    public void start() {

    }

    @Override
    public void exit() {

    }

    public void addCommands(){
        serverCommandReader.addCommand("info", new InfoCommand(collectionManager));
        serverCommandReader.addCommand("show", new ShowCommand(collectionManager, responseCreator));
        serverCommandReader.addCommand("add", new AddCommand(collectionManager));
        serverCommandReader.addCommand("update_id", new UpdateIdCommand(collectionManager, responseCreator));
        serverCommandReader.addCommand("remove_by_id", new RemoveByIdCommand(collectionManager, responseCreator));
        serverCommandReader.addCommand("clear", new ClearCommand(collectionManager));
        serverCommandReader.addCommand("add_if_max", new AddIfMaxCommand(collectionManager, responseCreator));
        serverCommandReader.addCommand("remove_lower", new RemoveLowerCommand(collectionManager));
        serverCommandReader.addCommand("history", new HistoryCommand(serverCommandReader, responseCreator));
        serverCommandReader.addCommand("count_less_than_type", new CountLessThanTypeCommand(collectionManager, responseCreator));
        serverCommandReader.addCommand("print_field_descending_price", new PrintFieldDescendingPriceCommand(collectionManager, responseCreator));
        serverCommandReader.addCommand("help", new HelpCommand(serverCommandReader.getCommandMap(), responseCreator));
    }




    public void prepareToStart(){

    }
}
