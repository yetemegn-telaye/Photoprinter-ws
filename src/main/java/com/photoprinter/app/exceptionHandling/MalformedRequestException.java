package com.photoprinter.app.exceptionHandling;


public class MalformedRequestException extends WebserviceException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 0L;
	private String message;
	private static final String MALFORMED_MSG = " is Expected";
	

	public MalformedRequestException(String expectedData) {
		if(expectedData != null){
			this.message = expectedData + MALFORMED_MSG;
		}	
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}

}
