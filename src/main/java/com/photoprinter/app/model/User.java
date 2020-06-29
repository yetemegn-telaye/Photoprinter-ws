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
public class User {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String userId;
	
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Client client;

	
	private String userName;

	
	private Userrole role;

	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private Set<Shop> shops;

	
	private String password;

	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return this.userId;
	}
	
		public void setClient(Client client) {
		this.client = client;
	}

	
	public Client getClient() {
		return this.client;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public String getUserName() {
		return this.userName;
	}
	
	public void setRole(Userrole role) {
		this.role = role;
	}

	
	public Userrole getRole() {
		return this.role;
	}
	
	public void setShops(Set<Shop> shops) {
		this.shops = shops;
	}

	
	public Set<Shop> getShops() {
		return this.shops;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getPassword() {
		return this.password;
	}
	

	
	@Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

	
}
