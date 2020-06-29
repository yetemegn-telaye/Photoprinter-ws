package com.photoprinter.app.exceptionHandling;


public abstract class WebserviceException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 0L;

	public WebserviceException() {
		
	}
	
	public WebserviceException(Exception ex) {
		super(ex.getMessage(), ex);
	}
}
