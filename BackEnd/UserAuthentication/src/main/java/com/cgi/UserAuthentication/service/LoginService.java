package com.cgi.UserAuthentication.service;

import com.cgi.UserAuthentication.exception.UserAlreadyExistsException;
import com.cgi.UserAuthentication.exception.UserNotFoundException;
import com.cgi.UserAuthentication.model.User;

public interface LoginService  {
	
	public User findByUserEmailAndUserPassword(String userEmail, String userPassword) throws UserNotFoundException;

    boolean saveUser(User user) throws UserAlreadyExistsException;

}
