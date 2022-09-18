package com.cgi.recomendations.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.recomendations.exception.AlreadyRecomendedException;
import com.cgi.recomendations.exception.NotRecomendedException;
import com.cgi.recomendations.model.Recomendations;
import com.cgi.recomendations.service.RecomendationsService;


@RestController
@CrossOrigin("http://localhost:4200")
public class RecomendationsController {
	
	@Autowired
	private RecomendationsService  recommendService; 
	
	public RecomendationsController(RecomendationsService  recommendService)
	{
		this.recommendService=recommendService;
	}
	
	//adding the recommend
	 @PostMapping("/api/v1/recommend")
 public ResponseEntity<?> addRecommend(@RequestBody Recomendations recomendations)
 {
	 try {
		 recommendService.saveRecomendations(recomendations);
		return new ResponseEntity<String>("Recommend Added Successfully",HttpStatus.CREATED);
	} catch (AlreadyRecomendedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
	}
 }
	 //delete the recommend
	@DeleteMapping("/api/v1/recommend/{recommendId}")
	public  ResponseEntity<?> deleteRecommend(@PathVariable int recommendId)
	{
		try {
			recommendService.deleteRecomendations(recommendId);
			return new ResponseEntity<String>("Recommend deleted",HttpStatus.OK);
		} catch (NotRecomendedException e) {
			// TODO Auto-generated catch block
			 return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/api/v1/recommend")
  public ResponseEntity<?> getAllRecommends(@RequestBody Recomendations recomendations)
  {
	    try {
			List<Recomendations> data=recommendService.getAllRecomendations();
			return new ResponseEntity<List<Recomendations>>(data,HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 return new ResponseEntity<String>("No recommends",HttpStatus.NOT_FOUND);
		}
  }
	

}
