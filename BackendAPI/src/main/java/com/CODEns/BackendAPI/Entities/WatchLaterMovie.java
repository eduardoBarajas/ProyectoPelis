package com.CODEns.BackendAPI.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WatchLaterMovie {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int idWatchLater;
    private int idMovie;
    private int idUser;

    public WatchLaterMovie(int idWatchLater, int idMovie, int idUser) {
        this.idWatchLater = idWatchLater;
        this.idMovie = idMovie;
        this.idUser = idUser;
    }

    public WatchLaterMovie() { }

	public int getIdWatchLater() {
		return idWatchLater;
	}

	public void setIdWatchLater(int idWatchLater) {
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
}
