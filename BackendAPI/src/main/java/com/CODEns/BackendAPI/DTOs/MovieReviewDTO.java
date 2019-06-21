package com.CODEns.BackendAPI.DTOs;

import com.CODEns.BackendAPI.Entities.MovieReview;

public class MovieReviewDTO {
	private int IdReview;
    private double Grade;
    private String Review;
    private int IdUser;
    private int IdMovie;
    private String Status;
    private String Message;

    public MovieReviewDTO(MovieReview review, String status, String message) {
        this.IdReview = review.getIdReview();
        this.Grade = review.getGrade();
        this.Review = review.getReview();
        this.IdUser = review.getIdUser();
        this.IdMovie = review.getIdMovie();
        this.Status = status;
        this.Message = message;
    }
    
    public MovieReviewDTO(String status, String message) {
        this.Status = status;
        this.Message = message;
    }
    
    public MovieReviewDTO(MovieReview review) {
    	this.IdReview = review.getIdReview();
        this.Grade = review.getGrade();
        this.Review = review.getReview();
        this.IdUser = review.getIdUser();
        this.IdMovie = review.getIdMovie();
    }

	public int getIdReview() {
		return IdReview;
	}

	public void setIdReview(int idReview) {
		IdReview = idReview;
	}

	public double getGrade() {
		return Grade;
	}

	public void setGrade(double grade) {
		Grade = grade;
	}

	public String getReview() {
		return Review;
	}

	public void setReview(String review) {
		Review = review;
	}

	public int getIdUser() {
		return IdUser;
	}

	public void setIdUser(int idUser) {
		IdUser = idUser;
	}

	public int getIdMovie() {
		return IdMovie;
	}

	public void setIdMovie(int idMovie) {
		IdMovie = idMovie;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}
	
}
