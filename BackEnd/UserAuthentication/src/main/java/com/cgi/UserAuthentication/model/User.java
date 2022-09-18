package com.cgi.UserAuthentication.model;

import javax.persistence.*;

import org.springframework.stereotype.Component;

@Entity
@Component
public class User {
	
	
	
	private String userName;
	@Id
	@Column(length =50 )
	private String userEmail;
	private String userPassword;
	
	
	public User() {
		super();
	}
	
	public User(String userName, String userEmail, String userPassword) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", userEmail=" + userEmail + ", userPassword=" + userPassword + "]";
	}
	
	
	
	
	
}
