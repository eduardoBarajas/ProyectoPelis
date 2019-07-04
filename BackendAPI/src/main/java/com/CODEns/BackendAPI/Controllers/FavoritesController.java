package com.CODEns.BackendAPI.Controllers;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.CODEns.BackendAPI.DTOs.FavoriteMoviesDTO;
import com.CODEns.BackendAPI.Entities.FavoriteMovies;
import com.CODEns.BackendAPI.Services.FavoritesService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FavoritesController {

    @Autowired
    private FavoritesService favoritesService;
	
	@RequestMapping(value = "/Favorites/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<FavoriteMoviesDTO> addMovie(@RequestBody FavoriteMovies entity) {
		return new Resource<>(favoritesService.save(entity));
	}

	@RequestMapping(value = "/Favorites/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<FavoriteMoviesDTO>> getAllFavoritesMovies() {
		List<Resource<FavoriteMoviesDTO>> movies = favoritesService.findAll().stream()
				.map( movie -> new Resource<>(movie)).collect(Collectors.toList());
		return new Resources<>(movies);
    }
    
    @RequestMapping(value = "/Favorites/User/{id_user}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<FavoriteMoviesDTO>> getAllFavoritesMoviesByUser(@PathVariable int id_user) {
		List<Resource<FavoriteMoviesDTO>> movies = favoritesService.findAllByUser(id_user).stream()
				.map( movie -> new Resource<>(movie)).collect(Collectors.toList());
		return new Resources<>(movies);
	}

    @RequestMapping(value = "/Favorites/Movie/{id_movie}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<FavoriteMoviesDTO> getFavoritesByMovie(@PathVariable int id_movie) {
		return new Resource<>(favoritesService.findByIdMovie(id_movie));
    }

    @RequestMapping(value = "/Favorites/Movie/{id_movie}/User/{id_user}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<FavoriteMoviesDTO> getFavoritesByMovieAndUser(@PathVariable int id_movie, @PathVariable int id_user) {
		return new Resource<>(favoritesService.findByIdMovieAndIdUser(id_movie, id_user));
    }

	@RequestMapping(value = "/Favorites/{id}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<FavoriteMoviesDTO> getFavoriteMovieById(@PathVariable Integer id) {
		return new Resource<>(favoritesService.getById(id));
	}

	@RequestMapping(value = "/Favorites/{id}/", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<FavoriteMoviesDTO> deleteFavoritesById(@PathVariable Integer id) {
		return new Resource<>(favoritesService.deleteById(id));
	}

    @RequestMapping(value = "/Favorites/Movie/{id}/", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<FavoriteMoviesDTO> deleteFavoritesMovieById(@PathVariable Integer id) {
		return new Resource<>(favoritesService.deleteByIdMovie(id));
	}

	@RequestMapping(value = "/Favorites/{id}/", method = RequestMethod.PUT,  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<FavoriteMoviesDTO> updateFavoriteMovie(@RequestBody FavoriteMovies entity, @PathVariable Integer id) {
		entity.setIdFavorites(id);
		return new Resource<>(favoritesService.update(entity));
	}

}
