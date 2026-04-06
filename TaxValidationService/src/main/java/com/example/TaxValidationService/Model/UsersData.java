package com.example.TaxValidationService.Model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UsersData {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer userD;
	private String username;
	private String password;
	private String role;
	public UsersData(Integer userD, String username, String password,String role) {
		super();
		this.userD = userD;
		this.username = username;
		this.password = new BCryptPasswordEncoder().encode(password);
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}
	
	public Integer getUserD() {
		return userD;
	}
	public void setUserD(Integer userD) {
		this.userD = userD;
	}
	public String getUsername() {
		return username;
	}
	public UsersData() {
		super();
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		// TODO Auto-generated method stub
		return this.role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
