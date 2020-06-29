package com.photoprinter.app.dto;

import com.photoprinter.app.model.Userrole;

import java.util.Set;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import static javax.persistence.CascadeType.ALL;
import com.google.gson.Gson;

public class UserDto {
	
	private String userId;
	
	
	private ClientDto client;

	
	private String userName;

	
	private Userrole role;

	
	private Set<ShopDto> shops;

	
	private String password;

	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return this.userId;
	}
	
		public void setClient(ClientDto client) {
		this.client = client;
	}

	
	public ClientDto getClient() {
		return this.client;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public String getUserName() {
		return this.userName;
	}
	
	public void setRole(Userrole role) {
		this.role = role;
	}

	
	public Userrole getRole() {
		return this.role;
	}
	
	public void setShops(Set<ShopDto> shops) {
		this.shops = shops;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public Set<ShopDto> getShops() {
		return this.shops;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getPassword() {
		return this.password;
	}
	

	
	@Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
	
}
