package eg.erraibus.helloworld.client;

import com.google.gwt.user.client.Window;
import org.jboss.errai.ioc.client.api.EntryPoint;

import javax.annotation.PostConstruct;

/**
 * Entry point for the hello world app.
 * <br>
 * User: Josh
 * Date: 5/8/14
 * Time: 2:48 PM
 */
@EntryPoint
public class HelloWorldApp
{

    @PostConstruct
    public void onLoad()
    {
        Window.alert("Hello World!");
    }
}
