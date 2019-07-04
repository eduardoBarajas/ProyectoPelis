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
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentsController {

	@Autowired
	private CommentsService comments_service;
	
	@RequestMapping(value = "/comments/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<MovieCommentDTO> add(@RequestBody MovieComment comment) {
		return new Resource<>(comments_service.save(comment));
	}

	@RequestMapping(value = "/comments/movie/{idMovie}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<MovieCommentDTO>> getAllByIdMovie(@PathVariable Integer idMovie) {
		List<Resource<MovieCommentDTO>> movies_comments = comments_service.findAllByIdMovie(idMovie).stream()
				.map( comment -> new Resource<>(comment)).collect(Collectors.toList());
		return new Resources<>(movies_comments);
	}

	@RequestMapping(value = "/comments/movie/comment/{idComment}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpStatus deleteById(@PathVariable Integer idComment) {
		comments_service.deleteById(idComment);
		return HttpStatus.OK;
	}

	@RequestMapping(value = "/comments/movie/comment/{idComment}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<MovieCommentDTO> update(@RequestBody MovieComment entity, @PathVariable Integer idComment) {
		entity.setIdComment(idComment);
		return new Resource<>(comments_service.update(entity));
	}
}
