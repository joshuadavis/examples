package guice.comparison;

import guice.comparison.service.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class StopWatch {
	private static final List<Service> serviceInstances = new LinkedList<Service>();
	
	/**
	 * @return the serviceInstances
	 */
	public List<Service> getServiceInstances() {
		return serviceInstances;
	}
	
	public static void recordInstance(Service service){
		serviceInstances.add(service);
	}
	
	public static void clear(){
		serviceInstances.clear();
	}
	
	public static long averageInstanceCreationInMs(){
		long total = 0;
		Date date = null;
		for (Service service : serviceInstances) {
			if(date == null){
				date = service.getCreatedOn();
			}else{
				total = total + (service.getCreatedOn().getTime() - date.getTime());
			}
		}
		return total/serviceInstances.size();
	}

	public static int totalInstance() {
		return serviceInstances.size();
	}
}
