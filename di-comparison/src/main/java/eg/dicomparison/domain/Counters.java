package eg.dicomparison.domain;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * object creation counters
 * <br/>
 * Created by IntelliJ IDEA.
 * User: josh
 * Date: 6/22/11
 * Time: 11:15 PM
 */
public class Counters {
    private static Counters ourInstance = new Counters();

    public static Counters getInstance() {
        return ourInstance;
    }

    private AtomicInteger clientObjectsCreated = new AtomicInteger();
    private AtomicInteger serviceObjectsCreated = new AtomicInteger();
    private AtomicInteger clientObjectsFinalized = new AtomicInteger();

    private Counters() {
    }

    public int getClientObjectsCreated() {
        return clientObjectsCreated.get();
    }

    public int getServiceObjectsCreated() {
        return serviceObjectsCreated.get();
    }

    public int getClientObjectsFinalized() {
        return clientObjectsFinalized.get();
    }

    public void incrementClient() {
        clientObjectsCreated.incrementAndGet();
    }

    public void incrementService() {
        serviceObjectsCreated.incrementAndGet();
    }

    public void clear() {
        clientObjectsCreated.set(0);
        serviceObjectsCreated.set(0);
        clientObjectsFinalized.set(0);
    }

    public void incrementClientFinalized() {
        clientObjectsFinalized.incrementAndGet();
    }
}
