package com.cgi.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Entity
@Component
public class User {
	
	@Id
	@Column(length =50 )
	private String userEmail;
	private String userName;
	private String userPassword;
	
	public User()
    {
		
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String email) {
		this.userEmail = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public User(String userEmail, String userName, String userPassword) {
		super();
		this.userEmail = userEmail;
		this.userName = userName;
		this.userPassword = userPassword;
	}
	@Override
	public String toString() {
		return "user [email=" + userEmail + ", userName=" + userName + ", userPassword=" + userPassword + "]";
	}
	
	
}
