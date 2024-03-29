package com.CODEns.BackendAPI.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MovieReview {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int idReview;
    private double grade;
	private String review;
	private String reviewTitle;
	private String publishedDate;
	private String publishedTime;
    private String username;
    private int idUser;
    private int idMovie;

    public MovieReview() { }

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

	public String getPublishedTime() {
		return publishedTime;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String title) {
		this.reviewTitle = title;
	}

    public String getUsername() {
		return username;
	}

	public void setUsername(String user) {
		this.username = user;
	}
}
