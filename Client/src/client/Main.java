package client;

import authorizer.ClientAuthorizer;
import authorizer.ClientAuthorizerImpl;
import command.ClientCommandReaderImpl;
import connection.ClientConnectionManager;
import connection.ClientConnectionManagerImpl;
import connection.request.ClientRequestSender;
import connection.request.RequestSender;
import connection.response.ResponseReader;
import connection.response.ResponseReaderImpl;

public class Main {
    public static void main(String[] args) {
        if (args.length == 1) {
            try {
                ClientConnectionManager manager = new ClientConnectionManagerImpl();
                RequestSender requestSender = new ClientRequestSender();
                ResponseReader responseReader = new ResponseReaderImpl();
                ClientAuthorizer clientAuthorizer = new ClientAuthorizerImpl(manager, requestSender, responseReader);
                Client client = new ClientImpl(new ClientCommandReaderImpl(), manager, requestSender, responseReader, clientAuthorizer, Integer.parseInt(args[0]));
                client.start(Integer.parseInt(args[0]));

            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        } else
            System.out.println("Input port");
    }
}
