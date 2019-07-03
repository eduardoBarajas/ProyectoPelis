package com.CODEns.BackendAPI.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FavoriteMovies {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int idFavorites;
    private int idMovie;
    private int idUser;
    

    public FavoriteMovies(int id_fav, int id_mov, int idUser) {
        this.idFavorites = id_fav;
        idMovie = id_mov;
        this.idUser = idUser;
    }

    public FavoriteMovies() { }

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
}
