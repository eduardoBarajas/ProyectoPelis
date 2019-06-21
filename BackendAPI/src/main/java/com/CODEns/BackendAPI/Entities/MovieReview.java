package com.CODEns.BackendAPI.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MovieReview {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int IdReview;
    private double Grade;
    private String Review;
    private int IdUser;
    private int IdMovie;
	public MovieReview(int idReview, double grade, String review, int idUser, int idMovie) {
		super();
		IdReview = idReview;
		Grade = grade;
		Review = review;
		IdUser = idUser;
		IdMovie = idMovie;
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

}