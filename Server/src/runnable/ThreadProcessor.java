package runnable;

import java.util.concurrent.ExecutionException;

public interface ThreadProcessor {
    void process() throws ExecutionException, InterruptedException;

    void shutDownExecutorService();
}
