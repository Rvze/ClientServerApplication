package connection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

public class ServerConnectionManagerImpl implements ServerConnectionManager {
    private ServerSocketChannel serverSocketChannel;

    @Override
    public ServerSocketChannel openConnection(int port) throws IOException {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("localhost", port));
            serverSocketChannel.configureBlocking(false);
            return serverSocketChannel;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
