package general;

import java.util.LinkedList;

public class LimitedQueue<E> extends LinkedList {
    private final int limit;

    public LimitedQueue(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean add(Object o) {
        super.add(o);
        while (size() > limit) {
            super.remove();
        }
        return true;
    }
}
