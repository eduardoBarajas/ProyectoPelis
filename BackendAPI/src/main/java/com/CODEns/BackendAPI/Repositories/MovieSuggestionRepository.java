package com.CODEns.BackendAPI.Repositories;
import org.springframework.data.repository.CrudRepository;

import com.CODEns.BackendAPI.Entities.MovieSuggestion;

public interface MovieSuggestionRepository extends CrudRepository<MovieSuggestion, Integer> {

}
