package com.cgi.UserAuthentication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.UserAuthentication.exception.UserAlreadyExistsException;
import com.cgi.UserAuthentication.exception.UserNotFoundException;
import com.cgi.UserAuthentication.model.User;
import com.cgi.UserAuthentication.repository.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginRepository repository;

//to validate EmailId and password
	
	@Override
	public User findByUserEmailAndUserPassword(String userEmail, String userPassword) throws UserNotFoundException {
		
		User user=null;
		try {
			user = repository.findById(userEmail).get();
			if(user==null || !user.getUserPassword().equals(userPassword))
				
				throw new UserNotFoundException("emailId and password mismatch");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserNotFoundException("emailId does not exist");
		}
	
		return user;
	}
	//to save new user

	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException {
		
		Optional<User> userr = repository.findById(user.getUserEmail());
		if (userr.isPresent())
			throw new UserAlreadyExistsException("User with emailId already exists");

		repository.save(user);
		return true;
	}
	}
		
		
		
	


