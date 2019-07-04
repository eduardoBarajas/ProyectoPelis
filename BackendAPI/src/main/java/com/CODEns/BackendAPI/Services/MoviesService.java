package com.CODEns.BackendAPI.Services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CODEns.BackendAPI.DTOs.ResponseDTO;
import com.CODEns.BackendAPI.DTOs.MovieDTO;
import com.CODEns.BackendAPI.Entities.Movie;
import com.CODEns.BackendAPI.Repositories.MovieRepository;

@Service
public class MoviesService {

	final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private LinksService linksService;
	
	public MovieDTO save(Movie entity) {
		MovieDTO movie_dto;
		Movie db_entity = movieRepository.findByNameAndYear(entity.getName(), entity.getYear());
		if (db_entity != null) {
			db_entity.setCast(entity.getCast());
			db_entity.setPoster(entity.getPoster());
			db_entity.setSynopsis(entity.getSynopsis());
			db_entity.setTags(entity.getTags());
			db_entity.setOriginalName(entity.getOriginalName());
			db_entity.setModificationDate(entity.getModificationDate());
			db_entity.setLength(entity.getLength());
			db_entity.setGenres(entity.getGenres());
			movieRepository.save(db_entity);
			movie_dto = new MovieDTO("Success", "Se actualizo la pelicula con exito.", db_entity.getIdMovie(), db_entity.getName());
		} else {
			if (entity.getCast() == null)
				entity.setCast(" ");
			if (entity.getTags() == null)
				entity.setTags(" ");
			entity.setGrade(5.0);
			Movie new_movie = movieRepository.save(entity);
			if (new_movie.getIdMovie() > 0) {
				movie_dto = new MovieDTO(new_movie, "Success", "Se almaceno la pelicula con exito.");
			} else {
				movie_dto = new MovieDTO("Error", "No se pudo guardar la pelicula en la base de datos.");
			}
		}
		return movie_dto;
	}
	
	public ResponseDTO<MovieDTO> saveAll(List<Movie> entities) {
		ResponseDTO<MovieDTO> response = new ResponseDTO<MovieDTO>();
		for (int x = 0; x < entities.size(); x++) {
			Movie db_entity = movieRepository.findByNameAndYear(entities.get(x).getName(), entities.get(x).getYear());
			if (db_entity != null) {
				db_entity.setCast(entities.get(x).getCast());
				db_entity.setPoster(entities.get(x).getPoster());
				db_entity.setSynopsis(entities.get(x).getSynopsis());
				db_entity.setTags(entities.get(x).getTags());
				db_entity.setOriginalName(entities.get(x).getOriginalName());
				db_entity.setModificationDate(entities.get(x).getModificationDate());
				db_entity.setLength(entities.get(x).getLength());
				db_entity.setGenres(entities.get(x).getGenres());
				movieRepository.save(db_entity);
				response.getResponses().add(new MovieDTO("Success", "Se actualizo la pelicula con exito.", db_entity.getIdMovie(), db_entity.getName()));
			} else {
				if (entities.get(x).getCast() == null)
					entities.get(x).setCast(" ");
				if (entities.get(x).getTags() == null)
					entities.get(x).setTags(" ");
				entities.get(x).setGrade(5.0);
				Movie new_movie = movieRepository.save(entities.get(x));
				if (new_movie.getIdMovie() > 0) {
					response.getResponses().add(new MovieDTO(new_movie, "Success", "Se almaceno la pelicula con exito."));
				} else {
					response.getResponses().add(new MovieDTO("Error", "No se pudo guardar la pelicula en la base de datos."));
				}
			}
		}	
		boolean some_failed = false;
		for (MovieDTO mov_response : response.getResponses()) {
			if (mov_response.getStatus().equals("Error")) {
				some_failed = !some_failed;
				break;
			}
		}
		if (some_failed) {
			response.setStatus("Error");
			response.setMessage("Algunas peliculas no se agregaron con exito");
		} else {
			response.setStatus("Success");
			response.setMessage("Todas las peliculas se agregaron con exito");
		}
		return response;
	}

    public ResponseDTO<MovieDTO> getAllByIds(List<Integer> ids) {
		ResponseDTO<MovieDTO> responses = new ResponseDTO<MovieDTO>();
		for (Integer id : ids) {
            if (movieRepository.existsById(id)) {
			    responses.getResponses().add(new MovieDTO(movieRepository.findById(id).get(), "Success", "Se obtuvo la pelicula con exito."));
            }
        }
        boolean some_failed = false;
		for (MovieDTO mov_response : responses.getResponses()) {
			if (mov_response.getStatus().equals("Error")) {
				some_failed = !some_failed;
				break;
			}
		}
		if (some_failed) {
			responses.setStatus("Error");
			responses.setMessage("Algunas peliculas no se encontraron con exito");
		} else {
			responses.setStatus("Success");
			responses.setMessage("Todas las peliculas se encontraron con exito");
		}
		return responses;
	}

