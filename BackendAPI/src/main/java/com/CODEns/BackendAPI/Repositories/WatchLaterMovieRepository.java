package com.CODEns.BackendAPI.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

import com.CODEns.BackendAPI.Entities.WatchLaterMovie;

public interface WatchLaterMovieRepository extends CrudRepository<WatchLaterMovie, Integer>{

    List<WatchLaterMovie> findAllByIdUser(int idUser);

    WatchLaterMovie findByIdMovie(int idMovie);
    
    @Modifying
    @Query(value = "DELETE FROM watch_later_movie WHERE id_movie = ?1", nativeQuery = true)
    Integer deleteByIdMovie(int idMovie);
}
