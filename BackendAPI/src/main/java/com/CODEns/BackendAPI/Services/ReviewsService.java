package com.CODEns.BackendAPI.Services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CODEns.BackendAPI.DTOs.MovieReviewDTO;
import com.CODEns.BackendAPI.Entities.MovieReview;
import com.CODEns.BackendAPI.Interfaces.ServiceInterface;
import com.CODEns.BackendAPI.Repositories.ReviewRepository;

@Service
public class ReviewsService implements ServiceInterface<MovieReview, MovieReviewDTO> {

	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	public MovieReviewDTO save(MovieReview entity) {
		MovieReview new_review = reviewRepository.save(entity);
		MovieReviewDTO review_dto;
		if (new_review.getIdReview() > 0) {
			review_dto = new MovieReviewDTO(new_review, "Success", "Se almaceno con exito.");
		} else {
			review_dto = new MovieReviewDTO("Error", "No se pudo guardar en la base de datos.");
		}
		return review_dto;
	}

	@Override
	public List<MovieReviewDTO> findAll() {
		List<MovieReviewDTO> reviews_dto = new LinkedList<>();
		for(MovieReview review: reviewRepository.findAll()) {
			reviews_dto.add(new MovieReviewDTO(review));
		}
		return reviews_dto;
	}

	@Override
	public MovieReviewDTO getById(Integer id) {
		MovieReviewDTO review_dto = new MovieReviewDTO("Error", "No se encontro en la base de datos.");
		if (reviewRepository.existsById(id)) {
			MovieReview review = reviewRepository.findById(id).get();
			review_dto = new MovieReviewDTO(review, "Success", "Se obtuvo con exito.");
		}
		return review_dto;
	}

	@Override
	public MovieReviewDTO deleteById(Integer id) {
		MovieReviewDTO review_dto = new MovieReviewDTO("Error", "No existe en la base de datos.");
		if (reviewRepository.existsById(id)) {
			reviewRepository.deleteById(id);
			review_dto.setMessage("Se elimino con exito.");
			review_dto.setStatus("Success");
		}
		return review_dto;
	}

	@Override
	public MovieReviewDTO update(MovieReview entity) {
		MovieReviewDTO review_dto = new MovieReviewDTO("Error", "No se encontro en la base de datos.");
		if (reviewRepository.existsById(entity.getIdReview())) {
			review_dto = new MovieReviewDTO(reviewRepository.save(entity), "Success", "Se actualizo con exito.");
		}
		return review_dto;
	}

	public List<MovieReviewDTO> findAllByIdMovie(Integer idMovie) {
		List<MovieReviewDTO> reviews_dto = new LinkedList<>();
		for(MovieReview review: reviewRepository.findAllByIdMovie(idMovie)) {
			reviews_dto.add(new MovieReviewDTO(review));
		}
		return reviews_dto;
	}

}
