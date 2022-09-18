package com.cgi.favourite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.favourite.exception.FavouriteAlreadyExistsException;
import com.cgi.favourite.exception.FavouriteNotFoundException;
import com.cgi.favourite.model.Favourite;
import com.cgi.favourite.service.FavouriteService;


@RestController
@CrossOrigin("*" )


public class FavouriteController {
	@Autowired
	private FavouriteService favouriteService;

	public FavouriteController(FavouriteService favouriteService) {
		super();
		this.favouriteService = favouriteService;
	}
	//@Autowired
	//private HttpSession httpSession;
	
	
@PostMapping("/api/v1/favourite")
	public ResponseEntity<String> addFavourite(@RequestBody Favourite favourite)
	{
	System.out.println("favourite "+favourite);
		try {
			favouriteService.addFavourite(favourite);
			System.out.println("favourite added");
			return new ResponseEntity<String>("favourite added", HttpStatus.CREATED);
		} catch (FavouriteAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		
	}
	
	@DeleteMapping("/api/v1/favourite/{userEmail}/{isbn}")
	public ResponseEntity<String> deleteFavourite(@PathVariable String userEmail, @PathVariable String isbn)
	{
		//if(httpSession.getAttribute("loggedInUserId").equals(userEmail))
	//{
			try {
				favouriteService.deleteFavourite(userEmail,isbn);
				return new ResponseEntity<String>("deleted successfuly", HttpStatus.OK);
			} catch (FavouriteNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
			}
		//}
			//return new ResponseEntity<String>("you are unauthorized to delete",HttpStatus.CONFLICT);
		
		
	}
	
	@GetMapping("/api/v1/favourite/{userEmail}")
	public ResponseEntity<?> getAllFavourites(@PathVariable String userEmail){
		Favourite[] favourite=favouriteService.getAllFavourites(userEmail);
		if(favourite!=null)
		{
			return new ResponseEntity<Favourite[]>(favourite,HttpStatus.OK);
		}
		return new ResponseEntity<String>("no favourites available",HttpStatus.NOT_FOUND);
		
	}
	


	
}



