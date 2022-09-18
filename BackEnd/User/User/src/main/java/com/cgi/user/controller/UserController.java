package com.cgi.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.user.exception.UserAllreadyExistsException;
import com.cgi.user.exception.UserNotFoundException;
import com.cgi.user.model.User;
import com.cgi.user.service.UserService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class UserController {
	@Autowired
	private UserService service;

	/*
	 * Autowiring should be implemented for the UserService. (Use Constructor-based
	 * autowiring) Please note that we should not create an object using the new
	 * keyword
	 */
	public UserController(UserService service)
	{
		this.service=service;
	}

	/*
	 * Define a handler method which will create a specific user by reading the
	 * Serialized object from request body and save the user details in the
	 * database. This handler method should return any one of the status messages
	 * basis on different situations:
	 * 1. 201(CREATED) - If the user created successfully. 
	 * 2. 409(CONFLICT) - If the userId conflicts with any existing user
	 * 
	 * This handler method should map to the URL "/user" using HTTP POST method
	 */
	@PostMapping("/api/v1/user")
	
	public ResponseEntity<?> createUser(@RequestBody User user){
		try {
			service.registerUser(user);
			return new ResponseEntity<String>("Successfully registered",HttpStatus.CREATED);
		
		} catch (UserAllreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	/*
	 * Define a handler method which will update a specific user by reading the
	 * Serialized object from request body and save the updated user details in a
	 * database. This handler method should return any one of the status messages
	 * basis on different situations: 
	 * 1. 200(OK) - If the user updated successfully.
	 * 2. 404(NOT FOUND) - If the user with specified userId is not found.
	 * 
	 * This handler method should map to the URL "/api/v1/user/{id}" using HTTP PUT method.
	 */
	@PutMapping("user/{id}")
	public ResponseEntity<?> updateUser(@PathVariable String id,@RequestBody User user,HttpSession session){
		System.out.println(id);
		System.out.println(session.getAttribute("loggedInUserId"));
		try {
			
			if(session.getAttribute("loggedInUserId")==null || !id.equals(session.getAttribute("loggedInUserId")))
			{
				
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);

			}
				if(service.updateUser(id, user) != null) {
			return new ResponseEntity<String>("Successfully updated", HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("not found", HttpStatus.NOT_FOUND);
			}
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	
	/*
	 * Define a handler method which will delete a user from a database.
	 * This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the user deleted successfully from database. 
	 * 2. 404(NOT FOUND) - If the user with specified userId is not found.
	 *
	 * This handler method should map to the URL "/api/v1/user/{id}" using HTTP Delete
	 * method" where "id" should be replaced by a valid userId without {}
	 */
	@DeleteMapping("/api/v1/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable String id){
		try {
			
			   service.deleteUser(id);
				
				return new ResponseEntity<String>("successfully updated"+id, HttpStatus.OK);
			
			
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("user/{email}")
	public ResponseEntity<?> getUserId(@PathVariable String email){
		try {
			User user=service.getUserByEmailId(email);
			if(user == null) {
				return new ResponseEntity<String>("not found", HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>("not found", HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	
	
}
