package com.cgi.recomendations.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cgi.recomendations.model.Recomendations;


@Repository
public interface RecomendationsRepository extends MongoRepository<Recomendations, String> {
	
	public Recomendations findOneByUserEmailAndIsbn(String userEmail, String isbn);
	
	public void deleteByRecomendId(int recomendId);
	


}


