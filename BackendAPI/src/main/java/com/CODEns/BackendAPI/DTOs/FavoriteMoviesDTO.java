package com.CODEns.BackendAPI.DTOs;

import com.CODEns.BackendAPI.Entities.FavoriteMovies;

public class FavoriteMoviesDTO {
	private int IdFavorites;
    private int IdMovie;
    private int IdUser;
    private String Status;
    private String Message;

    public FavoriteMoviesDTO(FavoriteMovies fav, String status, String message) {
        this.IdFavorites = fav.getIdFavorites();
        this.IdMovie = fav.getIdMovie();
        this.IdUser = fav.getIdUser();
        this.Status = status;
        this.Message = message;
    }
    
    public FavoriteMoviesDTO(String status, String message) {
        this.Status = status;
        this.Message = message;
    }
    
    public FavoriteMoviesDTO(FavoriteMovies fav) {
        this.IdFavorites = fav.getIdFavorites();
        this.IdMovie = fav.getIdMovie();
        this.IdUser = fav.getIdUser();
    }

	public int getIdFavorites() {
		return IdFavorites;
	}

	public void setIdFavorites(int IdFavorites) {
		this.IdFavorites = IdFavorites;
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
