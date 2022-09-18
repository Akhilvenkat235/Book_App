package com.cgi.favourite.service;

import com.cgi.favourite.exception.FavouriteAlreadyExistsException;
import com.cgi.favourite.exception.FavouriteNotFoundException;
import com.cgi.favourite.model.Favourite;

public interface FavouriteService {
	public Favourite addFavourite (Favourite favourite) throws FavouriteAlreadyExistsException;
	public boolean deleteFavourite (String userEmail, String isbn) throws FavouriteNotFoundException;
	public Favourite[] getAllFavourites(String userEmail);
	


}
