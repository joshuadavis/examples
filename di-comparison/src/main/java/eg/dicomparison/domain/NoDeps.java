package eg.dicomparison.domain;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

/**
 * TODO: Add class level comments
 * <br/>
 * Created by IntelliJ IDEA.
 * User: josh
 * Date: 6/22/11
 * Time: 11:51 PM
 */
@Qualifier
@Target({TYPE, METHOD, CONSTRUCTOR, FIELD})
@Retention(value = RUNTIME)
public @interface NoDeps {
}
