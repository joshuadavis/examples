package eg.dicomparison.containers.weld;

import eg.dicomparison.AbstractContainerBenchmark;
import eg.dicomparison.domain.TestBean;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jboss.weld.environment.se.events.ContainerInitialized;
import org.jboss.weld.literal.DefaultLiteral;

import javax.enterprise.inject.Instance;
import javax.enterprise.util.AnnotationLiteral;

/**
 * Simple implementation.
 * <br/>
 * Created by IntelliJ IDEA.
 * User: josh
 * Date: 6/22/11
 * Time: 11:46 PM
 */
@SuppressWarnings({"UnusedDeclaration"})
public class WeldNoDeps extends AbstractContainerBenchmark {
    private WeldContainer weld;
    private Instance<TestBean> instance;

    @Override
    protected void setUpContainer() {
        weld = new Weld().initialize();
        weld.event().select(ContainerInitialized.class).fire(new ContainerInitialized());
        instance = weld.instance().select(TestBean.class);
    }

    @Override
    protected TestBean createTestBean() {
        return instance.get();
    }

    @Override
    protected void destroyContainer() {
        weld = null;
    }
}
