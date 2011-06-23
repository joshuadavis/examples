package eg.dicomparison.containers.spring;

import eg.dicomparison.AbstractContainerBenchmark;
import eg.dicomparison.domain.TestBean;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Simple benchmark with no dependency injection in the client object.
 * <br/>
 * Created by IntelliJ IDEA.
 * User: josh
 * Date: 6/22/11
 * Time: 10:59 PM
 */
@SuppressWarnings({"UnusedDeclaration"})
public class SpringNoDeps extends AbstractContainerBenchmark {
    private BeanFactory beanFactory;

    @Override
    protected void setUpContainer() {
        beanFactory = new XmlBeanFactory(new ClassPathResource("spring-nodeps.xml"));
    }

    @Override
    protected TestBean createTestBean() {
        return (TestBean) beanFactory.getBean("testBean");
    }

    @Override
    protected void destroyContainer() {
        beanFactory = null;
    }
}
