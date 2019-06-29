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
import com.CODEns.BackendAPI.DTOs.MovieDTO;
import com.CODEns.BackendAPI.DTOs.MovieSuggestionDTO;
import com.CODEns.BackendAPI.DTOs.ResponseDTO;
import com.CODEns.BackendAPI.DTOs.WatchLaterMovieDTO;
import com.CODEns.BackendAPI.Entities.FavoriteMovies;
import com.CODEns.BackendAPI.Entities.Movie;
import com.CODEns.BackendAPI.Entities.MovieSuggestion;
import com.CODEns.BackendAPI.Entities.WatchLaterMovie;
import com.CODEns.BackendAPI.Services.MoviesService;

@RestController
@RequestMapping(path="/movies")
public class MoviesController {

	@Autowired
	private MoviesService movies_service;
	
	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<MovieDTO> add(@RequestBody Movie entity) {
		return new Resource<>(movies_service.save(entity));
	}
	
	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@RequestMapping(value = "/saveAll", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<ResponseDTO<MovieDTO>> addAll(@RequestBody List<Movie> entities) {
		return new Resource<>(movies_service.saveAll(entities));
	}

	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<MovieDTO>> getAll() {
		List<Resource<MovieDTO>> movies = movies_service.findAll().stream()
				.map( movie -> new Resource<>(movie)).collect(Collectors.toList());
		return new Resources<>(movies);
	}

	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@RequestMapping(value = "/genre={genres}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<MovieDTO>> getAllByGenres(@PathVariable String genres) {
		List<Resource<MovieDTO>> movies = movies_service.findAllByGenres(genres).stream()
				.map( movie -> new Resource<>(movie)).collect(Collectors.toList());
		return new Resources<>(movies);
	}

	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@RequestMapping(value = "/genre={genre}/yearStart={startYear}/yearEnd={endYear}/ratingStart={ratingStart}/ratingEnd={ratingEnd}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<MovieDTO>> getAllByFilter(@PathVariable String genre, @PathVariable int startYear, @PathVariable int endYear,
		@PathVariable double ratingStart, @PathVariable double ratingEnd) {
		List<Resource<MovieDTO>> movies = movies_service.findAllByFilter(genre, startYear, endYear, ratingStart, ratingEnd).stream()
				.map( movie -> new Resource<>(movie)).collect(Collectors.toList());
		return new Resources<>(movies);
	}

	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@RequestMapping(value = "/getAllGenres", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseDTO<String> getAllByGenres() {
		return movies_service.findAllGenres();
	}

	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<MovieDTO> getById(@PathVariable Integer id) {
		return new Resource<>(movies_service.getById(id));
	}

	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@RequestMapping(value = "/year={year}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<MovieDTO>> getAllByYear(@PathVariable int year) {
		List<Resource<MovieDTO>> movies = movies_service.findAllByYear(year).stream()
				.map( movie -> new Resource<>(movie)).collect(Collectors.toList());
		return new Resources<>(movies);
	}

	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@RequestMapping(value = "/name={name}/year={year}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<ResponseDTO<MovieDTO>> getByNameAndYear(@PathVariable String name, @PathVariable int year) {
		// si el nombre contiene el caracter '/' en su nombre se debe reemplazar utilizando el tag -slash- que se paso en el string desde el cliente.
		if (name.contains("-slash-")) {
			name = java.net.URLDecoder.decode(name.replace("-slash-", "/"), StandardCharsets.UTF_8);
		}
		return new Resource<>(movies_service.findByNameAndYear(name, year));
	}

	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<MovieDTO> deleteById(@PathVariable Integer id) {
		return new Resource<>(movies_service.deleteById(id));
	}

	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<MovieDTO> update(@RequestBody Movie entity) {
		return new Resource<>(movies_service.update(entity));
	}
	/*@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<MovieDTO> update(@ModelAttribute Movie entity, @PathVariable Integer id) {
		entity.setIdMovie(id);
		return new Resource<>(movies_service.update(entity));
	}*/
	
	@RequestMapping(value = "/Suggestions", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<MovieSuggestionDTO> addMovieSuggestion(@ModelAttribute MovieSuggestion entity) {
		return new Resource<>(movies_service.saveMovieSuggestion(entity));
	}

	@RequestMapping(value = "/Suggestions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<MovieSuggestionDTO>> getAllMoviesSuggestions() {
		List<Resource<MovieSuggestionDTO>> suggestions = movies_service.findAllMovieSuggestions().stream()
				.map( suggestion -> new Resource<>(suggestion)).collect(Collectors.toList());
		return new Resources<>(suggestions);
	}

	@RequestMapping(value = "/Suggestions/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<MovieSuggestionDTO> getMovieSuggestionById(@PathVariable Integer id) {
		return new Resource<>(movies_service.getMovieSuggestionById(id));
	}

	@RequestMapping(value = "/Suggestion/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<MovieSuggestionDTO> deleteMovieSuggestionById(@PathVariable Integer id) {
		return new Resource<>(movies_service.deleteMovieSuggestionById(id));
	}

	@RequestMapping(value = "/Suggestion/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<MovieSuggestionDTO> updateMovie(@ModelAttribute MovieSuggestion entity, @PathVariable Integer id) {
		entity.setIdSuggestion(id);
		return new Resource<>(movies_service.updateMovieSuggestion(entity));
	}
	
	@RequestMapping(value = "/Favorites", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<FavoriteMoviesDTO> addMovie(@ModelAttribute FavoriteMovies entity) {
		return new Resource<>(movies_service.saveFavoriteMovies(entity));
	}

	@RequestMapping(value = "/Favorites", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<FavoriteMoviesDTO>> getAllFavoritesMovies() {
		List<Resource<FavoriteMoviesDTO>> movies = movies_service.findAllFavoriteMoviess().stream()
				.map( movie -> new Resource<>(movie)).collect(Collectors.toList());
		return new Resources<>(movies);
	}

	@RequestMapping(value = "/Favorites/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<FavoriteMoviesDTO> getFavoriteMovieById(@PathVariable Integer id) {
		return new Resource<>(movies_service.getFavoriteMoviesById(id));
	}

	@RequestMapping(value = "/Favorites/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<FavoriteMoviesDTO> deleteFavoritesMovieById(@PathVariable Integer id) {
		return new Resource<>(movies_service.deleteFavoriteMoviesById(id));
	}

	@RequestMapping(value = "/Favorites/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<FavoriteMoviesDTO> updateFavoriteMovie(@ModelAttribute FavoriteMovies entity, @PathVariable Integer id) {
		entity.setIdFavorites(id);
		return new Resource<>(movies_service.updateFavoriteMovies(entity));
	}
	
	@RequestMapping(value = "/WatchLater", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<WatchLaterMovieDTO> addWatchLaterMovie(@ModelAttribute WatchLaterMovie entity) {
		return new Resource<>(movies_service.saveWatchLaterMovie(entity));
	}

	@RequestMapping(value = "/WatchLater", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<WatchLaterMovieDTO>> getAllWatchLaterMovies() {
		List<Resource<WatchLaterMovieDTO>> movies = movies_service.findAllWatchLaterMovies().stream()
				.map( movie -> new Resource<>(movie)).collect(Collectors.toList());
		return new Resources<>(movies);
	}

	@RequestMapping(value = "/WatchLater/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<WatchLaterMovieDTO> getWatchLaterMovieById(@PathVariable Integer id) {
		return new Resource<>(movies_service.getWatchLaterMovieById(id));
	}

	@RequestMapping(value = "/WatchLater/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<WatchLaterMovieDTO> deleteWatchLaterMovieById(@PathVariable Integer id) {
		return new Resource<>(movies_service.deleteWatchLaterMovieById(id));
	}

	@RequestMapping(value = "/WatchLater/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<WatchLaterMovieDTO> updateWatchLaterMovie(@ModelAttribute WatchLaterMovie entity, @PathVariable Integer id) {
		entity.setIdWatchLater(id);
		return new Resource<>(movies_service.updateWatchLaterMovie(entity));
	}

}
