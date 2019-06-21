package com.CODEns.BackendAPI.DTOs;

import com.CODEns.BackendAPI.Entities.WatchLaterMovie;

public class WatchLaterMovieDTO {
	private int IdWatchLater;
    private int IdMovie;
    private int IdUser;
    private String Status;
    private String Message;

    public WatchLaterMovieDTO(WatchLaterMovie watch_later, String status, String message) {
        this.IdWatchLater = watch_later.getIdWatchLater();
        this.IdMovie = watch_later.getIdMovie();
        this.IdUser = watch_later.getIdUser();
        this.Status = status;
        this.Message = message;
    }
    
    public WatchLaterMovieDTO(String status, String message) {
        this.Status = status;
        this.Message = message;
    }
    
    public WatchLaterMovieDTO(WatchLaterMovie watch_later) {
        this.IdWatchLater = watch_later.getIdWatchLater();
        this.IdMovie = watch_later.getIdMovie();
        this.IdUser = watch_later.getIdUser();
    }

	public int getIdWatchLater() {
		return IdWatchLater;
	}

	public void setIdWatchLater(int IdWatchLater) {
		this.IdWatchLater = IdWatchLater;
	}

	public int getIdMovie() {
		return IdMovie;
	}

	public void setIdMovie(int IdMovie) {
		this.IdMovie = IdMovie;
	}

	public int getIdUser() {
		return IdUser;
	}

	public void setIdUser(int IdUser) {
		this.IdUser = IdUser;
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
