package connection.response;

import general.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.BufferOverflowException;
import java.nio.channels.SocketChannel;

public class ResponseReaderImpl implements ResponseReader {
    @Override
    public Response readResponse(SocketChannel socketChannel) throws IOException, ClassNotFoundException {
        int capacity = 16384;
        byte[] bytes = new byte[capacity];
        InputStream inputStream = socketChannel.socket().getInputStream();
        if (capacity == inputStream.read(bytes))
            throw new BufferOverflowException();
        return deserializableRequest(bytes);
    }

    private Response deserializableRequest(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        return (Response) objectInputStream.readObject();
    }
}
