package com.CODEns.BackendAPI.DTOs;

import java.util.LinkedList;
import java.util.List;
import com.CODEns.BackendAPI.DTOs.MovieReviewDTO;
import com.CODEns.BackendAPI.DTOs.MovieCommentDTO;

public class MovieInteractionsDTO {
	
	private String message;
	private String status;
	private List<MovieReviewDTO> reviews = new LinkedList<>();
    private List<MovieCommentDTO> comments = new LinkedList<>();
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public List<MovieReviewDTO> getReviews() {
		return reviews;
	}

	public void setReviews(List<MovieReviewDTO> reviews) {
		this.reviews = reviews;
	}

    public List<MovieCommentDTO> getComments() {
		return comments;
	}

	public void setComments(List<MovieCommentDTO> comments) {
		this.comments = comments;
	}
}
