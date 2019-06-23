package com.CODEns.BackendAPI.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.CODEns.BackendAPI.Entities.Movie;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
	public Movie findByNameAndYear(String name, int year);
}
