package guice.comparison.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.inject.BindingAnnotation;
import com.google.inject.Inject;

public class ServiceThree extends AbstractService implements Service {
	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	@BindingAnnotation
	public @interface ServiceTen {
	}

	@Inject
	@ServiceTen
	private Service serviceTen;

	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	@BindingAnnotation
	public @interface ServiceEleven {
	}

	@Inject
	@ServiceEleven
	private Service serviceEleven;

	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	@BindingAnnotation
	public @interface ServiceTwelve {
	}

	@Inject
	@ServiceTwelve
	private Service serviceTwelve;

	public void doService() {
		serviceTen.doService();
		serviceEleven.doService();
		serviceTwelve.doService();
	}

	/**
	 * @return the serviceEleven
	 */
	public Service getServiceEleven() {
		return serviceEleven;
	}

	/**
	 * @param serviceEleven
	 *            the serviceEleven to set
	 */
	public void setServiceEleven(Service serviceEleven) {
		this.serviceEleven = serviceEleven;
	}

	/**
	 * @return the serviceTen
	 */
	public Service getServiceTen() {
		return serviceTen;
	}

	/**
	 * @param serviceTen
	 *            the serviceTen to set
	 */
	public void setServiceTen(Service serviceTen) {
		this.serviceTen = serviceTen;
	}

	/**
	 * @return the serviceTwelve
	 */
	public Service getServiceTwelve() {
		return serviceTwelve;
	}

	/**
	 * @param serviceTwelve
	 *            the serviceTwelve to set
	 */
	public void setServiceTwelve(Service serviceTwelve) {
		this.serviceTwelve = serviceTwelve;
	}

}
