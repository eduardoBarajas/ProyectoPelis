package com.CODEns.BackendAPI.Entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.CODEns.BackendAPI.DTOs.MovieDTO;
@Entity
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idMovie;
	@Column(name="name", unique=true)
    private String name;
    private String originalName;
    @Column(name="synopsis",columnDefinition="LONGTEXT")
    private String synopsis;
    private Integer length;
	private String poster;
	@Column(name="tags",columnDefinition="LONGTEXT")
    private String tags;
    private String genres;
    private String cast;
    private Double grade;
	private Integer year;
    private String creationDate;
    private String modificationDate;

    public Movie() {
    	
    }

    public Movie(MovieDTO movie) {
        this.idMovie = movie.getIdMovie();
        this.name = movie.getName();
        this.originalName = movie.getOriginalName();
        this.synopsis = movie.getSynopsis();
        this.length = movie.getLength();
        this.poster = movie.getPoster();
        this.tags = movie.getTags();
        this.genres = movie.getGenres();
        this.cast = movie.getCast();
        this.grade = movie.getGrade();
        this.year = movie.getYear();
        this.creationDate = movie.getCreationDate();
        this.modificationDate = movie.getModificationDate();
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
}
