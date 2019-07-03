package com.CODEns.BackendAPI.Services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CODEns.BackendAPI.DTOs.FavoriteMoviesDTO;
import com.CODEns.BackendAPI.Entities.FavoriteMovies;
import com.CODEns.BackendAPI.Repositories.FavoriteMoviesRepository;

@Transactional
@Service
public class FavoritesService {

	@Autowired
	private FavoriteMoviesRepository favoriteMoviesRepository;

	public FavoriteMoviesDTO save(FavoriteMovies entity) {
		FavoriteMovies new_favorite_movies = favoriteMoviesRepository.save(entity);
		FavoriteMoviesDTO favorite_movies_dto;
		if (new_favorite_movies.getIdFavorites() > 0) {
			favorite_movies_dto = new FavoriteMoviesDTO(new_favorite_movies, "Success", "Se almaceno con exito.");
		} else {
			favorite_movies_dto = new FavoriteMoviesDTO("Error", "No se pudo guardar en la base de datos.");
		}
		return favorite_movies_dto;
	}

	public List<FavoriteMoviesDTO> findAll() {
		List<FavoriteMoviesDTO> FavoriteMoviess_dto = new LinkedList<>();
		for(FavoriteMovies favorite_movies: favoriteMoviesRepository.findAll()) {
			FavoriteMoviess_dto.add(new FavoriteMoviesDTO(favorite_movies));
		}
		return FavoriteMoviess_dto;
    }
    
    public List<FavoriteMoviesDTO> findAllByUser(int id) {
        List<FavoriteMoviesDTO> movies = new LinkedList<>();
        for (FavoriteMovies fav : this.favoriteMoviesRepository.findAllByIdUser(id)) {
            movies.add(new FavoriteMoviesDTO(fav));
        }
        return movies;
    }

	public FavoriteMoviesDTO getById(Integer id) {
		FavoriteMoviesDTO FavoriteMovies_dto = new FavoriteMoviesDTO("Error", "No se encontro en la base de datos.");
		if (favoriteMoviesRepository.existsById(id)) {
			FavoriteMovies FavoriteMovies = favoriteMoviesRepository.findById(id).get();
			FavoriteMovies_dto = new FavoriteMoviesDTO(FavoriteMovies, "Success", "Se obtuvo con exito.");
		}
		return FavoriteMovies_dto;
	}

	public FavoriteMoviesDTO deleteById(Integer id) {
		FavoriteMoviesDTO favorite_movies_dto = new FavoriteMoviesDTO("Error", "No existe en la base de datos.");
		if (favoriteMoviesRepository.existsById(id)) {
			favoriteMoviesRepository.deleteById(id);
			favorite_movies_dto.setMessage("Se elimino con exito.");
			favorite_movies_dto.setStatus("Success");
		}
		return favorite_movies_dto;
	}

    public FavoriteMoviesDTO findByIdMovie(int id_movie) {
        FavoriteMoviesDTO movie = new FavoriteMoviesDTO("Error", "No se encontro en la base de datos.");
        FavoriteMovies data_movie = favoriteMoviesRepository.findByIdMovie(id_movie);
        if (data_movie != null) {
            movie = new FavoriteMoviesDTO(data_movie, "Success", "Se encontro en la base de datos.");
        }
        return movie;
    }

    public FavoriteMoviesDTO deleteByIdMovie(Integer id) {
		FavoriteMoviesDTO favorite_dto = new FavoriteMoviesDTO("Error", "No existe en la base de datos.");
        Integer deleted = favoriteMoviesRepository.deleteByIdMovie(id);
		if (deleted != null) {
			favorite_dto.setStatus("Success");
            favorite_dto.setMessage("Se elimino con exito.");
		}
		return favorite_dto;
	}

	public FavoriteMoviesDTO update(FavoriteMovies entity) {
		FavoriteMoviesDTO favorite_movies_dto = new FavoriteMoviesDTO("Error", "No se encontro en la base de datos.");
		if (favoriteMoviesRepository.existsById(entity.getIdFavorites())) {
			favorite_movies_dto = new FavoriteMoviesDTO(favoriteMoviesRepository.save(entity), "Success", "Se actualizo con exito.");
		}
		return favorite_movies_dto;
	}

}
