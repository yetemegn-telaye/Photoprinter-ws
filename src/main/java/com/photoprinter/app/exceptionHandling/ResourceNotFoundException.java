package com.photoprinter.app.exceptionHandling;


public class ResourceNotFoundException extends WebserviceException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 0L;
	private String message;
	private static final String RESOURCE_NOT_FOUND_MSG = " is not Found";

	/*public ResourceNotFoundException() {
		
	}*/
	
	public ResourceNotFoundException(String message) {
		this.message = message;
	}
	
	@SuppressWarnings("rawtypes")
	public ResourceNotFoundException(Class targetClass) {
		if(targetClass != null){
			this.message = targetClass.getSimpleName() + RESOURCE_NOT_FOUND_MSG;
		}	
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
