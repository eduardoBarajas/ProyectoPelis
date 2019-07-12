package com.CODEns.BackendAPI.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.CODEns.BackendAPI.Entities.MovieReview;
import java.util.List;

public interface ReviewRepository extends CrudRepository<MovieReview, Integer> {

    @Query(value = "SELECT * FROM movie_review WHERE id_movie = ?1 ORDER BY published_date DESC, published_time DESC;", nativeQuery = true)
    public List<MovieReview> findAllByIdMovie(Integer idMovie);

}
