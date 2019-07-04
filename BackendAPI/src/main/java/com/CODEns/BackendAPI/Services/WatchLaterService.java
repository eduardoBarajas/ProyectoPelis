package com.CODEns.BackendAPI.Services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.CODEns.BackendAPI.DTOs.WatchLaterMovieDTO;
import com.CODEns.BackendAPI.Entities.WatchLaterMovie;
import com.CODEns.BackendAPI.Repositories.WatchLaterMovieRepository;

@Transactional
@Service
public class WatchLaterService {

    @Autowired
    private WatchLaterMovieRepository watchLaterRepository;
	
	public WatchLaterMovieDTO save(WatchLaterMovie entity) {
		WatchLaterMovie new_watch_later_movie = watchLaterRepository.save(entity);
		WatchLaterMovieDTO watch_later_movie_dto;
		if (new_watch_later_movie.getIdWatchLater() > 0) {
			watch_later_movie_dto = new WatchLaterMovieDTO(new_watch_later_movie, "Success", "Se almaceno con exito.");
		} else {
			watch_later_movie_dto = new WatchLaterMovieDTO("Error", "No se pudo guardar en la base de datos.");
		}
		return watch_later_movie_dto;
	}

	public List<WatchLaterMovieDTO> findAll() {
		List<WatchLaterMovieDTO> watch_later_movie_dto = new LinkedList<>();
		for(WatchLaterMovie watch_later_movie: watchLaterRepository.findAll()) {
			watch_later_movie_dto.add(new WatchLaterMovieDTO(watch_later_movie));
		}
		return watch_later_movie_dto;
    }
    
    public List<WatchLaterMovieDTO> findAllByUser(int id) {
        List<WatchLaterMovieDTO> movies = new LinkedList<>();
        for (WatchLaterMovie m : this.watchLaterRepository.findAllByIdUser(id)) {
            movies.add(new WatchLaterMovieDTO(m));
        }
        return movies;
    }

	public WatchLaterMovieDTO getById(Integer id) {
		WatchLaterMovieDTO watch_later_movie_dto = new WatchLaterMovieDTO("Error", "No se encontro en la base de datos.");
		if (watchLaterRepository.existsById(id)) {
			WatchLaterMovie watch_later_movie = watchLaterRepository.findById(id).get();
			watch_later_movie_dto = new WatchLaterMovieDTO(watch_later_movie, "Success", "Se obtuvo con exito.");
		}
		return watch_later_movie_dto;
	}

	public WatchLaterMovieDTO deleteById(Integer id) {
		WatchLaterMovieDTO watch_later_movie_dto = new WatchLaterMovieDTO("Error", "No existe en la base de datos.");
		if (watchLaterRepository.existsById(id)) {
			watchLaterRepository.deleteById(id);
			watch_later_movie_dto.setMessage("Se elimino con exito.");
			watch_later_movie_dto.setStatus("Success");
		}
		return watch_later_movie_dto;
	}

    public WatchLaterMovieDTO deleteByIdMovie(Integer id) {
		WatchLaterMovieDTO watch_later_movie_dto = new WatchLaterMovieDTO("Error", "No existe en la base de datos.");
        Integer deleted = watchLaterRepository.deleteByIdMovie(id);
		if (deleted != null) {
			watch_later_movie_dto.setStatus("Success");
            watch_later_movie_dto.setMessage("Se elimino con exito.");
		}
		return watch_later_movie_dto;
	}

    public WatchLaterMovieDTO findByIdMovie(int id_movie) {
        WatchLaterMovieDTO movie = new WatchLaterMovieDTO("Error", "No se encontro en la base de datos.");
        WatchLaterMovie data_movie = watchLaterRepository.findByIdMovie(id_movie);
        if (data_movie != null) {
            movie = new WatchLaterMovieDTO(data_movie, "Success", "Se encontro en la base de datos.");
        }
        return movie;
    }

    public WatchLaterMovieDTO findByIdMovieAndIdUser(int id_movie, int id_user) {
        WatchLaterMovieDTO movie = new WatchLaterMovieDTO("Error", "No se encontro en la base de datos.");
        WatchLaterMovie data_movie = watchLaterRepository.findByIdMovieAndIdUser(id_movie, id_user);
        if (data_movie != null) {
            movie = new WatchLaterMovieDTO(data_movie, "Success", "Se encontro en la base de datos.");
        }
        return movie;
    }

	public WatchLaterMovieDTO update(WatchLaterMovie entity) {
		WatchLaterMovieDTO watch_later_movie_dto = new WatchLaterMovieDTO("Error", "No se encontro en la base de datos.");
		if (watchLaterRepository.existsById(entity.getIdWatchLater())) {
			watch_later_movie_dto = new WatchLaterMovieDTO(watchLaterRepository.save(entity), "Success", "Se actualizo con exito.");
		}
		return watch_later_movie_dto;
	}

}
