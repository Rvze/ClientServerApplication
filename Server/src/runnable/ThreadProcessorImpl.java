package runnable;

import connection.request.RequestHandler;
import connection.request.RequestReader;
import connection.response.ResponseSender;
import general.IO;
import general.Request;
import general.Response;

import java.nio.channels.SocketChannel;
import java.util.concurrent.*;

public class ThreadProcessorImpl implements ThreadProcessor, IO, Runnable {
    private final RequestReader requestReader;
    private final RequestHandler requestHandler;
    private final ResponseSender responseSender;
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final SocketChannel socketChannel;
    static int numOfThreads = Runtime.getRuntime().availableProcessors();

    public ThreadProcessorImpl(RequestReader requestReader,
                               RequestHandler requestHandler,
                               ResponseSender responseSender,
                               SocketChannel socketChannel) {
        this.requestReader = requestReader;
        this.requestHandler = requestHandler;
        this.responseSender = responseSender;
        this.socketChannel = socketChannel;
    }

    @Override
    public void process() throws ExecutionException, InterruptedException {
        try {
            ForkJoinPool readThread = new ForkJoinPool(numOfThreads);
            ForkJoinTask<Request> result = readThread.submit(new ReadThread(socketChannel, requestReader));
            println("Reading request...");
            Request request;

            request = result.get();

            Callable<Response> callable2 = new HandleThread(requestHandler, request);
            FutureTask<Response> futureTask2 = new FutureTask<>(callable2);
            println("Handling request...");
            executorService.submit(futureTask2);
            Response response = futureTask2.get();
            println("Sending response...");
            new Thread(new SendThread(responseSender, socketChannel, response)).start();
        } catch (ExecutionException | InterruptedException e) {
            shutDownExecutorService();
            e.printStackTrace();
        }
    }

    @Override
    public void shutDownExecutorService() {
        executorService.shutdown();
    }

    @Override
    public void run() {
        try {
            process();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
