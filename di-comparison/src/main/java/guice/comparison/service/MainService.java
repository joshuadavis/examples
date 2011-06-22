package guice.comparison.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.inject.BindingAnnotation;
import com.google.inject.Inject;

public class MainService extends AbstractService implements Service {
	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	@BindingAnnotation
	public @interface ServiceOne {
	}

	@Inject
	@ServiceOne
	private Service serviceOne;

	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	@BindingAnnotation
	public @interface ServiceTwo {
	}

	@Inject
	@ServiceTwo
	private Service serviceTwo;

	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	@BindingAnnotation
	public @interface ServiceThree {
	}

	@Inject
	@ServiceThree
	private Service serviceThree;

	public void doService() {
		serviceOne.doService();
		serviceTwo.doService();
		serviceThree.doService();
	}

	/**
	 * @return the serviceOne
	 */
	public Service getServiceOne() {
		return serviceOne;
	}

	/**
	 * @param serviceOne
	 *            the serviceOne to set
	 */
	public void setServiceOne(Service serviceOne) {
		this.serviceOne = serviceOne;
	}

	/**
	 * @return the serviceThree
	 */
	public Service getServiceThree() {
		return serviceThree;
	}

	/**
	 * @param serviceThree
	 *            the serviceThree to set
	 */
	public void setServiceThree(Service serviceThree) {
		this.serviceThree = serviceThree;
	}

	/**
	 * @return the serviceTwo
	 */
	public Service getServiceTwo() {
		return serviceTwo;
	}

	/**
	 * @param serviceTwo
	 *            the serviceTwo to set
	 */
	public void setServiceTwo(Service serviceTwo) {
		this.serviceTwo = serviceTwo;
	}

}
