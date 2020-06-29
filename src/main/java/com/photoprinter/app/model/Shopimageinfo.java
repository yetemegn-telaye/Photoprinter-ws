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
public class Shopimageinfo {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String shopimageinfoId;
	
	
	private Double imagePrice;

	
	private Imagesize imageSize;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ShopId")
	@NotFound(action = NotFoundAction.IGNORE)
	private Shop shop;

	
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
	
	public void setShop(Shop shop) {
		this.shop = shop;
	}

	
	public Shop getShop() {
		return this.shop;
	}
	

	
	@Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

	
}
