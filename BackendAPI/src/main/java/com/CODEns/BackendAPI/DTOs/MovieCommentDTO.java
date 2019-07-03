package com.CODEns.BackendAPI.DTOs;

import com.CODEns.BackendAPI.Entities.MovieComment;

public class MovieCommentDTO {
	private int idComment;
	private String comment;
	private String publishedDate;
	private String publishedTime;
    private int idUser;
    private int idMovie;
    private String username;
    private String status;
    private String message;

    public MovieCommentDTO(MovieComment comment, String status, String message) {
        this.idComment = comment.getIdComment();
        this.comment = comment.getComment();
        this.idUser = comment.getIdUser();
		this.idMovie = comment.getIdMovie();
		this.publishedDate = comment.getPublishedDate();
		this.publishedTime = comment.getPublishedTime();
        this.username = comment.getUsername();
        this.status = status;
		this.message = message;
    }
    
    public MovieCommentDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }
    
    public MovieCommentDTO(MovieComment comment) {
    	this.idComment = comment.getIdComment();
        this.comment = comment.getComment();
        this.idUser = comment.getIdUser();
		this.idMovie = comment.getIdMovie();
		this.publishedDate = comment.getPublishedDate();
		this.publishedTime = comment.getPublishedTime();
        this.username = comment.getUsername();
    }

	public int getIdComment() {
		return idComment;
	}
	public void setIdComment(int idComment) {
		this.idComment = idComment;
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

    public String getUsername() {
		return username;
	}

	public void setUsername(String user) {
		this.username = user;
	}
	
}
