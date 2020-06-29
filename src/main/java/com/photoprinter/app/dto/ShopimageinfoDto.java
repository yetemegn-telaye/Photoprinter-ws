package com.photoprinter.app.dto;

import com.photoprinter.app.model.Imagesize;

import java.util.Set;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import static javax.persistence.CascadeType.ALL;
import com.google.gson.Gson;

public class ShopimageinfoDto {
	
	private String shopimageinfoId;
	
	
	private Double imagePrice;

	
	private Imagesize imageSize;

	
	private ShopDto shop;

	
	public void setShopimageinfoId(String shopimageinfoId) {
		this.shopimageinfoId = shopimageinfoId;
	}
	
	public String getShopimageinfoId() {
		return this.shopimageinfoId;
	}
	
		public void setImagePrice(Double imagePrice) {
		this.imagePrice = imagePrice;
	}

	
	public Double getImagePrice() {
		return this.imagePrice;
	}
	
	public void setImageSize(Imagesize imageSize) {
		this.imageSize = imageSize;
	}

	
	public Imagesize getImageSize() {
		return this.imageSize;
	}
	
	public void setShop(ShopDto shop) {
		this.shop = shop;
	}

	
	public ShopDto getShop() {
		return this.shop;
	}
	

	
	@Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
	
}
