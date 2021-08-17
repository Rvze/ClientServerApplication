package runnable;

import connection.request.RequestReader;
import general.Request;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.RecursiveTask;

public class ReadThread extends RecursiveTask<Request> {
    private final SocketChannel socketChannel;
    private final RequestReader requestReader;

    public ReadThread(SocketChannel socketChannel, RequestReader requestReader) {
        this.socketChannel = socketChannel;
        this.requestReader = requestReader;
    }

    @Override
    protected Request compute() {
        try {
            return requestReader.readRequest(socketChannel);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
