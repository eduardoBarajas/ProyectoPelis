package com.CODEns.BackendAPI.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

import com.CODEns.BackendAPI.Entities.FavoriteMovies;

public interface FavoriteMoviesRepository extends CrudRepository<FavoriteMovies, Integer> {

    List<FavoriteMovies> findAllByIdUser(int idUser);

    FavoriteMovies findByIdMovie(int idMovie);

    @Modifying
    @Query(value = "DELETE FROM favorite_movies WHERE id_movie = ?1", nativeQuery = true)
    Integer deleteByIdMovie(int idMovie);
}
