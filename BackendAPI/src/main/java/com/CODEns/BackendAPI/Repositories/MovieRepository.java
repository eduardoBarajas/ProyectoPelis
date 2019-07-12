package com.CODEns.BackendAPI.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

import com.CODEns.BackendAPI.Entities.Movie;
import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
	
    public Movie findByNameAndYear(String name, int year);
	public List<Movie> findAllByYear(int year);
	public List<Movie> findAllByGenresContaining(String genres);
	
	@Query(value = "SELECT * FROM movie m WHERE m.genres LIKE %?1% AND m.year BETWEEN ?2 AND ?3 AND m.grade BETWEEN ?4 AND ?5 AND m.name LIKE %?6%", nativeQuery = true)
	public List<Movie> findAllByFilterWithName(String genre, int startYear, int endYear, double startRating, double endRating, String name);

    @Query(value = "SELECT * FROM movie m WHERE m.genres LIKE %?1% AND m.year BETWEEN ?2 AND ?3 AND m.grade BETWEEN ?4 AND ?5", nativeQuery = true)
	public List<Movie> findAllByFilterWithoutName(String genre, int startYear, int endYear, double startRating, double endRating);

    @Query(value = "SELECT * FROM movie m WHERE m.year BETWEEN ?1 AND ?2 AND m.grade BETWEEN ?3 AND ?4", nativeQuery = true)
	public List<Movie> findAllByFilterWithoutGenreAndWithoutName(int startYear, int endYear, double startRating, double endRating);
    
    @Query(value = "SELECT * FROM movie m WHERE m.year BETWEEN ?1 AND ?2 AND m.grade BETWEEN ?3 AND ?4 AND m.name LIKE %?5%", nativeQuery = true)
	public List<Movie> findAllByFilterWithoutGenreAndWithName(int startYear, int endYear, double startRating, double endRating, String name);

    @Query(value = "SELECT * FROM movie ORDER BY grade DESC LIMIT 10", nativeQuery = true)
    public List<Movie> findTrending();

    @Query(value = "SELECT * FROM movie ORDER BY creation_date DESC LIMIT 10", nativeQuery = true)
    public List<Movie> findRecents();

    @Query(value = "SELECT id_user FROM user m WHERE EXISTS (SELECT id_user FROM favorite_movies WHERE id_movie = ?1 AND m.id_user = ?2)", nativeQuery = true)
    public Integer findFavoriteWithIdUserAndIdMovie(Integer id_movie, Integer id_user);

    @Query(value = "SELECT id_user FROM user m WHERE EXISTS (SELECT id_user FROM watch_later_movie WHERE id_movie = ?1 AND m.id_user = ?2)", nativeQuery = true)
    public Integer findWatchLaterWithIdUserAndIdMovie(Integer id_movie, Integer id_user);

    @Query(value = "SELECT * FROM movie WHERE genres LIKE %?1% ORDER BY RAND() LIMIT ?2", nativeQuery = true)
    public List<Movie> findMoviesByGenreWithLimit(String genre, Integer limit);

}
