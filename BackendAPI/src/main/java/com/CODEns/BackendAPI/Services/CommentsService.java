package com.CODEns.BackendAPI.Services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CODEns.BackendAPI.DTOs.MovieCommentDTO;
import com.CODEns.BackendAPI.Entities.MovieComment;
import com.CODEns.BackendAPI.Interfaces.ServiceInterface;
import com.CODEns.BackendAPI.Repositories.CommentRepository;

@Service
public class CommentsService implements ServiceInterface<MovieComment, MovieCommentDTO> {

	@Autowired
	private CommentRepository commentRepository;

	@Override
	public MovieCommentDTO save(MovieComment entity) {
		MovieComment new_comment = commentRepository.save(entity);
		MovieCommentDTO comment_dto;
		if (new_comment.getIdComment() > 0) {
			comment_dto = new MovieCommentDTO(new_comment, "Success", "Se almaceno con exito.");
		} else {
			comment_dto = new MovieCommentDTO("Error", "No se pudo guardar en la base de datos.");
		}
		return comment_dto;
	}

	@Override
	public List<MovieCommentDTO> findAll() {
		List<MovieCommentDTO> comments_dto = new LinkedList<>();
		for(MovieComment comment: commentRepository.findAll()) {
			comments_dto.add(new MovieCommentDTO(comment));
		}
		return comments_dto;
	}

	@Override
	public MovieCommentDTO getById(Integer id) {
		MovieCommentDTO comment_dto = new MovieCommentDTO("Error", "No se encontro en la base de datos.");
		if (commentRepository.existsById(id)) {
			MovieComment comment = commentRepository.findById(id).get();
			comment_dto = new MovieCommentDTO(comment, "Success", "Se obtuvo con exito.");
		}
		return comment_dto;
	}

	@Override
	public MovieCommentDTO deleteById(Integer id) {
		MovieCommentDTO comment_dto = new MovieCommentDTO("Error", "No existe en la base de datos.");
		if (commentRepository.existsById(id)) {
			commentRepository.deleteById(id);
			comment_dto.setMessage("Se elimino con exito.");
			comment_dto.setStatus("Success");
		}
		return comment_dto;
	}

	@Override
	public MovieCommentDTO update(MovieComment entity) {
		MovieCommentDTO comment_dto = new MovieCommentDTO("Error", "No se encontro en la base de datos.");
		if (commentRepository.existsById(entity.getIdComment())) {
			comment_dto = new MovieCommentDTO(commentRepository.save(entity), "Success", "Se actualizo con exito.");
		}
		return comment_dto;
	}

	public List<MovieCommentDTO> findAllByIdMovie(Integer idMovie) {
		List<MovieCommentDTO> comments_dto = new LinkedList<>();
		for(MovieComment comment: commentRepository.findAllByIdMovie(idMovie)) {
			comments_dto.add(new MovieCommentDTO(comment));
		}
		return comments_dto;
	}

}
