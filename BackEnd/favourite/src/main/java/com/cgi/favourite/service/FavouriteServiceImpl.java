package com.cgi.favourite.service;

import org.springframework.stereotype.Service;

import com.cgi.favourite.exception.FavouriteAlreadyExistsException;
import com.cgi.favourite.exception.FavouriteNotFoundException;
import com.cgi.favourite.model.Favourite;
import com.cgi.favourite.repository.FavouriteRepository;
@Service
public class FavouriteServiceImpl implements FavouriteService {
	private FavouriteRepository favouriteRepository;

	public FavouriteServiceImpl(FavouriteRepository favouriteRepository) {
		super();
		this.favouriteRepository = favouriteRepository;
	}

	@Override
	public Favourite addFavourite(Favourite favourite) throws FavouriteAlreadyExistsException {
		// TODO Auto-generated method stub
		System.out.println(favourite);
		if (favouriteRepository.findOneByIsbnAndUserEmail(favourite.getIsbn(), favourite.getUserEmail()).isPresent()) {
			throw new FavouriteAlreadyExistsException("favourite already exists");
		}
		return favouriteRepository.insert(favourite);
	}

	
	
	public boolean deleteFavourite(String userEmail, String isbn) throws FavouriteNotFoundException {
			
		
			System.out.println(userEmail+":"+isbn);	
		if(	favouriteRepository.deleteByIsbnAndUserEmail(isbn, userEmail)==0)
			{
			throw new FavouriteNotFoundException("favourite not found");
			
			};			
			
			return true;
		}
	

	@Override
	public Favourite[] getAllFavourites(String userEmail) {
		// TODO Auto-generated method stub
		return favouriteRepository.findAllFavouritesByUserEmail(userEmail);
	}
	



}




