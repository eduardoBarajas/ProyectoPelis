package com.CODEns.BackendAPI.DTOs;


import com.CODEns.BackendAPI.Entities.Movie;

public class MovieDTO {
	private Integer idMovie;
    private String title;
    private String originalTitle;
    private String synopsis;
    private Integer lenght;
    private String posterLink;
    private String tags;
    private String genres;
    private String cast;
    private Double grade;
    private Integer year;
    private String creationDate;
    private String status;
    private String message;

    public MovieDTO(Movie mv, String Status, String Message) {
    	this.idMovie = mv.getIdMovie();
    	this.lenght = mv.getLenght();
    	this.originalTitle = mv.getOriginalTitle();
    	this.posterLink = mv.getPosterLink();
    	this.synopsis = mv.getSynopsis();
    	this.title = mv.getTitle();
    	this.creationDate = mv.getCreationDate();
		this.year = mv.getYear();
		this.cast = mv.getCast();
		this.grade = mv.getGrade();
		this.tags = mv.getTags();
		this.genres = mv.getGenres();
    	this.status = Status;
    	this.message = Message;
    }
    
    public MovieDTO(String Status, String Message) {
    	this.status = Status;
    	this.message = Message;
    }
    
    public MovieDTO(Movie mv) {
    	this.idMovie = mv.getIdMovie();
    	this.lenght = mv.getLenght();
    	this.originalTitle = mv.getOriginalTitle();
    	this.posterLink = mv.getPosterLink();
    	this.synopsis = mv.getSynopsis();
    	this.title = mv.getTitle();
    	this.creationDate = mv.getCreationDate();
		this.year = mv.getYear();
		this.cast = mv.getCast();
		this.grade = mv.getGrade();
		this.tags = mv.getTags();
		this.genres = mv.getGenres();
    }

	public Integer getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(int idMovie) {
		this.idMovie = idMovie;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Integer getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}

	public String getPosterLink() {
		return posterLink;
	}

	public void setPosterLink(String posterLink) {
		this.posterLink = posterLink;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
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
