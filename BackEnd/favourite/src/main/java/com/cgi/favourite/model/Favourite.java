package com.cgi.favourite.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Favourite {
	@Id
	private int favouriteId;
	private String isbn;
	private String userEmail;
	private String author_name;
	private String title_name;
	
	public Favourite() {
		super();
	}
	public Favourite(int favouriteId, String isbn, String userEmail, String author_name, String title_name) {
		super();
		this.favouriteId = favouriteId;
		this.isbn = isbn;
		this.userEmail = userEmail;
		this.author_name = author_name;
		this.title_name = title_name;
	}
	public int getFavouriteId() {
		return favouriteId;
	}
	public void setFavouriteId(int favouriteId) {
		this.favouriteId = favouriteId;
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
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public String getTitle_name() {
		return title_name;
	}
	public void setTitle_name(String title_name) {
		this.title_name = title_name;
	}
	@Override
	public String toString() {
		return "Favourite [favouriteId=" + favouriteId + ", isbn=" + isbn + ", userEmail=" + userEmail
				+ ", author_name=" + author_name + ", title_name=" + title_name + "]";
	}
	
}