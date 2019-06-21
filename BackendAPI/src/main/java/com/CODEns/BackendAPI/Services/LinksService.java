package com.CODEns.BackendAPI.Services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CODEns.BackendAPI.DTOs.MovieLinkDownDTO;
import com.CODEns.BackendAPI.DTOs.MovieLinksDTO;
import com.CODEns.BackendAPI.Entities.MovieLinkDown;
import com.CODEns.BackendAPI.Entities.MovieLinks;
import com.CODEns.BackendAPI.Repositories.MovieLinkDownRepository;
import com.CODEns.BackendAPI.Repositories.MovieLinksRepository;

@Service
public class LinksService {
	
	@Autowired
	private MovieLinkDownRepository movieLinkDownRepository;
	
	@Autowired
	private MovieLinksRepository movieLinksRepository;
	
	public MovieLinkDownDTO saveLinkDown(MovieLinkDown entity) {
		MovieLinkDown new_link_down = movieLinkDownRepository.save(entity);
		MovieLinkDownDTO link_down_dto;
		if (new_link_down.getIdLinkDown() > 0) {
			link_down_dto = new MovieLinkDownDTO(new_link_down, "Success", "Se almaceno con exito.");
		} else {
			link_down_dto = new MovieLinkDownDTO("Error", "No se pudo guardar en la base de datos.");
		}
		return link_down_dto;
	}

	public List<MovieLinkDownDTO> findAllLinksDown() {
		List<MovieLinkDownDTO> links_down_dto = new LinkedList<>();
		for(MovieLinkDown link: movieLinkDownRepository.findAll()) {
			links_down_dto.add(new MovieLinkDownDTO(link));
		}
		return links_down_dto;
	}

	public MovieLinkDownDTO getLinkDownById(Integer id) {
		MovieLinkDownDTO link_down_dto = new MovieLinkDownDTO("Error", "No se encontro en la base de datos.");
		if (movieLinkDownRepository.existsById(id)) {
			MovieLinkDown link_down = movieLinkDownRepository.findById(id).get();
			link_down_dto = new MovieLinkDownDTO(link_down, "Success", "Se obtuvo con exito.");
		}
		return link_down_dto;
	}

	public MovieLinkDownDTO deleteLinkDownById(Integer id) {
		MovieLinkDownDTO link_down_dto = new MovieLinkDownDTO("Error", "No existe en la base de datos.");
		if (movieLinkDownRepository.existsById(id)) {
			movieLinkDownRepository.deleteById(id);
			link_down_dto.setMessage("Se elimino con exito.");
			link_down_dto.setStatus("Success");
		}
		return link_down_dto;
	}

	public MovieLinkDownDTO updateLinkDown(MovieLinkDown entity) {
		MovieLinkDownDTO link_down_dto = new MovieLinkDownDTO("Error", "No se encontro en la base de datos.");
		if (movieLinkDownRepository.existsById(entity.getIdLinkDown())) {
			link_down_dto = new MovieLinkDownDTO(movieLinkDownRepository.save(entity), "Success", "Se actualizo con exito.");
		}
		return link_down_dto;
	}
	
	public MovieLinksDTO saveLinksMovie(MovieLinks entity) {
		MovieLinks new_link = movieLinksRepository.save(entity);
		MovieLinksDTO link_dto;
		if (new_link.getIdLinkMovie() > 0) {
			link_dto = new MovieLinksDTO(new_link, "Success", "Se almaceno con exito.");
		} else {
			link_dto = new MovieLinksDTO("Error", "No se pudo guardar en la base de datos.");
		}
		return link_dto;
	}

	public List<MovieLinksDTO> findAllMovieLinks() {
		List<MovieLinksDTO> links_dto = new LinkedList<>();
		for(MovieLinks link: movieLinksRepository.findAll()) {
			links_dto.add(new MovieLinksDTO(link));
		}
		return links_dto;
	}

	public MovieLinksDTO getMovieLinksById(Integer id) {
		MovieLinksDTO link_dto = new MovieLinksDTO("Error", "No se encontro en la base de datos.");
		if (movieLinksRepository.existsById(id)) {
			MovieLinks link = movieLinksRepository.findById(id).get();
			link_dto = new MovieLinksDTO(link, "Success", "Se obtuvo con exito.");
		}
		return link_dto;
	}

	public MovieLinksDTO deleteMovieLinkById(Integer id) {
		MovieLinksDTO link_dto = new MovieLinksDTO("Error", "No existe en la base de datos.");
		if (movieLinksRepository.existsById(id)) {
			movieLinksRepository.deleteById(id);
			link_dto.setMessage("Se elimino con exito.");
			link_dto.setStatus("Success");
		}
		return link_dto;
	}

	public MovieLinksDTO updateMovieLinks(MovieLinks entity) {
		MovieLinksDTO link_dto = new MovieLinksDTO("Error", "No se encontro en la base de datos.");
		if (movieLinkDownRepository.existsById(entity.getIdLinkMovie())) {
			link_dto = new MovieLinksDTO(movieLinksRepository.save(entity), "Success", "Se actualizo con exito.");
		}
		return link_dto;
	}

}
