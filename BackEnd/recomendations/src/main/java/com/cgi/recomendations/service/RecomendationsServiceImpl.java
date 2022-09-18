package com.cgi.recomendations.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.recomendations.exception.AlreadyRecomendedException;
import com.cgi.recomendations.exception.NotRecomendedException;
import com.cgi.recomendations.model.Recomendations;
import com.cgi.recomendations.repository.RecomendationsRepository;


@Service
public class RecomendationsServiceImpl implements RecomendationsService{
	

	@Autowired
	private RecomendationsRepository repository;

//to validate EmailId and password
	
	@Override
	public Recomendations findOneByUserEmailAndIsbn(String userEmail, String isbn) throws NotRecomendedException {
		
		Recomendations recomendations=repository.findOneByUserEmailAndIsbn(userEmail, isbn);
	if(recomendations==null)
		
		throw new NotRecomendedException("emailId and book number mismatch");
	return recomendations;
	}
	
	//to save new recommendations

	@Override
	public boolean saveRecomendations(Recomendations recomendations) throws AlreadyRecomendedException {
		
		Optional<Recomendations> recomend = repository.findById(recomendations.getUserEmail());
		if (recomend.isPresent())
			throw new AlreadyRecomendedException("User with emailId already exists");

		repository.save(recomendations);
		return true;
	}
	
	@Override
	public boolean deleteRecomendations(int recomendId) throws NotRecomendedException {
		
		try {
			repository.deleteByRecomendId(recomendId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NotRecomendedException("not able to delete");
		}
		// TODO Auto-generated method stub
		return true;
	}
	

	@Override
	public List<Recomendations> getAllRecomendations() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}


}
