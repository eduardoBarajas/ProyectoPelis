package com.CODEns.BackendAPI.Services;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.CODEns.BackendAPI.DTOs.MovieLinksDTO;
import com.CODEns.BackendAPI.DTOs.ResponseDTO;
import com.CODEns.BackendAPI.Entities.MovieLinks;
import com.CODEns.BackendAPI.Repositories.MovieLinksRepository;

@Transactional
@Service
public class LinksService {
	
	private final EntityManager entityManager;

    @Autowired
    public LinksService(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }

	@Autowired
	private MovieLinksRepository movieLinksRepository;
	
	public HttpStatus replaceAll(List<MovieLinks> links) {
		List<Integer> movies_identifiers = new LinkedList<>();
		Map<Integer, List<String>> links_relation = new HashMap<>();
		for (MovieLinks link : links) {
			if (!movies_identifiers.contains(link.getIdMovie())) {
				movies_identifiers.add(link.getIdMovie());
				links_relation.put(link.getIdMovie(), new LinkedList<>());
			}
			links_relation.get(link.getIdMovie()).add(link.getLink());
		}
		for (Integer id : movies_identifiers) {
			movieLinksRepository.deleteAllByIdMovie(id);
			for (String link : links_relation.get(id)) {
				movieLinksRepository.save(new MovieLinks(id, link));
			}
		}
		return HttpStatus.OK;
	}
	
	public ResponseDTO<MovieLinksDTO> save(List<String> links, int id) {
		ResponseDTO<MovieLinksDTO> response = new ResponseDTO<>();
		MovieLinksDTO link_dto;
		for (String link : links) {
			MovieLinks new_link = movieLinksRepository.save(new MovieLinks(id, link));
			if (new_link.getIdLinkMovie() > 0) {
				link_dto = new MovieLinksDTO(new_link, "Success", "Se almaceno con exito.");
			} else {
				link_dto = new MovieLinksDTO("Error", "No se pudo guardar en la base de datos.");
			}
			response.getResponses().add(link_dto);
		}
		response.setMessage("Se agregaron los links sin errores.");
		response.setStatus("Success");
		for (MovieLinksDTO link : response.getResponses()) {
			if (link.getStatus().equals("Error")) {
				response.setMessage("Hubo problemas al agregar los links.");
				response.setStatus("Error");
				break;
			}
		}
		return response;
	}

	public List<MovieLinksDTO> findAll() {
		List<MovieLinksDTO> links_dto = new LinkedList<>();
		for(MovieLinks link: movieLinksRepository.findAll()) {
			links_dto.add(new MovieLinksDTO(link));
		}
		return links_dto;
	}

	public MovieLinksDTO getById(Integer id) {
		MovieLinksDTO link_dto = new MovieLinksDTO("Error", "No se encontro en la base de datos.");
		if (movieLinksRepository.existsById(id)) {
			MovieLinks link = movieLinksRepository.findById(id).get();
			link_dto = new MovieLinksDTO(link, "Success", "Se obtuvo con exito.");
		}
		return link_dto;
	}
	
	public List<MovieLinksDTO> findAllByIdMovie(Integer id) {
		List<MovieLinksDTO> links_dto = new LinkedList<>();
		for (MovieLinks link : movieLinksRepository.findAllByIdMovie(id)) {
			links_dto.add(new MovieLinksDTO(link));
		}
		return links_dto;
	}

	public MovieLinksDTO deleteById(Integer id) {
		MovieLinksDTO link_dto = new MovieLinksDTO("Error", "No existe en la base de datos.");
		if (movieLinksRepository.existsById(id)) {
			movieLinksRepository.deleteById(id);
			link_dto.setMessage("Se elimino con exito.");
			link_dto.setStatus("Success");
		}
		return link_dto;
	}

    public HttpStatus deleteAllById(List<Integer> ids) {
		boolean success = true;
        for (Integer id: ids) {
            if (movieLinksRepository.existsById(id)) {
                movieLinksRepository.deleteById(id);
            } else {
                success = false;
            }
        }
        if (success) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.PARTIAL_CONTENT;
        }
	}

	public MovieLinksDTO update(MovieLinks entity) {
		MovieLinksDTO link_dto = new MovieLinksDTO("Error", "No se encontro en la base de datos.");
		if (movieLinksRepository.existsById(entity.getIdLinkMovie())) {
			link_dto = new MovieLinksDTO(movieLinksRepository.save(entity), "Success", "Se actualizo con exito.");
		}
		return link_dto;
	}

    public HttpStatus updateAll(List<MovieLinks> links) {
		boolean success = true;
        for (MovieLinks link: links) {
            if (movieLinksRepository.existsById(link.getIdLinkMovie())) {
                if (movieLinksRepository.save(link) == null) {
                    success = false;
                }
            }
        }
        if (success) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.PARTIAL_CONTENT;
        }
	}

    public HttpStatus saveAll(List<MovieLinks> links) {
		boolean success = true;
        for (MovieLinks link: links) {
            if (movieLinksRepository.save(link) == null) {
                success = false;
            }
        }
        if (success) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.PARTIAL_CONTENT;
        }
	}

	public void deleteByIdMovie(Integer id) {
		movieLinksRepository.deleteAllByIdMovie(id);
	}

}
