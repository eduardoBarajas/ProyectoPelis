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

import com.CODEns.BackendAPI.DTOs.MovieCommentDTO;
import com.CODEns.BackendAPI.DTOs.ResponseDTO;
import com.CODEns.BackendAPI.Entities.MovieComment;
import com.CODEns.BackendAPI.Services.CommentsService;

@RestController
@RequestMapping(path="/comments")
public class CommentsController {

	@Autowired
	private CommentsService comments_service;
	
	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<MovieCommentDTO> add(@RequestBody MovieComment comment) {
		return new Resource<>(comments_service.save(comment));
	}

	@RequestMapping(value = "/movie/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<MovieCommentDTO>> getAllByIdMovie(@PathVariable Integer idMovie) {
		List<Resource<MovieCommentDTO>> movies_comments = comments_service.findAllByIdMovie(idMovie).stream()
				.map( comment -> new Resource<>(comment)).collect(Collectors.toList());
		return new Resources<>(movies_comments);
	}

	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@RequestMapping(value = "/movie/comment/{idComment}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpStatus deleteById(@PathVariable Integer id) {
		comments_service.deleteById(id);
		return HttpStatus.OK;
	}

	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@RequestMapping(value = "/movie/comment/{idComment}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<MovieCommentDTO> update(@ModelAttribute MovieComment entity, @PathVariable Integer idComment) {
		entity.setIdMovie(idComment);
		return new Resource<>(comments_service.update(entity));
	}
}
