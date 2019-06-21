package com.CODEns.BackendAPI.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.CODEns.BackendAPI.Entities.MovieReview;

public interface ReviewRepository extends CrudRepository<MovieReview, Integer> {

}
