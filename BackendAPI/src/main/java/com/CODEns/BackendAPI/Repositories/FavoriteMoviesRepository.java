package com.CODEns.BackendAPI.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.CODEns.BackendAPI.Entities.FavoriteMovies;

public interface FavoriteMoviesRepository extends CrudRepository<FavoriteMovies, Integer> {

}
