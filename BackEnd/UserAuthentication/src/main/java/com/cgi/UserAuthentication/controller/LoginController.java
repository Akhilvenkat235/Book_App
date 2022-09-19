package com.cgi.UserAuthentication.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.UserAuthentication.exception.UserAlreadyExistsException;
import com.cgi.UserAuthentication.model.User;
import com.cgi.UserAuthentication.service.LoginService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin(origins="http://localhost:4200" )
@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;

    public LoginController(LoginService loginService) {
    	this.loginService=loginService;
	}
    
    //to register new user
    
    @PostMapping("/api/v1/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        try {

        	loginService.saveUser(user);
			System.out.println("user is"+user);
            return new ResponseEntity<String>("User registered successfully", HttpStatus.CREATED);


        } catch (UserAlreadyExistsException exception) {
        	
        	 return new ResponseEntity<String>("Cannot Registe User", HttpStatus.CONFLICT);
        }
    }


	//to login existing user

    @PostMapping("/api/v1/auth/login")
    public ResponseEntity loginUser(@RequestBody User user,HttpSession session) {
    	System.out.println(user);
            
			try {
				loginService.findByUserEmailAndUserPassword(user.getUserEmail(), user.getUserPassword());
				String token =getToken(user.getUserEmail(), user.getUserPassword());
				Map<String,String> map=new HashMap<>();
		        map.put("token", token);
		       session.setAttribute("loggedInUserId", user);
		        
		        return new ResponseEntity<Map<String,String>> (map,HttpStatus.OK);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return new ResponseEntity<String> (e.getMessage(),HttpStatus.UNAUTHORIZED);
			}
            
    }
    
    //to user logout
    
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session ) {
    	
    	if(session.getAttribute("loggedInUserId")!=null) {
    		session.removeAttribute("loggedInUserId");
    		return new ResponseEntity<String>("SuccessFullyLoggedOut",HttpStatus.OK);
    	
    	}
    	return new ResponseEntity<String>("Unable to LoggedOut",HttpStatus.UNAUTHORIZED);
    }



// Generate JWT token
	public String getToken(String emailId, String password) throws Exception {
			
		long expirationTime=20_00_000;
	       return Jwts.builder().setSubject(emailId).setIssuedAt(new Date(System.currentTimeMillis()))
	    		   .setExpiration(new Date(System.currentTimeMillis()+expirationTime))
	    		   .signWith(SignatureAlgorithm.HS256,"secretKey").compact();
     
     
}
        
        

}
