package com.CODEns.BackendAPI.Services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CODEns.BackendAPI.DTOs.MovieSuggestionDTO;
import com.CODEns.BackendAPI.DTOs.WatchLaterMovieDTO;
import com.CODEns.BackendAPI.DTOs.FavoriteMoviesDTO;
import com.CODEns.BackendAPI.DTOs.MovieDTO;
import com.CODEns.BackendAPI.Entities.MovieSuggestion;
import com.CODEns.BackendAPI.Entities.WatchLaterMovie;
import com.CODEns.BackendAPI.Entities.FavoriteMovies;
import com.CODEns.BackendAPI.Entities.Movie;
import com.CODEns.BackendAPI.Repositories.FavoriteMoviesRepository;
import com.CODEns.BackendAPI.Repositories.MovieRepository;
import com.CODEns.BackendAPI.Repositories.MovieSuggestionRepository;
import com.CODEns.BackendAPI.Repositories.WatchLaterMovieRepository;

@Service
public class MoviesService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private MovieSuggestionRepository movieSuggestionRepository;
	
	@Autowired
	private FavoriteMoviesRepository favoriteMoviesRepository;
	
	@Autowired
	private WatchLaterMovieRepository watchLaterRepository;
	
	public MovieDTO saveMovie(Movie entity) {
		MovieDTO movie_dto;
		if (movieRepository.findByTitle(entity.getTitle()) != null) {
			movie_dto = new MovieDTO("Error", "Ya existe un registro igual en la base de datos.");
		} else {
			if (entity.getCast() == null)
				entity.setCast(" ");
			if (entity.getTags() == null)
				entity.setTags(" ");
			entity.setGrade(0.0);
			Movie new_movie = movieRepository.save(entity);
			if (new_movie.getIdMovie() > 0) {
				movie_dto = new MovieDTO(new_movie, "Success", "Se almaceno la pelicula con exito.");
			} else {
				movie_dto = new MovieDTO("Error", "No se pudo guardar la pelicula en la base de datos.");
			}
		}
		return movie_dto;
	}

	public List<MovieDTO> findAllMovies() {
		List<MovieDTO> movies_dto = new LinkedList<>();
		for(Movie movie : movieRepository.findAll()) {
			movies_dto.add(new MovieDTO(movie));
		}
		return movies_dto;
	}

	public MovieDTO getMovieById(Integer id) {
		MovieDTO movie_dto = new MovieDTO("Error", "No se encontro la pelicula en la base de datos.");
		if (movieRepository.existsById(id)) {
			Movie movie = movieRepository.findById(id).get();
			movie_dto = new MovieDTO(movie, "Success", "Se obtuvo la pelicula con exito.");
			/*for(MovieTags movie_tag : movie_tagsRepository.findByIdMovie(movie.getIdMovie())) {
				movie_dto.getTags().add(tagRepository.findById(movie_tag.getIdTag()).get().getTitle());
			}*/
		}
		return movie_dto;
	}

	public MovieDTO deleteMovieById(Integer id) {
		MovieDTO movie_dto = new MovieDTO("Error", "No existe esa pelicula en la base de datos.");
		if (movieRepository.existsById(id)) {
			movieRepository.deleteById(id);
			movie_dto.setMessage("Se elimino la pelicula con exito.");
			movie_dto.setStatus("Success");
		}
		return movie_dto;
	}

	public MovieDTO updateMovie(Movie entity) {
		MovieDTO movie_dto = new MovieDTO("Error", "No se encontro la pelicula en la base de datos.");
		if (movieRepository.existsById(entity.getIdMovie())) {
			movie_dto = new MovieDTO(movieRepository.save(entity), "Success", "Se actualizo la pelicula con exito.");
		}
		return movie_dto;
	}
	
	public MovieSuggestionDTO saveMovieSuggestion(MovieSuggestion entity) {
		MovieSuggestion new_movieSuggestion = movieSuggestionRepository.save(entity);
		MovieSuggestionDTO movieSuggestion_dto;
		if (new_movieSuggestion.getIdSuggestion() > 0) {
			movieSuggestion_dto = new MovieSuggestionDTO(new_movieSuggestion, "Success", "Se almaceno con exito.");
		} else {
			movieSuggestion_dto = new MovieSuggestionDTO("Error", "No se pudo guardar en la base de datos.");
		}
		return movieSuggestion_dto;
	}

	public List<MovieSuggestionDTO> findAllMovieSuggestions() {
		List<MovieSuggestionDTO> MovieSuggestions_dto = new LinkedList<>();
		for(MovieSuggestion movieSuggestion: movieSuggestionRepository.findAll()) {
			MovieSuggestions_dto.add(new MovieSuggestionDTO(movieSuggestion));
		}
		return MovieSuggestions_dto;
	}

	public MovieSuggestionDTO getMovieSuggestionById(Integer id) {
		MovieSuggestionDTO MovieSuggestion_dto = new MovieSuggestionDTO("Error", "No se encontro en la base de datos.");
		if (movieSuggestionRepository.existsById(id)) {
			MovieSuggestion MovieSuggestion = movieSuggestionRepository.findById(id).get();
			MovieSuggestion_dto = new MovieSuggestionDTO(MovieSuggestion, "Success", "Se obtuvo con exito.");
		}
		return MovieSuggestion_dto;
	}

	public MovieSuggestionDTO deleteMovieSuggestionById(Integer id) {
		MovieSuggestionDTO movieSuggestion_dto = new MovieSuggestionDTO("Error", "No existe en la base de datos.");
		if (movieSuggestionRepository.existsById(id)) {
			movieSuggestionRepository.deleteById(id);
			movieSuggestion_dto.setMessage("Se elimino con exito.");
			movieSuggestion_dto.setStatus("Success");
		}
		return movieSuggestion_dto;
	}

	public MovieSuggestionDTO updateMovieSuggestion(MovieSuggestion entity) {
		MovieSuggestionDTO movieSuggestion_dto = new MovieSuggestionDTO("Error", "No se encontro en la base de datos.");
		if (movieSuggestionRepository.existsById(entity.getIdSuggestion())) {
			movieSuggestion_dto = new MovieSuggestionDTO(movieSuggestionRepository.save(entity), "Success", "Se actualizo con exito.");
		}
		return movieSuggestion_dto;
	}
	
	public FavoriteMoviesDTO saveFavoriteMovies(FavoriteMovies entity) {
		FavoriteMovies new_favorite_movies = favoriteMoviesRepository.save(entity);
		FavoriteMoviesDTO favorite_movies_dto;
		if (new_favorite_movies.getIdFavorites() > 0) {
			favorite_movies_dto = new FavoriteMoviesDTO(new_favorite_movies, "Success", "Se almaceno con exito.");
		} else {
			favorite_movies_dto = new FavoriteMoviesDTO("Error", "No se pudo guardar en la base de datos.");
		}
		return favorite_movies_dto;
	}

	public List<FavoriteMoviesDTO> findAllFavoriteMoviess() {
		List<FavoriteMoviesDTO> FavoriteMoviess_dto = new LinkedList<>();
		for(FavoriteMovies favorite_movies: favoriteMoviesRepository.findAll()) {
			FavoriteMoviess_dto.add(new FavoriteMoviesDTO(favorite_movies));
		}
		return FavoriteMoviess_dto;
	}

	public FavoriteMoviesDTO getFavoriteMoviesById(Integer id) {
		FavoriteMoviesDTO FavoriteMovies_dto = new FavoriteMoviesDTO("Error", "No se encontro en la base de datos.");
		if (favoriteMoviesRepository.existsById(id)) {
			FavoriteMovies FavoriteMovies = favoriteMoviesRepository.findById(id).get();
			FavoriteMovies_dto = new FavoriteMoviesDTO(FavoriteMovies, "Success", "Se obtuvo con exito.");
		}
		return FavoriteMovies_dto;
	}

	public FavoriteMoviesDTO deleteFavoriteMoviesById(Integer id) {
		FavoriteMoviesDTO favorite_movies_dto = new FavoriteMoviesDTO("Error", "No existe en la base de datos.");
		if (favoriteMoviesRepository.existsById(id)) {
			favoriteMoviesRepository.deleteById(id);
			favorite_movies_dto.setMessage("Se elimino con exito.");
			favorite_movies_dto.setStatus("Success");
		}
		return favorite_movies_dto;
	}

	public FavoriteMoviesDTO updateFavoriteMovies(FavoriteMovies entity) {
		FavoriteMoviesDTO favorite_movies_dto = new FavoriteMoviesDTO("Error", "No se encontro en la base de datos.");
		if (favoriteMoviesRepository.existsById(entity.getIdFavorites())) {
			favorite_movies_dto = new FavoriteMoviesDTO(favoriteMoviesRepository.save(entity), "Success", "Se actualizo con exito.");
		}
		return favorite_movies_dto;
	}
	
	public WatchLaterMovieDTO saveWatchLaterMovie(WatchLaterMovie entity) {
		WatchLaterMovie new_watch_later_movie = watchLaterRepository.save(entity);
		WatchLaterMovieDTO watch_later_movie_dto;
		if (new_watch_later_movie.getIdWatchLater() > 0) {
			watch_later_movie_dto = new WatchLaterMovieDTO(new_watch_later_movie, "Success", "Se almaceno con exito.");
		} else {
			watch_later_movie_dto = new WatchLaterMovieDTO("Error", "No se pudo guardar en la base de datos.");
		}
		return watch_later_movie_dto;
	}

	public List<WatchLaterMovieDTO> findAllWatchLaterMovies() {
		List<WatchLaterMovieDTO> watch_later_movie_dto = new LinkedList<>();
		for(WatchLaterMovie watch_later_movie: watchLaterRepository.findAll()) {
			watch_later_movie_dto.add(new WatchLaterMovieDTO(watch_later_movie));
		}
		return watch_later_movie_dto;
	}

	public WatchLaterMovieDTO getWatchLaterMovieById(Integer id) {
		WatchLaterMovieDTO watch_later_movie_dto = new WatchLaterMovieDTO("Error", "No se encontro en la base de datos.");
		if (watchLaterRepository.existsById(id)) {
			WatchLaterMovie watch_later_movie = watchLaterRepository.findById(id).get();
			watch_later_movie_dto = new WatchLaterMovieDTO(watch_later_movie, "Success", "Se obtuvo con exito.");
		}
		return watch_later_movie_dto;
	}

	public WatchLaterMovieDTO deleteWatchLaterMovieById(Integer id) {
		WatchLaterMovieDTO watch_later_movie_dto = new WatchLaterMovieDTO("Error", "No existe en la base de datos.");
		if (watchLaterRepository.existsById(id)) {
			watchLaterRepository.deleteById(id);
			watch_later_movie_dto.setMessage("Se elimino con exito.");
			watch_later_movie_dto.setStatus("Success");
		}
		return watch_later_movie_dto;
	}

	public WatchLaterMovieDTO updateWatchLaterMovie(WatchLaterMovie entity) {
		WatchLaterMovieDTO watch_later_movie_dto = new WatchLaterMovieDTO("Error", "No se encontro en la base de datos.");
		if (watchLaterRepository.existsById(entity.getIdWatchLater())) {
			watch_later_movie_dto = new WatchLaterMovieDTO(watchLaterRepository.save(entity), "Success", "Se actualizo con exito.");
		}
		return watch_later_movie_dto;
	}

}
