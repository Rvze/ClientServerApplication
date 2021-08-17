package connection;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;

public interface ServerConnectionManager {
    ServerSocketChannel openConnection(int port) throws IOException;
}
