package com.CODEns.BackendAPI.DTOs;

import com.CODEns.BackendAPI.Entities.MovieLinks;

public class MovieLinksDTO {
	private int IdLinkMovie;
    private int IdMovie;
    private String Status;
    private String Message;

    public MovieLinksDTO(MovieLinks movieLink, String status, String message) {
        this.IdLinkMovie = movieLink.getIdLinkMovie();
        this.IdMovie = movieLink.getIdMovie();
        this.Status = status;
        this.Message = message;
    }
    
    public MovieLinksDTO(String status, String message) {
        this.Status = status;
        this.Message = message;
    }
    
    public MovieLinksDTO(MovieLinks movieLink) {
        this.IdMovie = movieLink.getIdMovie();
        this.IdLinkMovie = movieLink.getIdLinkMovie();
    }

	public int getIdLinkMovie() {
		return IdLinkMovie;
	}

	public void setIdLinkMovie(int idLinkMovie) {
		IdLinkMovie = idLinkMovie;
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
