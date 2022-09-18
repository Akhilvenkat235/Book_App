package com.cgi.user.exception;

public class UserAllreadyExistsException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserAllreadyExistsException(String message) {
        super(message);
    }
}
