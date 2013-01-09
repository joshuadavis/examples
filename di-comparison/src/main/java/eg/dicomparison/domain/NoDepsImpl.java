package eg.dicomparison.domain;

/**
 * Bean with no dependencies.
 * <br/>
 * Created by IntelliJ IDEA.
 * User: josh
 * Date: 6/22/11
 * Time: 10:42 PM
 */
public class NoDepsImpl implements TestBean {
    public NoDepsImpl() {
        Counters.getInstance().incrementClient();
    }

    public void action() {
    }

    @Override
    protected void finalize() throws Throwable {
        Counters.getInstance().incrementClientFinalized();
    }
}
