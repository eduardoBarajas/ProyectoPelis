package com.CODEns.BackendAPI.DTOs;

import com.CODEns.BackendAPI.Entities.FavoriteMovies;

public class FavoriteMoviesDTO {
	private int idFavorites;
    private int idMovie;
    private int idUser;
    private String status;
    private String message;

    public FavoriteMoviesDTO(FavoriteMovies fav, String status, String message) {
        this.idFavorites = fav.getIdFavorites();
        this.idMovie = fav.getIdMovie();
        this.idUser = fav.getIdUser();
        this.status = status;
        this.message = message;
    }
    
    public FavoriteMoviesDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }
    
    public FavoriteMoviesDTO(FavoriteMovies fav) {
        this.idFavorites = fav.getIdFavorites();
        this.idMovie = fav.getIdMovie();
        this.idUser = fav.getIdUser();
    }

	public int getIdFavorites() {
		return idFavorites;
	}

	public void setIdFavorites(int idFavorites) {
		this.idFavorites = idFavorites;
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
