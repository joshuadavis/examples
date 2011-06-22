package guice.comparison.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

public class SpringFactory {
	public BeanFactory craeteModules(boolean singleton, List<String> clientNames)
			throws IOException {
		clientsToCreate(clientNames);
		BeanFactory serviceFactory = new XmlBeanFactory(new ClassPathResource(
				(singleton) ? SPRING_MODULE_SINGLETON : SPRING_MODULE_PROTYPE));
		BeanFactory clientFactory = new XmlBeanFactory(new ClassPathResource(
				FILE_NAME), serviceFactory);
		return clientFactory;
	}

	public static final String FILE_NAME = "spring-module-client.xml";

	public static final String SPRING_MODULE_SINGLETON = "spring-module-singleton.xml";

	public static final String SPRING_MODULE_PROTYPE = "spring-module-prototype.xml";

	private void clientsToCreate(List<String> names) throws IOException {
		File file = new File(FILE_NAME);
		if (file.exists()) {
			file.delete();
		}
		file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		writer.write("<!DOCTYPE beans PUBLIC \"-//SPRING//DTD BEAN//EN\" \"http://www.springframework.org/dtd/spring-beans.dtd\">");
		writer.write("<beans>");
		for (String string : names) {
			writeBean(writer, string);
		}
		writer.write("</beans>");
		writer.flush();
	}

	private void writeBean(BufferedWriter writer, String string) throws IOException {
		writer.write("<bean name=\"" + string
				+ "\" class=\"guice.comparison.Client\" singleton=\"false\">"
				+ "\n");
		writer
				.write("<property name=\"service\"><ref bean=\"mainService\"/></property>"
						+ "\n");
		writer
		.write("<property name=\"name\"><value>"+string+"</value></property>"
				+ "\n");
		writer.write("</bean>" + "\n");

	}
}
