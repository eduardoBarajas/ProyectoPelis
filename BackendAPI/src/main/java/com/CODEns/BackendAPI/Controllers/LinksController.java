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

import com.CODEns.BackendAPI.DTOs.MovieLinksDTO;
import com.CODEns.BackendAPI.DTOs.ResponseDTO;
import com.CODEns.BackendAPI.Entities.MovieLinks;
import com.CODEns.BackendAPI.Services.LinksService;

@RestController
@RequestMapping(path="/links")
public class LinksController {

	@Autowired
	private LinksService links_service;
	
	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@RequestMapping(value = "/movie/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<ResponseDTO<MovieLinksDTO>> addMovieLinks(@RequestBody List<String> links, @PathVariable Integer id) {
		return new Resource<>(links_service.saveMovieLinks(links, id));
	}

	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@RequestMapping(value = "/movies/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpStatus addMoviesLinks(@RequestBody List<MovieLinks> links) {
		return links_service.saveMoviesLinks(links);
	}

	@RequestMapping(value = "/movie/{id}/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<MovieLinksDTO>> getAllMoviesLinks() {
		List<Resource<MovieLinksDTO>> movies_links = links_service.findAllMovieLinks().stream()
				.map( link -> new Resource<>(link)).collect(Collectors.toList());
		return new Resources<>(movies_links);
	}
	
	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@RequestMapping(value = "/movie/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<MovieLinksDTO>> getMovieLinksByIdMovie(@PathVariable Integer id) {
		List<Resource<MovieLinksDTO>> movie_links = links_service.findAllMovieLinksByIdMovie(id).stream()
				.map( link -> new Resource<>(link)).collect(Collectors.toList());
		return new Resources<>(movie_links);
	}

	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@RequestMapping(value = "/movie/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpStatus deleteMovieLinksByIdMovie(@PathVariable Integer id) {
		links_service.deleteMovieLinksByIdMovie(id);
		return HttpStatus.OK;
	}

	@RequestMapping(value = "/movie/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<MovieLinksDTO> updateMovieLinks(@ModelAttribute MovieLinks entity, @PathVariable Integer id) {
		entity.setIdMovie(id);
		return new Resource<>(links_service.updateMovieLinks(entity));
	}
}
