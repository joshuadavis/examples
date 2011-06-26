package eg.dicomparison;

import eg.dicomparison.domain.Counters;
import eg.dicomparison.domain.TestBean;
import org.apache.commons.lang.time.StopWatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for container benchmark.
 * <br/>
 * Created by IntelliJ IDEA.
 * User: josh
 * Date: 6/22/11
 * Time: 10:02 PM
 */
public abstract class AbstractContainerBenchmark {
    private static final Logger log = LoggerFactory.getLogger(AbstractContainerBenchmark.class);

    private int iterations;
    private StopWatch diTime = new StopWatch();

    public Result run() {
        Counters counters = Counters.getInstance();
        counters.clear();
        setUpContainer();   // Create the container / injector or whatever.
        diTime.start();
        for (int i = 0; i < iterations; i++) {
            // Create the object.
            TestBean bean = createTestBean();
            // Call the test method.
            bean.action();
        }
        diTime.stop();
        destroyContainer();
        long elapsedTime = diTime.getTime();
        log.info(this.getClass().getName() + "\n\t" +
                "clientsCreated=" + counters.getClientObjectsCreated() +
                " clientFinalized=" + counters.getClientObjectsFinalized() +
                "\n\tservicesCreated=" + counters.getServiceObjectsCreated() +
                "\n\telapsed=" + elapsedTime + "ms"
        );

        return new Result(counters.getClientObjectsCreated(), counters.getServiceObjectsCreated(), elapsedTime);
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
