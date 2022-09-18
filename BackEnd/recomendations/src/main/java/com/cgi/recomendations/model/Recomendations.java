package com.cgi.recomendations.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Recomendations {
	
	@Id
	private int recomendId;
	private String isbn;
	private String userEmail;
	
	
	
	public Recomendations() {
		super();
	}

	public Recomendations(int recomendId, String isbn, String userEmail) {
		super();
		this.recomendId = recomendId;
		this.isbn = isbn;
		this.userEmail = userEmail;
	}



	public int getRecomendId() {
		return recomendId;
	}



	public void setRecomendId(int recomendId) {
		this.recomendId = recomendId;
	}



	public String getIsbn() {
		return isbn;
	}



	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}



	public String getUserEmail() {
		return userEmail;
	}



	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}



	@Override
	public String toString() {
		return "Recomendations [recomendId=" + recomendId + ", isbn=" + isbn + ", userEmail=" + userEmail + "]";
	}
	
	
	
	
	

}
