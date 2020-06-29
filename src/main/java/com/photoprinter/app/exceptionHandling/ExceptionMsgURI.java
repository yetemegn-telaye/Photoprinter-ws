package com.photoprinter.app.exceptionHandling;


public enum ExceptionMsgURI {
	DUPLICATE_RESOURCE("http://app-ws/WebserviceException/DuplicateResourceException"),
	MALFORMED_REQUEST("http://app-ws/WebserviceException/MalformedRequestException"),
	RESOURCE_NOT_FOUND("http://app-ws/WebserviceException/RequestNotFoundException"),
	DATA_ACCESS_VIOLATION("http://app-ws/WebserviceException/DataAccessException"),
	SELFLOOK_WEBSERVICE_EXECETION("http://app-ws/WebserviceException"),
	UNKOWON_EXCEPTION("https://docs.oracle.com/javase/8/docs/api/java/lang/Exception.html");
	
	private final String msg;

	private ExceptionMsgURI(String msg) {
		this.msg = msg;
	}
	
	 public String getValue() { return msg; }
}
