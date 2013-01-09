package eg.dicomparison;

/**
 * Benchmark result.
 * <br/>
 * Created by IntelliJ IDEA.
 * User: josh
 * Date: 6/22/11
 * Time: 10:56 PM
 */
public class Result {
    private long clientObjectsCreated;
    private long serviceObjectsCreated;
    private long elapsedTime;

    public Result(long clientObjectsCreated,long serviceObjectsCreated, long elapsedTime) {
        this.clientObjectsCreated = clientObjectsCreated;
        this.serviceObjectsCreated = serviceObjectsCreated;
        this.elapsedTime = elapsedTime;
    }

    public long getClientObjectsCreated() {
        return clientObjectsCreated;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    @Override
    public String toString() {
        return "Result{" +
                "clientObjectsCreated=" + clientObjectsCreated +
                ", serviceObjectsCreaetd=" + serviceObjectsCreated +
                ", elapsedTime=" + elapsedTime + "ms " +
                '}';
    }
}
