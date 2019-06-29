package com.CODEns.BackendAPI.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.CODEns.BackendAPI.Entities.MovieReview;
import java.util.List;

public interface ReviewRepository extends CrudRepository<MovieReview, Integer> {

    List<MovieReview> findAllByIdMovie(Integer idMovie);
}
