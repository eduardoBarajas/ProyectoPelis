package com.CODEns.BackendAPI.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MovieLinks {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int IdLinkMovie;
    private int IdMovie;

    public MovieLinks(int id_link, int id_movie) {
        this.IdLinkMovie = id_link;
        this.IdMovie = id_movie;
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
    
    
}