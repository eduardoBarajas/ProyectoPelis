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

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class LinksController {

	@Autowired
	private LinksService links_service;
	
	@RequestMapping(value = "/admin/links/movie/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<ResponseDTO<MovieLinksDTO>> add(@RequestBody List<String> links, @PathVariable Integer id) {
		return new Resource<>(links_service.save(links, id));
	}

	@RequestMapping(value = "/admin/links/movies/replaceAll", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpStatus replaceAll(@RequestBody List<MovieLinks> links) {
		return links_service.replaceAll(links);
	}

    @RequestMapping(value = "/admin/links/movies/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpStatus addAll(@RequestBody List<MovieLinks> links) {
		return links_service.saveAll(links);
	}

	@RequestMapping(value = "/admin/links/movie/{id}/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<MovieLinksDTO>> getAll() {
		List<Resource<MovieLinksDTO>> movies_links = links_service.findAll().stream()
				.map( link -> new Resource<>(link)).collect(Collectors.toList());
		return new Resources<>(movies_links);
	}
	
	@RequestMapping(value = "/links/movie/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<MovieLinksDTO>> getAllByIdMovie(@PathVariable Integer id) {
		List<Resource<MovieLinksDTO>> movie_links = links_service.findAllByIdMovie(id).stream()
				.map( link -> new Resource<>(link)).collect(Collectors.toList());
		return new Resources<>(movie_links);
	}

	@RequestMapping(value = "/admin/links/movie/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpStatus deleteByIdMovie(@PathVariable Integer id) {
		links_service.deleteByIdMovie(id);
		return HttpStatus.OK;
	}

    @RequestMapping(value = "/admin/links/movie/deleteAll/ids={ids_links}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpStatus deleteAll(@PathVariable List<Integer> ids_links) {
		return links_service.deleteAllById(ids_links);
	}

	@RequestMapping(value = "/links/movie/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<MovieLinksDTO> update(@RequestBody MovieLinks entity, @PathVariable Integer id) {
		entity.setIdLinkMovie(id);
		return new Resource<>(links_service.update(entity));
	}

    @RequestMapping(value = "/admin/links/movie/updateAll", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpStatus updateAll(@RequestBody List<MovieLinks> links) {
		return links_service.updateAll(links);
	}
}
