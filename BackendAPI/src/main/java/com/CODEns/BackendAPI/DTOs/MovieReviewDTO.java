package com.CODEns.BackendAPI.DTOs;

import com.CODEns.BackendAPI.Entities.MovieReview;

public class MovieReviewDTO {
	private int idReview;
    private double grade;
	private String review;
	private String publishedDate;
	private String publishedTime;
	private String reviewTitle;
    private int idUser;
    private int idMovie;
    private String status;
    private String message;

    public MovieReviewDTO(MovieReview review, String status, String message) {
        this.idReview = review.getIdReview();
        this.grade = review.getGrade();
        this.review = review.getReview();
        this.idUser = review.getIdUser();
		this.idMovie = review.getIdMovie();
		this.publishedDate = review.getPublishedDate();
		this.publishedTime = review.getPublishedTime();
        this.status = status;
		this.message = message;
    }
    
    public MovieReviewDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }
    
    public MovieReviewDTO(MovieReview review) {
    	this.idReview = review.getIdReview();
        this.grade = review.getGrade();
        this.review = review.getReview();
        this.idUser = review.getIdUser();
		this.idMovie = review.getIdMovie();
		this.publishedDate = review.getPublishedDate();
		this.publishedTime = review.getPublishedTime();
    }

	public int getIdReview() {
		return idReview;
	}
	public void setIdReview(int idReview) {
		this.idReview = idReview;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdMovie() {
		return idMovie;
	}
	public void setIdMovie(int idMovie) {
		this.idMovie = idMovie;
	}

	public void setPublishedDate(String date) {
		this.publishedDate = date;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedTime(String time) {
		this.publishedTime = time;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String title) {
		this.reviewTitle = title;
	}

	public String getPublishedTime() {
		return publishedTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
