package guice.comparison.service;

import guice.comparison.StopWatch;

import java.util.Date;

public abstract class AbstractService implements Service{
	private Date createdOn;
	public AbstractService(){
		createdOn = new Date();
		StopWatch.recordInstance(this);
	}
	
	/**
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}
}