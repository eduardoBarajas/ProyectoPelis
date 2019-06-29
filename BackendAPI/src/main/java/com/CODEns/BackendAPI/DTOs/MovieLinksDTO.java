package com.CODEns.BackendAPI.DTOs;

import java.util.LinkedList;
import java.util.List;

import com.CODEns.BackendAPI.Entities.MovieLinks;

public class MovieLinksDTO {
	private int idLinkMovie;
    private int idMovie;
    private String link;
    private String status;
    private String message;

    public MovieLinksDTO(MovieLinks movieLink, String status, String message) {
        this.idLinkMovie = movieLink.getIdLinkMovie();
        this.idMovie = movieLink.getIdMovie();
        this.link = movieLink.getLink();
        this.status = status;
        this.message = message;
    }
    
    public MovieLinksDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }
    
    public MovieLinksDTO(MovieLinks movieLink) {
        this.idLinkMovie = movieLink.getIdLinkMovie();
        this.idMovie = movieLink.getIdMovie();
        this.link = movieLink.getLink();
    }

	public int getIdLinkMovie() {
		return idLinkMovie;
	}

	public void setIdLinkMovie(int idLinkMovie) {
		this.idLinkMovie = idLinkMovie;
	}

	public int getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(int idMovie) {
		this.idMovie = idMovie;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
