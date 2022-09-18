package com.cgi.user.service;


import com.cgi.user.exception.UserAllreadyExistsException;
import com.cgi.user.exception.UserNotFoundException;
import com.cgi.user.model.User;

public interface UserService {
	/*
	 * Should not modify this interface. You have to implement these methods in
	 * corresponding Impl classes
	 */

	 User registerUser(User user) throws UserAllreadyExistsException;

	    User updateUser(String email,User user) throws UserNotFoundException;

	    boolean deleteUser(String email) throws UserNotFoundException;


	    User getUserByEmailId(String email) throws UserNotFoundException;
}
