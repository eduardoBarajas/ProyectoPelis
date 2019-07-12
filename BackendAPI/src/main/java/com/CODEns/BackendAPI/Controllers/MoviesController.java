package com.CODEns.BackendAPI.Controllers;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CODEns.BackendAPI.DTOs.MovieDTO;
import com.CODEns.BackendAPI.DTOs.MovieUniqueCheckDTO;
import com.CODEns.BackendAPI.DTOs.MovieReviewDTO;
import com.CODEns.BackendAPI.DTOs.MovieCommentDTO;
import com.CODEns.BackendAPI.DTOs.ResponseDTO;
import com.CODEns.BackendAPI.Entities.Movie;
import com.CODEns.BackendAPI.Entities.MovieLinks;
import com.CODEns.BackendAPI.Services.MoviesService;
import com.CODEns.BackendAPI.Services.CommentsService;
import com.CODEns.BackendAPI.Services.LinksService;
import com.CODEns.BackendAPI.Services.ReviewsService;
import com.CODEns.BackendAPI.DTOs.MovieInteractionsDTO;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MoviesController {

	@Autowired
	private MoviesService movies_service;

    @Autowired
    private ReviewsService reviewService;

    @Autowired
    private CommentsService commentService;

    @Autowired
	private LinksService linksService;
	
	@RequestMapping(value = "/admin/movies", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<MovieDTO> add(@RequestBody Movie entity) {
		return new Resource<>(movies_service.save(entity));
	}
	
	@RequestMapping(value = "/admin/movies/saveAll", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<Map<String, MovieDTO>> addAll(@RequestBody List<MovieDTO> entities) {
        Map<String, MovieDTO> movies = movies_service.saveAll(entities);
        List<MovieLinks> updatedLinks = new LinkedList<>();
        for (MovieDTO entity: entities) {
            if (movies.get(entity.getName()).getStatus().equals("Success")) {
               for (String link : entity.getMovieLinks()) {
                    updatedLinks.add(new MovieLinks(movies.get(entity.getName()).getIdMovie(), link, 1));
                }
            }
        }
        this.linksService.replaceAll(updatedLinks);
		return new Resource<>(movies);
	}

	@RequestMapping(value = "/admin/movies/", method = {RequestMethod.GET, RequestMethod.OPTIONS}, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<MovieDTO>> getAll() {
		List<Resource<MovieDTO>> movies = movies_service.findAll().stream()
				.map( movie -> new Resource<>(movie)).collect(Collectors.toList());
		return new Resources<>(movies);
	}

	@RequestMapping(value = "/movies/trending", method = {RequestMethod.GET, RequestMethod.OPTIONS}, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<MovieDTO>> getAllTrending() {
		List<Resource<MovieDTO>> movies = movies_service.findTrending().stream()
				.map( movie -> new Resource<>(movie)).collect(Collectors.toList());
		return new Resources<>(movies);
	}

    @CrossOrigin(origins = "*", allowedHeaders = "*")
	@RequestMapping(value = "/movies/recents", method = {RequestMethod.GET, RequestMethod.OPTIONS}, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<MovieDTO>> getAllRecents() {
		List<Resource<MovieDTO>> movies = movies_service.findRecents().stream()
				.map( movie -> new Resource<>(movie)).collect(Collectors.toList());
		return new Resources<>(movies);
	}

	@RequestMapping(value = "/movies/ids={moviesIds}", method = {RequestMethod.GET, RequestMethod.OPTIONS}, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<ResponseDTO<MovieDTO>> getAllByIds(@PathVariable List<Integer> moviesIds) {
		return new Resource<>(movies_service.getAllByIds(moviesIds));
	}

	@RequestMapping(value = "/movies/genre={genres}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<MovieDTO>> getAllByGenres(@PathVariable String genres) {
		List<Resource<MovieDTO>> movies = movies_service.findAllByGenres(genres).stream()
				.map( movie -> new Resource<>(movie)).collect(Collectors.toList());
		return new Resources<>(movies);
	}

	@RequestMapping(value = "/movies/genre={genre}/yearStart={startYear}/yearEnd={endYear}/ratingStart={ratingStart}/ratingEnd={ratingEnd}/name={name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<MovieDTO>> getAllByFilter(@PathVariable String genre, @PathVariable int startYear, @PathVariable int endYear,
		@PathVariable double ratingStart, @PathVariable double ratingEnd, @PathVariable String name) {
		List<Resource<MovieDTO>> movies = movies_service.findAllByFilter(genre, startYear, endYear, ratingStart, ratingEnd, name).stream()
				.map( movie -> new Resource<>(movie)).collect(Collectors.toList());
		return new Resources<>(movies);
	}

	@RequestMapping(value = "/movies/getAllGenres", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseDTO<String> getAllByGenres() {
		return movies_service.findAllGenres();
	}

	@RequestMapping(value = "/movies/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<MovieDTO> getById(@PathVariable Integer id) {
		return new Resource<>(movies_service.getById(id));
	}

	@RequestMapping(value = "/movies/year={year}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resources<Resource<MovieDTO>> getAllByYear(@PathVariable int year) {
		List<Resource<MovieDTO>> movies = movies_service.findAllByYear(year).stream()
				.map( movie -> new Resource<>(movie)).collect(Collectors.toList());
		return new Resources<>(movies);
	}

	@RequestMapping(value = "/movies/check", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<ResponseDTO<MovieDTO>> getByNameAndYear(@RequestBody List<MovieUniqueCheckDTO> movies) {
		// si el nombre contiene el caracter '/' en su nombre se debe reemplazar utilizando el tag -slash- que se paso en el string desde el cliente.
		/*if (name.contains("-slash-")) {
            try {
                name = java.net.URLDecoder.decode(name.replace("-slash-", "/"), StandardCharsets.UTF_8.toString());
            } catch(java.io.UnsupportedEncodingException e) {}
		}*/
		return new Resource<>(movies_service.findByNameAndYear(movies));
	}

	@RequestMapping(value = "/admin/movies/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<MovieDTO> deleteById(@PathVariable Integer id) {
		return new Resource<>(movies_service.deleteById(id));
	}

	@RequestMapping(value = "/admin/movies/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<MovieDTO> update(@RequestBody Movie entity) {
		return new Resource<>(movies_service.update(entity));
	}

    @RequestMapping(value = "/movies/{id_movie}/user/{id_user}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<ResponseDTO<String>> getMovieUserNotation(@PathVariable Integer id_movie, @PathVariable Integer id_user) {
		return new Resource<>(movies_service.getMovieUserNotation(id_movie, id_user));
	}

    @RequestMapping(value = "/movies/genre={genre}/limit={limit}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Resources<Resource<MovieDTO>> getMoviesByGenreWithLimit(@PathVariable String genre, @PathVariable int limit) {
        List<Resource<MovieDTO>> movies = movies_service.getMoviesByGenreWithLimit(genre, limit).stream().map( movie -> new Resource<>(movie))
            .collect(Collectors.toList());
        return new Resources<>(movies);
    }

    @RequestMapping(value = "/movies/interactions/{id_movie}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<MovieInteractionsDTO> getMoviesInteractions(@PathVariable int id_movie) {
        MovieInteractionsDTO response = new MovieInteractionsDTO();
        response.setReviews(reviewService.findAllByIdMovie(id_movie));
        response.setComments(commentService.findAllByIdMovie(id_movie));        
        response.setStatus("Success");
        response.setMessage("Interacciones de usuario obtenidas con exito");
        return new Resource<>(response);
    }

    @RequestMapping(value = "/movies/favorites/user={id_user}", method = {RequestMethod.GET, RequestMethod.OPTIONS}, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<ResponseDTO<MovieDTO>> getAllFavorites(@PathVariable int id_user) {
		ResponseDTO<MovieDTO> movies = movies_service.findAllFavorites(id_user);
		return new Resource<>(movies);
	}

    @RequestMapping(value = "/movies/watchLater/user={id_user}", method = {RequestMethod.GET, RequestMethod.OPTIONS}, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<ResponseDTO<MovieDTO>> getAllWatchLater(@PathVariable int id_user) {
		ResponseDTO<MovieDTO> movies = movies_service.findAllWatchLater(id_user);
		return new Resource<>(movies);
	}

    
	/*@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource<MovieDTO> update(@ModelAttribute Movie entity, @PathVariable Integer id) {
		entity.setIdMovie(id);
		return new Resource<>(movies_service.update(entity));
	}*/

}
