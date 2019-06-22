package com.CODEns.BackendAPI.Repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.CODEns.BackendAPI.Entities.MovieLinks;
public interface MovieLinksRepository extends CrudRepository<MovieLinks, Integer> {
	public List<MovieLinks> findAllByIdMovie(Integer idMovie);
	public MovieLinks findByLinkAndIdMovie(String link, Integer idMovie);
	public void deleteAllByIdMovie(Integer idMovie);
}
