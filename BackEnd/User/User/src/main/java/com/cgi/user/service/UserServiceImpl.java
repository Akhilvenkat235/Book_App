package com.cgi.user.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import com.cgi.user.exception.UserAllreadyExistsException;
import com.cgi.user.exception.UserNotFoundException;
import com.cgi.user.model.User;
import com.cgi.user.repository.UserRepository;

/*
* Service classes are used here to implement additional business logic/validation 
* This class has to be annotated with @Service annotation.
* @Service - It is a specialization of the component annotation. It doesn't currently 
* provide any additional behavior over the @Component annotation, but it's a good idea 
* to use @Service over @Component in service-layer classes because it specifies intent 
* better. Additionally, tool support and additional behavior might rely on it in the 
* future.
* */
@Service
@Component
public class UserServiceImpl implements UserService {
	 private  UserRepository repository;
	    
     
	    public UserServiceImpl(UserRepository repository) {
	       this.repository = repository;
	    }
	    /*
	     * This method should be used to save a new user.Call the corresponding method
	     * of Respository interface.
	     */
	    public User registerUser(User user) throws UserAllreadyExistsException {
	        if(repository.existsById(user.getUserEmail()))
	            throw new UserAllreadyExistsException("User Already Exists");
	        else
	        {
	        
	            User newUser=repository.save(user);
	            if(newUser!=null)
	            return newUser;
	            else
	            throw new UserAllreadyExistsException("User Already Exists");
	        }
	    }
	    
	    /*
	     * This method should be used to update a existing user.Call the corresponding
	     * method of Respository interface.
	     */
	    public User updateUser(String email, User user) throws UserNotFoundException {
	        if (repository.findById(email).get() == null) {
	            throw new UserNotFoundException("user is not found");
	        }
	        repository.save(user);
			return  repository.findById(email).get();
	    }
	    
	    
	    public boolean deleteUser(String email) throws UserNotFoundException {
	    	try {
				repository.deleteById(email);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new UserNotFoundException("user is not found");
			}
		
		return true;
		}
	    

	    public User getUserByEmailId(String email) throws UserNotFoundException {
	    	User user=repository.findById(email).get();
			if(user == null) {
				throw new UserNotFoundException("user not found");
			}

			return user;
		}
	    
	    
	    
	    
}
