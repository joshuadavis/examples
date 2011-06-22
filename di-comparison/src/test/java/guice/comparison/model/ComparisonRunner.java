package guice.comparison.model;

import guice.comparison.Client;
import guice.comparison.StopWatch;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.BeanFactory;

import com.google.inject.Injector;

public class ComparisonRunner {
	public static void main(String[] args) throws IOException{
		boolean singleton = false;
		boolean callMethod = true;
			int client = 1;
			StopWatch.clear();
			long loadModule = springClientRequest(client,singleton,callMethod);
			System.out.println("Spring\t "+client+"\t"+singleton+"\t"+callMethod+"\t"+StopWatch.averageInstanceCreationInMs()+"\t"+StopWatch.totalInstance()+"\t"+loadModule);
			
			StopWatch.clear();
			loadModule = guiceClientRequest(client,singleton,callMethod);
			System.out.println("Guice\t "+client+"\t"+singleton+"\t"+callMethod+"\t"+StopWatch.averageInstanceCreationInMs()+"\t"+StopWatch.totalInstance()+"\t"+loadModule);
	}

	private static long springClientRequest(int i,boolean singleton,boolean callMethod) throws IOException {
		List<String> clients = createClients(i);
		long loadModule = 0;
		Date date = new Date();
		SpringFactory springFactory = new SpringFactory();
		BeanFactory factory = springFactory.craeteModules(singleton, clients);
		Date finishedLoadModule = new Date();
		loadModule = finishedLoadModule.getTime() -date.getTime(); 
		callingBean(factory,i,callMethod);
		return loadModule;
	}
	
	private static long guiceClientRequest(int i,boolean singleton,boolean callMethod) throws IOException {
		long loadModule = 0;
		Date date = new Date();
		GuiceInjector guiceInjector = new GuiceInjector();
		Injector injector = guiceInjector.craeteModules(singleton);
		Date finishedLoadModule = new Date();
			callingBean(injector,i,callMethod);
			loadModule = finishedLoadModule.getTime() -date.getTime();
			return loadModule;
	}

	private static void callingBean(Injector guiceInjector, int totals, boolean callMethod) {
		for(int i = 0;i<totals;i++){
			Client client = new Client();
			guiceInjector.injectMembers(client);
			if(callMethod){
			client.doService();
			}
		}
	}

	private static void callingBean(BeanFactory factory, int totals,boolean callMethod) {
		for(int i = 0;i<totals;i++){
			Client client = (Client) factory.getBean(""+i);
			if(callMethod){
			client.doService();
			}
		}
	}

	private static List<String> createClients(int totals) {
		List<String> clients = new LinkedList<String>();
		for(int i=0;i<totals;i++){
			clients.add(""+i);
		}
		return clients;
	}
}
