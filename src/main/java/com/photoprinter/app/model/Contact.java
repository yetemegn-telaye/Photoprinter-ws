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
public class Contact {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String contactId;
	
	
	private String phoneNumber;

	
	@OneToOne(mappedBy = "contact", cascade = CascadeType.ALL)
	private Client client;

	
	@OneToOne(mappedBy = "contact", cascade = CascadeType.ALL)
	private Shop shop;

	
	private String emailAddress;

	
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	
	public String getContactId() {
		return this.contactId;
	}
	
		public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	public String getPhoneNumber() {
		return this.phoneNumber;
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
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	
	public String getEmailAddress() {
		return this.emailAddress;
	}
	

	
	@Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

	
}
