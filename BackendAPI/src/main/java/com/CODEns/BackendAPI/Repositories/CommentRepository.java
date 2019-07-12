package com.CODEns.BackendAPI.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.CODEns.BackendAPI.Entities.MovieComment;
import java.util.List;

public interface CommentRepository extends CrudRepository<MovieComment, Integer> {

    @Query(value = "SELECT * FROM movie_comment WHERE id_movie = ?1 ORDER BY published_date DESC, published_time DESC;", nativeQuery = true)
    List<MovieComment> findAllByIdMovie(Integer idMovie);

}
