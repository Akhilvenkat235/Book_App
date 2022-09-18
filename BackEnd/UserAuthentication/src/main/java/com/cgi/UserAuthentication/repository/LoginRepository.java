package com.cgi.UserAuthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgi.UserAuthentication.model.User;

@Repository
public interface LoginRepository extends JpaRepository<User, String> {
	
    User findByUserEmailAndUserPassword(String userEmail, String userPassword);


}
