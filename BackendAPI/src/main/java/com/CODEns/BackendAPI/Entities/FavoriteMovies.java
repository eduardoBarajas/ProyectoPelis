package com.CODEns.BackendAPI.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FavoriteMovies {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int IdFavorites;
    private int IdMovie;
    private int IdUser;
    

    public FavoriteMovies(int id_fav, int id_mov, int IdUser) {
        this.IdFavorites = id_fav;
        IdMovie = id_mov;
        this.IdUser = IdUser;
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
}