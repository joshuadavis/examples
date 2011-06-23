package eg.dicomparison.domain;

/**
 * TODO: Add class level comments
 * <br/>
 * Created by IntelliJ IDEA.
 * User: josh
 * Date: 6/22/11
 * Time: 11:12 PM
 */
public class ServiceThreeImpl implements ServiceThree {
    public ServiceThreeImpl() {
        Counters.getInstance().incrementService();
    }
}
