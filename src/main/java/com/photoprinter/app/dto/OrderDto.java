package com.photoprinter.app.dto;


import com.photoprinter.app.model.Imageinfo;
import com.photoprinter.app.model.Orderstatus;
import java.util.Set;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;


import javax.persistence.*;

public class OrderDto {

	private String orderId;


	private Orderstatus status;

	private Set<ImageinfoDto> imageInfos;

	private LocalDate date;

	private ShopDto shop;

	private ClientDto client;

	
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getOrderId() {
		return this.orderId;
	}
	
		public void setStatus(Orderstatus status) {
		this.status = status;
	}

	
	public Orderstatus getStatus() {
		return this.status;
	}
	
	public void setImageInfos(Set<ImageinfoDto> imageInfos) {
		this.imageInfos = imageInfos;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public Set<ImageinfoDto> getImageInfos() {
		return this.imageInfos;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}

	
	public LocalDate getDate() {
		return this.date;
	}

	public ShopDto getShop() {
		return shop;
	}

	public void setShop(ShopDto shop) {
		this.shop = shop;
	}

	public ClientDto getClient() {
		return client;
	}

	public void setClient(ClientDto client) {
		this.client = client;
	}

	@Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
	
}
