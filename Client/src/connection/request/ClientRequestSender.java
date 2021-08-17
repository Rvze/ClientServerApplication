package connection.request;

import general.Request;
import general.RequestType;

import java.io.*;
import java.nio.channels.SocketChannel;

public class ClientRequestSender implements RequestSender {
    @Override
    public void sendRequest(SocketChannel socketChannel, Request request) throws IOException {
        byte[] bytes = serializeRequest(request);
        OutputStream stream = socketChannel.socket().getOutputStream();
        stream.write(bytes);
        stream.flush();
    }

    public byte[] serializeRequest(Request request) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(request);
        return byteArrayOutputStream.toByteArray();
    }
}
