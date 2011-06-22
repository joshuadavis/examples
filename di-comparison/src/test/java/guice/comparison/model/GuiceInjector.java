package guice.comparison.model;

import guice.comparison.model.modules.GuiceModulePrototype;
import guice.comparison.model.modules.GuiceModuleSingleton;

import java.io.IOException;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;

public class GuiceInjector {
	public Injector craeteModules(boolean singleton) throws IOException {
		return Guice.createInjector(Stage.PRODUCTION,(singleton) ? new GuiceModuleSingleton()
				: new GuiceModulePrototype());
		
	}
}
