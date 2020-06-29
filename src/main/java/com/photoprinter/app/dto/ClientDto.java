package com.photoprinter.app.dto;


import java.util.Set;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import static javax.persistence.CascadeType.ALL;
import com.google.gson.Gson;

public class ClientDto {

	private String clientId;

	private ContactDto contact;

	private String lastName;

	private UserDto user;

	private Set<OrderDto> orders;


	private String firstName;

	private Set<AddressDto> addresss;

	private String middleName;

	private Set<ImageinfoDto> imageInfos;
	
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	public String getClientId() {
		return this.clientId;
	}
	
		public void setContact(ContactDto contact) {
		this.contact = contact;
	}

	
	public ContactDto getContact() {
		return this.contact;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setUser(UserDto user) {
		this.user = user;
	}

	
	public UserDto getUser() {
		return this.user;
	}
	
	public void setOrders(Set<OrderDto> orders) {
		this.orders = orders;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public Set<OrderDto> getOrders() {
		return this.orders;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setAddresss(Set<AddressDto> addresss) {
		this.addresss = addresss;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public Set<AddressDto> getAddresss() {
		return this.addresss;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	
	public String getMiddleName() {
		return this.middleName;
	}
	
	public void setImageInfos(Set<ImageinfoDto> imageInfos) {
		this.imageInfos = imageInfos;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public Set<ImageinfoDto> getImageInfos() {
		return this.imageInfos;
	}
	

	
	@Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
	
}
