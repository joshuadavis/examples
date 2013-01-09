package eg.dicomparison.containers.pico;

import eg.dicomparison.AbstractContainerBenchmark;
import eg.dicomparison.domain.*;
import org.picocontainer.DefaultPicoContainer;

/**
 * Picocontainer benchmark.  Creates only one instance of the dependencies.
 * <br/>
 * Created by IntelliJ IDEA.
 * User: josh
 * Date: 6/22/11
 * Time: 10:21 PM
 */
@SuppressWarnings({"UnusedDeclaration"})
public class PicoFourDeps extends AbstractContainerBenchmark {
    private DefaultPicoContainer container;

    @Override
    protected void setUpContainer() {
        container = new DefaultPicoContainer();
        container.addComponent(TestBean.class, FourServiceImpl.class);
        container.addComponent(ServiceOne.class,ServiceOneImpl.class);
        container.addComponent(ServiceTwo.class,ServiceTwoImpl.class);
        container.addComponent(ServiceThree.class,ServiceThreeImpl.class);
        container.addComponent(ServiceFour.class,ServiceFourImpl.class);
    }

    @Override
    protected TestBean createTestBean() {
        return container.getComponent(TestBean.class);
    }

    @Override
    protected void destroyContainer() {
    }
}
