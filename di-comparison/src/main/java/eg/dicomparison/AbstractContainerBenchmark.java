package eg.dicomparison;

import eg.dicomparison.domain.Counters;
import eg.dicomparison.domain.TestBean;
import org.apache.commons.lang.time.StopWatch;

/**
 * Base class for container benchmark.
 * <br/>
 * Created by IntelliJ IDEA.
 * User: josh
 * Date: 6/22/11
 * Time: 10:02 PM
 */
public abstract class AbstractContainerBenchmark {
    private int iterations;
    private StopWatch diTime = new StopWatch();

    public Result run() {
        Counters.getInstance().clear();
        setUpContainer();   // Create the container / injector or whatever.
        diTime.start();
        for (int i = 0; i < iterations ; i++) {
            // Create the object.
            TestBean bean = createTestBean();
            // Call the test method.
            bean.action();
        }
        diTime.stop();
        destroyContainer();
        return new Result(Counters.getInstance().getClientObjectsCreated(),Counters.getInstance().getServiceObjectsCreated(),diTime.getTime());
    }

    protected abstract void setUpContainer();

    protected abstract TestBean createTestBean();

    protected abstract void destroyContainer();

    public void initialize(int iterations) {
        this.iterations = iterations;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "{" +
                "iterations=" + iterations +
                '}';
    }
}
