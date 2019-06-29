package com.CODEns.BackendAPI.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import com.CODEns.BackendAPI.Entities.Movie;
import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
	public Movie findByNameAndYear(String name, int year);
	public List<Movie> findAllByYear(int year);
	public List<Movie> findAllByGenresContaining(String genres);
	
	@Query(value = "SELECT * FROM movie m WHERE m.genres LIKE %?1% AND m.year BETWEEN ?2 AND ?3 AND m.grade BETWEEN ?4 AND ?5", nativeQuery = true)
	public List<Movie> findAllByFilter(String genre, int startYear, int endYear, double startRating, double endRating);
}
