package guice.comparison.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.inject.BindingAnnotation;
import com.google.inject.Inject;

public class ServiceOne extends AbstractService implements Service {
	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	@BindingAnnotation
	public @interface ServiceFour {
	}

	@Inject
	@ServiceFour
	private Service serviceFour;

	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	@BindingAnnotation
	public @interface ServiceFive {
	}

	@Inject
	@ServiceFive
	private Service serviceFive;

	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	@BindingAnnotation
	public @interface ServiceSix {
	}

	@Inject
	@ServiceSix
	private Service serviceSix;

	public void doService() {
		serviceFour.doService();
		serviceFive.doService();
		serviceSix.doService();
	}

	/**
	 * @return the serviceFive
	 */
	public Service getServiceFive() {
		return serviceFive;
	}

	/**
	 * @param serviceFive
	 *            the serviceFive to set
	 */
	public void setServiceFive(Service serviceFive) {
		this.serviceFive = serviceFive;
	}

	/**
	 * @return the serviceFour
	 */
	public Service getServiceFour() {
		return serviceFour;
	}

	/**
	 * @param serviceFour
	 *            the serviceFour to set
	 */
	public void setServiceFour(Service serviceFour) {
		this.serviceFour = serviceFour;
	}

	/**
	 * @return the serviceSix
	 */
	public Service getServiceSix() {
		return serviceSix;
	}

	/**
	 * @param serviceSix
	 *            the serviceSix to set
	 */
	public void setServiceSix(Service serviceSix) {
		this.serviceSix = serviceSix;
	}
}
