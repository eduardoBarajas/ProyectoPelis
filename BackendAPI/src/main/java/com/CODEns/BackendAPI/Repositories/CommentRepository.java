package com.CODEns.BackendAPI.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.CODEns.BackendAPI.Entities.MovieComment;
import java.util.List;

public interface CommentRepository extends CrudRepository<MovieComment, Integer> {

    List<MovieComment> findAllByIdMovie(Integer idMovie);
}
