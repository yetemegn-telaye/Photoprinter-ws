package com.photoprinter.app.dto;


import java.util.Set;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import static javax.persistence.CascadeType.ALL;
import com.google.gson.Gson;

public class ContactDto {
	
	private String contactId;
	
	
	private String phoneNumber;

	
	private ClientDto client;

	
	private ShopDto shop;

	
	private String emailAddress;

	
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	
	public String getContactId() {
		return this.contactId;
	}
	
		public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public void setClient(ClientDto client) {
		this.client = client;
	}

	
	public ClientDto getClient() {
		return this.client;
	}
	
	public void setShop(ShopDto shop) {
		this.shop = shop;
	}

	
	public ShopDto getShop() {
		return this.shop;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	
	public String getEmailAddress() {
		return this.emailAddress;
	}
	

	
	@Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
	
}
