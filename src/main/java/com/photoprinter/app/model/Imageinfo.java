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
public class Imageinfo {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String imageinfoId;
	
	
	private Imagesize imageSize;

	
	private String image;

	
	private Integer count;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OrderId")
	@NotFound(action = NotFoundAction.IGNORE)
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ClientId")
	@NotFound(action = NotFoundAction.IGNORE)
	private Client client;

	
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
	
	public void setOrder(Order order) {
		this.order = order;
	}

	
	public Order getOrder() {
		return this.order;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

	
}
