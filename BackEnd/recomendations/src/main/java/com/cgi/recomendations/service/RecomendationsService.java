package com.cgi.recomendations.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cgi.recomendations.exception.AlreadyRecomendedException;
import com.cgi.recomendations.exception.NotRecomendedException;
import com.cgi.recomendations.model.Recomendations;

@Service

public interface RecomendationsService {
	
	public Recomendations findOneByUserEmailAndIsbn(String userEmail, String isbn) throws NotRecomendedException;

    boolean saveRecomendations(Recomendations recomendations) throws AlreadyRecomendedException;
    
    public boolean deleteRecomendations( int recomendId) throws NotRecomendedException;
    
    List<Recomendations> getAllRecomendations();

}
