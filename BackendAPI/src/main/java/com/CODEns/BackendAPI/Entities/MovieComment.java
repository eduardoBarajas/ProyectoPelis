package com.CODEns.BackendAPI.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MovieComment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int idComment;
	private String comment;
	private String publishedDate;
	private String publishedTime;
    private int idUser;
    private String username;
    private int idMovie;

	public MovieComment(int idcomment, String comment, int idUser, int idMovie, String date, String time) {
		super();
		this.idComment = idcomment;
		this.comment = comment;
		this.idUser = idUser;
		this.idMovie = idMovie;
		this.publishedDate = date;
		this.publishedTime = time;
	}

    public MovieComment() { }

	public int getIdComment() {
		return idComment;
	}
	public void setIdComment(int idcomment) {
		this.idComment = idcomment;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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

    public String getUsername() {
		return username;
	}

	public void setUsername(String user) {
		this.username = user;
	}
}
