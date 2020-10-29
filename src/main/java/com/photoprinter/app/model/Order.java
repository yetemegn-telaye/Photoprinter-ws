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

@Entity(name = "PrintOrder")
public class Order {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String orderId;
	
	
	private Orderstatus orderStatus;

	
	@OneToMany(mappedBy="order", cascade = CascadeType.ALL)
	private Set<Imageinfo> imageInfos;

	
	private LocalDate orderDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ShopId")
	@NotFound(action = NotFoundAction.IGNORE)
	private Shop shop;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ClientId")
	@NotFound(action = NotFoundAction.IGNORE)
	private Client client;

	
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getOrderId() {
		return this.orderId;
	}
	

	public Orderstatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Orderstatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public void setImageInfos(Set<Imageinfo> imageInfos) {
		this.imageInfos = imageInfos;
	}

	
	public Set<Imageinfo> getImageInfos() {
		return this.imageInfos;
	}
	
	

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
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
