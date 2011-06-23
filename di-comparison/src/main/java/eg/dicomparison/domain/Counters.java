package eg.dicomparison.domain;

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

    private int clientObjectsCreated;
    private int serviceObjectsCreated;

    private Counters() {
    }

    public int getClientObjectsCreated() {
        return clientObjectsCreated;
    }

    public int getServiceObjectsCreated() {
        return serviceObjectsCreated;
    }

    public void incrementClient() {
        clientObjectsCreated++;
    }

    public void incrementService() {
        serviceObjectsCreated++;
    }

    public void clear() {
        clientObjectsCreated = serviceObjectsCreated = 0;
    }
}