	public ResponseDTO<MovieDTO> findByNameAndYear(String name, int year) {
		ResponseDTO<MovieDTO> response = new ResponseDTO<>();
		response.setResponses(null);
		Movie movie = movieRepository.findByNameAndYear(name, year);
		response.setStatus("Completed");
		if (movie != null) {
			response.setStatus("Success");
			response.setMessage("Esta pelicula ya se encuentra en el sistema, si continuas con la operacion se sobreescribiran los datos.");
		}
		return response;
	}

	public List<MovieDTO> findAllByYear(int year) {
		List<MovieDTO> movies_dto = new LinkedList<>();
		for(Movie movie : movieRepository.findAllByYear(year)) {
			movies_dto.add(new MovieDTO(movie));
		}
		return movies_dto;
	}

	public List<MovieDTO> findAllByGenres(String genres) {
		List<MovieDTO> movies_dto = new LinkedList<>();
		for(Movie movie : movieRepository.findAllByGenresContaining(genres)) {
			movies_dto.add(new MovieDTO(movie));
		}
		return movies_dto;
	}

	public ResponseDTO<String> findAllGenres() {
		ResponseDTO<String> genresResponse = new ResponseDTO<>();
		for(Movie movie : movieRepository.findAll()) {
			for (String genre : movie.getGenres().split(",")) {
				if (genre != " " && !genresResponse.getResponses().contains(genre)) {
					genresResponse.getResponses().add(genre);
				}
			}
		}
		if (genresResponse.getResponses().size() == 0) {
			genresResponse.setMessage("No se pudieron obtener la lista de generos.");
			genresResponse.setStatus("Error");
		} else {
			genresResponse.setMessage("Se obtuvo la lista de generos con exito.");
			genresResponse.setStatus("Success");
		}
		return genresResponse;
	}

	public List<MovieDTO> findAllByFilter(String genre, int yearStart, int yearEnd, double ratingStart, double ratingEnd) {
		List<MovieDTO> movies_dto = new LinkedList<>();
        List<Movie> movie_list;
        System.out.println(genre);
        if (genre.equals("Todos") == true) {
            movie_list = movieRepository.findAllByAllGenresFilter(yearStart, yearEnd, ratingStart, ratingEnd);
            System.out.println("Entro en el primero");
        } else {
            movie_list = movieRepository.findAllByFilter(genre, yearStart, yearEnd, ratingStart, ratingEnd);
            System.out.println("Entro en el segundo");
        }
		for(Movie movie : movie_list) {
			movies_dto.add(new MovieDTO(movie));
		}
		return movies_dto;
	}

    public List<MovieDTO> findTrending() {
        List<MovieDTO> movies_dto = new LinkedList<>();
		for(Movie movie : movieRepository.findTrending()) {
			movies_dto.add(new MovieDTO(movie));
		}
		return movies_dto;
    }
    
    public List<MovieDTO> findRecents() {
        List<MovieDTO> movies_dto = new LinkedList<>();
		for(Movie movie : movieRepository.findRecents()) {
			movies_dto.add(new MovieDTO(movie));
		}
		return movies_dto;
    }

	public List<MovieDTO> findAll() {
		List<MovieDTO> movies_dto = new LinkedList<>();
		for(Movie movie : movieRepository.findAll()) {
			movies_dto.add(new MovieDTO(movie));
		}
		return movies_dto;
	}

	public MovieDTO getById(Integer id) {
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

	public MovieDTO deleteById(Integer id) {
		MovieDTO movie_dto = new MovieDTO("Error", "No existe esa pelicula en la base de datos.");
		if (movieRepository.existsById(id)) {
			movieRepository.deleteById(id);
			this.linksService.deleteByIdMovie(id);
			movie_dto.setMessage("Se elimino la pelicula con exito.");
			movie_dto.setStatus("Success");
		}
		return movie_dto;
	}

	public MovieDTO update(Movie entity) {
		MovieDTO movie_dto = new MovieDTO("Error", "No se encontro la pelicula en la base de datos.");
		if (movieRepository.existsById(entity.getIdMovie())) {
			entity.setModificationDate(this.sdf.format(new Date()));
			movie_dto = new MovieDTO(movieRepository.save(entity), "Success", "Se actualizo la pelicula con exito.");
		}
		return movie_dto;
	}
}
