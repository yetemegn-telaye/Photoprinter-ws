package com.photoprinter.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import java.util.Set;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import javax.persistence.FetchType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import com.google.gson.Gson;

@Entity
public class Address {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String addressId;
	
	
	private Integer kebele;

	
	private String city;

	
	private String country;

	
	private String streetAddress;

	
	private Integer zipCode;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ClientId")
	@NotFound(action = NotFoundAction.IGNORE)
	private Client client;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ShopId")
	@NotFound(action = NotFoundAction.IGNORE)
	private Shop shop;

	
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
	
	public void setClient(Client client) {
		this.client = client;
	}

	
	public Client getClient() {
		return this.client;
	}
	
	public void setShop(Shop shop) {
		this.shop = shop;
	}

	
	public Shop getShop() {
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
