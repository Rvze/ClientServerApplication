package connection.response;

import general.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ResponseSenderImpl implements ResponseSender {
    private SocketChannel socketChannel;

    @Override
    public void sendResponse(SocketChannel socketChannel, Response response) throws IOException {
        this.socketChannel = socketChannel;
        sendBytes(serializeResponse(response));
        
    }

    private void sendBytes(byte[] bytes) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        socketChannel.write(buffer);
    }

    private byte[] serializeResponse(Response response) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(response);
        return byteArrayOutputStream.toByteArray();
    }


}
