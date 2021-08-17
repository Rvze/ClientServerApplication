package collection;

import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractCollectionManager implements CollectionManager {
    protected CopyOnWriteArrayList<ServerTicket> tickets;
}
