package com.CODEns.BackendAPI.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.CODEns.BackendAPI.DTOs.WatchLaterMovieDTO;
import com.CODEns.BackendAPI.Entities.WatchLaterMovie;
import com.CODEns.BackendAPI.Services.WatchLaterService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WatchLaterController {

    @Autowired
    private WatchLaterService watchLaterService;
	
	@RequestMapping(value = "/WatchLater/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<WatchLaterMovieDTO> addWatchLaterMovie(@RequestBody WatchLaterMovie entity) {
		return new Resource<>(watchLaterService.save(entity));
	}

	@RequestMapping(value = "/WatchLater/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<WatchLaterMovieDTO>> getAllWatchLaterMovies() {
		List<Resource<WatchLaterMovieDTO>> movies = watchLaterService.findAll().stream()
				.map( movie -> new Resource<>(movie)).collect(Collectors.toList());
		return new Resources<>(movies);
    }

    @RequestMapping(value = "/WatchLater/User/{id_user}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<WatchLaterMovieDTO>> getAllWatchLaterMoviesByUser(@PathVariable int id_user) {
		List<Resource<WatchLaterMovieDTO>> movies = watchLaterService.findAllByUser(id_user).stream()
				.map( movie -> new Resource<>(movie)).collect(Collectors.toList());
		return new Resources<>(movies);
    }

    @RequestMapping(value = "/WatchLater/Movie/{id_movie}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<WatchLaterMovieDTO> getWatchLaterByMovie(@PathVariable int id_movie) {
		return new Resource<>(watchLaterService.findByIdMovie(id_movie));
    }

	@RequestMapping(value = "/WatchLater/{id}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<WatchLaterMovieDTO> getWatchLaterById(@PathVariable Integer id) {
		return new Resource<>(watchLaterService.getById(id));
	}

	@RequestMapping(value = "/WatchLater/{id}/", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<WatchLaterMovieDTO> deleteWatchLaterById(@PathVariable Integer id) {
		return new Resource<>(watchLaterService.deleteById(id));
	}

    @RequestMapping(value = "/WatchLater/Movie/{id}/", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<WatchLaterMovieDTO> deleteWatchLaterByIdMovie(@PathVariable Integer id) {
		return new Resource<>(watchLaterService.deleteByIdMovie(id));
	}

	@RequestMapping(value = "/WatchLater/{id}/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<WatchLaterMovieDTO> updateWatchLater(@RequestBody WatchLaterMovie entity, @PathVariable Integer id) {
		entity.setIdWatchLater(id);
		return new Resource<>(watchLaterService.update(entity));
	}

}
