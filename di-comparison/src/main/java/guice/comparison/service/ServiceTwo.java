package guice.comparison.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.inject.BindingAnnotation;
import com.google.inject.Inject;

public class ServiceTwo extends AbstractService implements Service {
	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	@BindingAnnotation
	public @interface ServiceSeven {
	}

	@Inject
	@ServiceSeven
	private Service serviceSeven;

	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	@BindingAnnotation
	public @interface ServiceEight {
	}

	@Inject
	@ServiceEight
	private Service serviceEight;

	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	@BindingAnnotation
	public @interface ServiceNine {
	}

	@Inject
	@ServiceNine
	private Service serviceNine;

	public void doService() {
		serviceSeven.doService();
		serviceEight.doService();
		serviceNine.doService();
	}

	/**
	 * @return the serviceEight
	 */
	public Service getServiceEight() {
		return serviceEight;
	}

	/**
	 * @param serviceEight
	 *            the serviceEight to set
	 */
	public void setServiceEight(Service serviceEight) {
		this.serviceEight = serviceEight;
	}

	/**
	 * @return the serviceNine
	 */
	public Service getServiceNine() {
		return serviceNine;
	}

	/**
	 * @param serviceNine
	 *            the serviceNine to set
	 */
	public void setServiceNine(Service serviceNine) {
		this.serviceNine = serviceNine;
	}

	/**
	 * @return the serviceSeven
	 */
	public Service getServiceSeven() {
		return serviceSeven;
	}

	/**
	 * @param serviceSeven
	 *            the serviceSeven to set
	 */
	public void setServiceSeven(Service serviceSeven) {
		this.serviceSeven = serviceSeven;
	}

}
