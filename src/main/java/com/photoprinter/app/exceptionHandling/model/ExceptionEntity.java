package com.photoprinter.app.exceptionHandling.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ExceptionEntity {
	private String userMessage;
	private String internalMessage;
	private int errorCode;
	private String moreInfoUrl;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date occuredOn;
	
	public ExceptionEntity(String userMessage, String internalMessage, int errorCode, String moreInfoUrl) {
		this.userMessage = userMessage;
		this.internalMessage = internalMessage;
		this.errorCode = errorCode;
		this.moreInfoUrl = moreInfoUrl;
		this.occuredOn = new Date();
	}

	public String getUserMessage() {
		return userMessage;
	}
	
	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
	
	public String getInternalMessage() {
		return internalMessage;
	}
	
	public void setInternalMessage(String internalMessage) {
		this.internalMessage = internalMessage;
	}
	
	public long getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getMoreInfoUrl() {
		return moreInfoUrl;
	}
	
	public void setMoreInfoUrl(String moreInfoUrl) {
		this.moreInfoUrl = moreInfoUrl;
	}
	
	public Date getOccuredOn() {
		return occuredOn;
	}
	
}
