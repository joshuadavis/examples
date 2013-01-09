package eg.dicomparison.containers.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;
import eg.dicomparison.AbstractContainerBenchmark;
import eg.dicomparison.domain.NoDepsImpl;
import eg.dicomparison.domain.TestBean;

/**
 * Guice 2.x injection - everything is a singleton except TestBean.
 * <br/>
 * Created by IntelliJ IDEA.
 * User: josh
 * Date: 6/22/11
 * Time: 10:23 PM
 */
@SuppressWarnings({"UnusedDeclaration"})
public class GuiceNoDeps extends AbstractContainerBenchmark {
    private Injector injector;

    @Override
    protected void setUpContainer() {
        injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(TestBean.class).to(NoDepsImpl.class).in(Scopes.NO_SCOPE);
            }
        });
    }

    @Override
    protected TestBean createTestBean() {
        return injector.getInstance(TestBean.class);
    }

    @Override
    protected void destroyContainer() {
        injector = null;
    }
}
