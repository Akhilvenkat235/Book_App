package com.cgi.favourite.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.cgi.favourite.model.Favourite;

@Repository
public interface FavouriteRepository extends MongoRepository<Favourite, Integer> {

	public long deleteByIsbnAndUserEmail(String isbn,String userEmail);
	public Optional<Favourite> findOneByIsbnAndUserEmail(String isbn,String userEmail);
	public Favourite[] findAllFavouritesByUserEmail(String userEmail);
	
}

 