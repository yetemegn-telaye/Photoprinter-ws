package com.photoprinter.app.exceptionHandling;


public class DuplicateResourceException extends WebserviceException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 0L;
	private String message;
	private static final String DUPLICATE_MSG = " Already Exists";

	/*public DuplicateResourceException() {
	}*/
	
	@SuppressWarnings("rawtypes")
	public DuplicateResourceException(Class targetClass) {
		if(targetClass != null){
			this.message = targetClass.getSimpleName() + DUPLICATE_MSG;
		}	
	}

	public DuplicateResourceException(String errorMessage) {
		this.message = errorMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String errorMessage) {
		this.message = errorMessage;
	}
}
