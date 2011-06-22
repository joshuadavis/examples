package guice.comparison.model.modules;

import guice.comparison.Client;
import guice.comparison.service.MainService;
import guice.comparison.service.Service;
import guice.comparison.service.ServiceEight;
import guice.comparison.service.ServiceEleven;
import guice.comparison.service.ServiceFive;
import guice.comparison.service.ServiceFour;
import guice.comparison.service.ServiceNine;
import guice.comparison.service.ServiceOne;
import guice.comparison.service.ServiceSeven;
import guice.comparison.service.ServiceSix;
import guice.comparison.service.ServiceTen;
import guice.comparison.service.ServiceThree;
import guice.comparison.service.ServiceTwelve;
import guice.comparison.service.ServiceTwo;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class GuiceModuleSingleton extends AbstractModule{

	@Override
	protected void configure() {
		bind(Service.class).annotatedWith(Client.ClientService.class).to(MainService.class).in(Scopes.SINGLETON);
		bind(Service.class).annotatedWith(MainService.ServiceOne.class).to(ServiceOne.class).in(Scopes.SINGLETON);
		bind(Service.class).annotatedWith(MainService.ServiceTwo.class).to(ServiceTwo.class).in(Scopes.SINGLETON);
		bind(Service.class).annotatedWith(MainService.ServiceThree.class).to(ServiceThree.class).in(Scopes.SINGLETON);
		bind(Service.class).annotatedWith(ServiceOne.ServiceFour.class).to(ServiceFour.class).in(Scopes.SINGLETON);
		bind(Service.class).annotatedWith(ServiceOne.ServiceFive.class).to(ServiceFive.class).in(Scopes.SINGLETON);
		bind(Service.class).annotatedWith(ServiceOne.ServiceSix.class).to(ServiceSix.class).in(Scopes.SINGLETON);
		bind(Service.class).annotatedWith(ServiceTwo.ServiceSeven.class).to(ServiceSeven.class).in(Scopes.SINGLETON);
		bind(Service.class).annotatedWith(ServiceTwo.ServiceEight.class).to(ServiceEight.class).in(Scopes.SINGLETON);
		bind(Service.class).annotatedWith(ServiceTwo.ServiceNine.class).to(ServiceNine.class).in(Scopes.SINGLETON);
		bind(Service.class).annotatedWith(ServiceThree.ServiceTen.class).to(ServiceTen.class).in(Scopes.SINGLETON);
		bind(Service.class).annotatedWith(ServiceThree.ServiceEleven.class).to(ServiceEleven.class).in(Scopes.SINGLETON);
		bind(Service.class).annotatedWith(ServiceThree.ServiceTwelve.class).to(ServiceTwelve.class).in(Scopes.SINGLETON);
	}

}
