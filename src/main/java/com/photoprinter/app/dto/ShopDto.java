package com.photoprinter.app.dto;

import com.photoprinter.app.model.Deliveryoption;

import java.util.Set;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import static javax.persistence.CascadeType.ALL;
import com.google.gson.Gson;
import com.photoprinter.app.model.Shopimageinfo;

public class ShopDto {

	private String shopId;


	private String workingDays;


	private String name;

	private Set<ShopimageinfoDto> shopImgInfos;

	private LocalTime workingHrStart;

	private Set<OrderDto> orders;

	private Set<AddressDto> addresss;


	private Deliveryoption deliveryOpt;

	private ContactDto contact;

	private ShopDto user;


	private LocalTime workingHrEnd;

	
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	
	public String getShopId() {
		return this.shopId;
	}
	
		public void setWorkingDays(String workingDays) {
		this.workingDays = workingDays;
	}

	
	public String getWorkingDays() {
		return this.workingDays;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getName() {
		return this.name;
	}
	
	public void setShopImgInfos(Set<ShopimageinfoDto> shopImgInfos) {
		this.shopImgInfos = shopImgInfos;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public Set<ShopimageinfoDto> getShopImgInfos() {
		return this.shopImgInfos;
	}
	
	public void setWorkingHrStart(LocalTime workingHrStart) {
		this.workingHrStart = workingHrStart;
	}

	
	public LocalTime getWorkingHrStart() {
		return this.workingHrStart;
	}
	
	public void setOrders(Set<OrderDto> orders) {
		this.orders = orders;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public Set<OrderDto> getOrders() {
		return this.orders;
	}
	
	public void setAddresss(Set<AddressDto> addresss) {
		this.addresss = addresss;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public Set<AddressDto> getAddresss() {
		return this.addresss;
	}
	
	public void setDeliveryOpt(Deliveryoption deliveryOpt) {
		this.deliveryOpt = deliveryOpt;
	}

	
	public Deliveryoption getDeliveryOpt() {
		return this.deliveryOpt;
	}
	
	public void setContact(ContactDto contact) {
		this.contact = contact;
	}

	
	public ContactDto getContact() {
		return this.contact;
	}
	
	public void setWorkingHrEnd(LocalTime workingHrEnd) {
		this.workingHrEnd = workingHrEnd;
	}

	
	public LocalTime getWorkingHrEnd() {
		return this.workingHrEnd;
	}

	public ShopDto getUser() {
		return user;
	}

	public void setUser(ShopDto user) {
		this.user = user;
	}

	@Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
	
}
