package eg.dicomparison;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class that runs all the comparisons.
 * <br/>
 * Created by IntelliJ IDEA.
 * User: josh
 * Date: 6/22/11
 * Time: 10:32 PM
 */
public class Comparison {
    private static final Logger log = LoggerFactory.getLogger(Comparison.class);

    private static final String[] BENCHMARK_CLASSES = {
            "eg.dicomparison.containers.pico.PicoNoDeps",
            "eg.dicomparison.containers.guice.GuiceNoDeps",
            "eg.dicomparison.containers.spring.SpringNoDeps",
            "eg.dicomparison.containers.weld.WeldNoDeps",
            "eg.dicomparison.containers.pico.PicoFourDeps",
            "eg.dicomparison.containers.guice.GuiceFourDeps",
            "eg.dicomparison.containers.spring.SpringFourDeps",
    };

    public static void main(String[] args) {
        try {
            Comparison c = new Comparison();
            c.go();
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(-1);
        }
    }

    private void go() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        List<AbstractContainerBenchmark> benchmarks = new ArrayList<AbstractContainerBenchmark>();
        int iterations = 1000000;
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        for (String benchmarkClass : BENCHMARK_CLASSES) {
            log.info("Instantiating " + benchmarkClass + " ...");
            @SuppressWarnings({"unchecked"}) Class<AbstractContainerBenchmark> clazz =
                    (Class<AbstractContainerBenchmark>) cl.loadClass(benchmarkClass);
            AbstractContainerBenchmark benchmark = clazz.newInstance();
            benchmark.initialize(iterations);
            benchmarks.add(benchmark);
        }

        for (AbstractContainerBenchmark benchmark : benchmarks) {
            log.info("Running " + benchmark + " ...");
            Result r = benchmark.run();
            log.info("Result = " + r);
        }
    }
}
