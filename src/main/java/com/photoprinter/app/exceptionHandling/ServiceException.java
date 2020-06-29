package com.photoprinter.app.exceptionHandling;


public class ServiceException extends WebserviceException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 0L;

	public ServiceException(WebserviceException ex){
		super(ex);
	}
	
	
}
