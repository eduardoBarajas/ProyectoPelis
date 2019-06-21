package com.CODEns.BackendAPI.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WatchLaterMovie {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int IdWatchLater;
    private int IdMovie;
    private int IdUser;

    public WatchLaterMovie(int IdWatchLater, int IdMovie, int IdUser) {
        this.IdWatchLater = IdWatchLater;
        this.IdMovie = IdMovie;
        this.IdUser = IdUser;
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
}