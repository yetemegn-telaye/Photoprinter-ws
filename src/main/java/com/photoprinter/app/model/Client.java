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
public class Client {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String clientId;
	
	
	@OneToOne(targetEntity = Contact.class, cascade = CascadeType.ALL)
	private Contact contact;

	
	private String lastName;

	
	@OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
	private User user;

	
	@OneToMany(mappedBy="client", cascade = CascadeType.ALL)
	private Set<Order> orders;

	
	private String firstName;

	
	@OneToMany(mappedBy="client", cascade = CascadeType.ALL)
	private Set<Address> addresss;

	
	private String middleName;

	
	@OneToMany(mappedBy="client", cascade = CascadeType.ALL)
	private Set<Imageinfo> imageInfos;

	
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	public String getClientId() {
		return this.clientId;
	}
	
		public void setContact(Contact contact) {
		this.contact = contact;
	}

	
	public Contact getContact() {
		return this.contact;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	
	public User getUser() {
		return this.user;
	}
	
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	
	public Set<Order> getOrders() {
		return this.orders;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setAddresss(Set<Address> addresss) {
		this.addresss = addresss;
	}

	
	public Set<Address> getAddresss() {
		return this.addresss;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	
	public String getMiddleName() {
		return this.middleName;
	}
	
	public void setImageInfos(Set<Imageinfo> imageInfos) {
		this.imageInfos = imageInfos;
	}

	
	public Set<Imageinfo> getImageInfos() {
		return this.imageInfos;
	}
	

	
	@Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

	
}
