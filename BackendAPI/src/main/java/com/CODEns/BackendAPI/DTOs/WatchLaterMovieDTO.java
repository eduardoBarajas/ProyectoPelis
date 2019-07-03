package com.CODEns.BackendAPI.DTOs;

import com.CODEns.BackendAPI.Entities.WatchLaterMovie;

public class WatchLaterMovieDTO {
	private int idWatchLater;
    private int idMovie;
    private int idUser;
    private String status;
    private String message;

    public WatchLaterMovieDTO(WatchLaterMovie watch_later, String status, String message) {
        this.idWatchLater = watch_later.getIdWatchLater();
        this.idMovie = watch_later.getIdMovie();
        this.idUser = watch_later.getIdUser();
        this.status = status;
        this.message = message;
    }
    
    public WatchLaterMovieDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }
    
    public WatchLaterMovieDTO(WatchLaterMovie watch_later) {
        this.idWatchLater = watch_later.getIdWatchLater();
        this.idMovie = watch_later.getIdMovie();
        this.idUser = watch_later.getIdUser();
    }

	public int getIdWatchLater() {
		return idWatchLater;
	}

	public void setidWatchLater(int idWatchLater) {
		this.idWatchLater = idWatchLater;
	}

	public int getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(int idMovie) {
		this.idMovie = idMovie;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
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
