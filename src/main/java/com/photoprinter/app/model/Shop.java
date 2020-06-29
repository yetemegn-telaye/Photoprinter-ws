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
public class Shop {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String shopId;
	
	
	private String workingDays;

	
	private String name;

	
	@OneToMany(mappedBy="shop", cascade = CascadeType.ALL)
	private Set<Shopimageinfo> shopImgInfos;

	
	private LocalTime workingHrStart;


	@OneToMany(mappedBy="shop", cascade = CascadeType.ALL)
	private Set<Order> orders;

	
	@OneToMany(mappedBy="shop", cascade = CascadeType.ALL)
	private Set<Address> addresss;

	
	private Deliveryoption deliveryOpt;

	
	@OneToOne(targetEntity = Contact.class, cascade = CascadeType.ALL)
	private Contact contact;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UserId")
	@NotFound(action = NotFoundAction.IGNORE)
	private Shop user;

	
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
	
	public void setShopImgInfos(Set<Shopimageinfo> shopImgInfos) {
		this.shopImgInfos = shopImgInfos;
	}

	
	public Set<Shopimageinfo> getShopImgInfos() {
		return this.shopImgInfos;
	}
	
	public void setWorkingHrStart(LocalTime workingHrStart) {
		this.workingHrStart = workingHrStart;
	}

	
	public LocalTime getWorkingHrStart() {
		return this.workingHrStart;
	}
	
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	
	public Set<Order> getOrders() {
		return this.orders;
	}
	
	public void setAddresss(Set<Address> addresss) {
		this.addresss = addresss;
	}

	
	public Set<Address> getAddresss() {
		return this.addresss;
	}
	
	public void setDeliveryOpt(Deliveryoption deliveryOpt) {
		this.deliveryOpt = deliveryOpt;
	}

	
	public Deliveryoption getDeliveryOpt() {
		return this.deliveryOpt;
	}
	
	public void setContact(Contact contact) {
		this.contact = contact;
	}

	
	public Contact getContact() {
		return this.contact;
	}
	
	public void setWorkingHrEnd(LocalTime workingHrEnd) {
		this.workingHrEnd = workingHrEnd;
	}

	
	public LocalTime getWorkingHrEnd() {
		return this.workingHrEnd;
	}

	public Shop getUser() {
		return user;
	}

	public void setUser(Shop user) {
		this.user = user;
	}

	@Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

	
}
