package eg.dicomparison.domain;

/**
 * The 'client' bean - The DI framework will inject the members into this bean, and
 * it will create a new instance for every benchmark iteration.
 * <br/>
 * Created by IntelliJ IDEA.
 * User: josh
 * Date: 6/22/11
 * Time: 10:07 PM
 */
public interface TestBean {
    void action();
}
