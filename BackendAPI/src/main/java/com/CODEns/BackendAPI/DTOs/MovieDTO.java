package com.CODEns.BackendAPI.DTOs;


import com.CODEns.BackendAPI.Entities.Movie;

public class MovieDTO {
	private Integer idMovie;
    private String name;
    private String originalName;
    private String synopsis;
    private Integer length;
    private String poster;
    private String tags;
    private String genres;
    private String cast;
    private Double grade;
    private Integer year;
    private String creationDate;
    private String modificationDate;
    private String status;
    private String message;

    public MovieDTO(Movie mv, String Status, String Message) {
    	this.idMovie = mv.getIdMovie();
    	this.length = mv.getLength();
    	this.originalName = mv.getOriginalName();
    	this.poster = mv.getPoster();
    	this.synopsis = mv.getSynopsis();
    	this.name = mv.getName();
    	this.creationDate = mv.getCreationDate();
		this.year = mv.getYear();
		this.cast = mv.getCast();
		this.grade = mv.getGrade();
		this.tags = mv.getTags();
		this.genres = mv.getGenres();
		this.modificationDate = mv.getModificationDate();
    	this.status = Status;
    	this.message = Message;
    }
    
    public MovieDTO(String Status, String Message) {
    	this.status = Status;
    	this.message = Message;
    }
    
    public MovieDTO(String Status, String Message, int idMovie, String name) {
    	this.status = Status;
    	this.message = Message;
    	this.idMovie = idMovie;
    	this.name = name;
    }
    
    public MovieDTO(Movie mv) {
    	this.idMovie = mv.getIdMovie();
    	this.length = mv.getLength();
    	this.originalName = mv.getOriginalName();
    	this.poster = mv.getPoster();
    	this.synopsis = mv.getSynopsis();
    	this.name = mv.getName();
    	this.creationDate = mv.getCreationDate();
		this.year = mv.getYear();
		this.cast = mv.getCast();
		this.grade = mv.getGrade();
		this.tags = mv.getTags();
		this.genres = mv.getGenres();
		this.modificationDate = mv.getModificationDate();
    }

	public Integer getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(Integer idMovie) {
		this.idMovie = idMovie;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
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

	public String getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
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
