package com.photoprinter.app.dto;

import com.photoprinter.app.model.Imagesize;

import java.util.Set;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import static javax.persistence.CascadeType.ALL;
import com.google.gson.Gson;

public class ImageinfoDto {

	private String imageinfoId;


	private Imagesize imageSize;


	private String image;


	private Integer count;

	private OrderDto order;

	private ClientDto client;

	
	public void setImageinfoId(String imageinfoId) {
		this.imageinfoId = imageinfoId;
	}
	
	public String getImageinfoId() {
		return this.imageinfoId;
	}
	
		public void setImageSize(Imagesize imageSize) {
		this.imageSize = imageSize;
	}

	
	public Imagesize getImageSize() {
		return this.imageSize;
	}
	
	public void setImage(String image) {
		this.image = image;
	}

	
	public String getImage() {
		return this.image;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}

	
	public Integer getCount() {
		return this.count;
	}
	
	public void setOrder(OrderDto order) {
		this.order = order;
	}

	
	public OrderDto getOrder() {
		return this.order;
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
