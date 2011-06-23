package eg.dicomparison.domain;


import com.google.inject.Inject;

/**
 * Test bean with four injected services.
 * <br/>
 * Created by IntelliJ IDEA.
 * User: josh
 * Date: 6/22/11
 * Time: 11:07 PM
 */
public class FourServiceImpl implements TestBean {
    private ServiceOne one;
    private ServiceTwo two;
    private ServiceThree three;
    private ServiceFour four;

    @Inject
    public FourServiceImpl(ServiceOne one, ServiceTwo two, ServiceThree three, ServiceFour four) {
        this.one = one;
        this.two = two;
        this.three = three;
        this.four = four;
        Counters.getInstance().incrementClient();
    }

    public void action() {
        assert one != null;
        assert two != null;
        assert three != null;
        assert four != null;
    }
}
