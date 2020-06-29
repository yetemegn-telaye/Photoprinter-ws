package com.photoprinter.app.dto;


import java.util.Set;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import static javax.persistence.CascadeType.ALL;
import com.google.gson.Gson;

public class AddressDto {

	private String addressId;


	private Integer kebele;


	private String city;


	private String country;


	private String streetAddress;


	private Integer zipCode;



	private ClientDto client;

	private ShopDto shop;


	private Integer woreda;


	private String location;
	
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	
	public String getAddressId() {
		return this.addressId;
	}
	
		public void setKebele(Integer kebele) {
		this.kebele = kebele;
	}

	
	public Integer getKebele() {
		return this.kebele;
	}
	
	public void setCity(String city) {
		this.city = city;
	}

	
	public String getCity() {
		return this.city;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}

	
	public String getCountry() {
		return this.country;
	}
	
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	
	public String getStreetAddress() {
		return this.streetAddress;
	}
	
	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	
	public Integer getZipCode() {
		return this.zipCode;
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
	
	public void setWoreda(Integer woreda) {
		this.woreda = woreda;
	}

	
	public Integer getWoreda() {
		return this.woreda;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}

	
	public String getLocation() {
		return this.location;
	}
	

	
	@Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
	
}
