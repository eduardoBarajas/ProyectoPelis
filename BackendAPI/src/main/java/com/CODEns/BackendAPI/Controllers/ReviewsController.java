package com.CODEns.BackendAPI.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.CODEns.BackendAPI.DTOs.MovieReviewDTO;
import com.CODEns.BackendAPI.DTOs.ResponseDTO;
import com.CODEns.BackendAPI.Entities.MovieReview;
import com.CODEns.BackendAPI.Services.LinksService;
import com.CODEns.BackendAPI.Services.ReviewsService;

@RestController
@RequestMapping(path="/reviews")
public class ReviewsController {

	@Autowired
	private ReviewsService reviews_service;
	
	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<MovieReviewDTO> add(@RequestBody MovieReview review) {
		return new Resource<>(reviews_service.save(review));
	}

	@RequestMapping(value = "/movie/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<MovieReviewDTO>> getAllByIdMovie(@PathVariable Integer idMovie) {
		List<Resource<MovieReviewDTO>> movies_reviews = reviews_service.findAllByIdMovie(idMovie).stream()
				.map( review -> new Resource<>(review)).collect(Collectors.toList());
		return new Resources<>(movies_reviews);
	}

	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@RequestMapping(value = "/movie/review/{idReview}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpStatus deleteById(@PathVariable Integer id) {
		reviews_service.deleteById(id);
		return HttpStatus.OK;
	}

	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@RequestMapping(value = "/movie/review/{idReview}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<MovieReviewDTO> update(@ModelAttribute MovieReview entity, @PathVariable Integer idReview) {
		entity.setIdMovie(idReview);
		return new Resource<>(reviews_service.update(entity));
	}
}
