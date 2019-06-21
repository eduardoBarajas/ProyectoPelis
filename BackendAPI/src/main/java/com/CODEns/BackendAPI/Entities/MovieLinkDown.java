package com.CODEns.BackendAPI.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MovieLinkDown {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int IdLinkDown;
    private int IdLinkMovie;
    private String DownDate;
    private boolean Repaired;

    public MovieLinkDown(int id_down, int id_movie_link, String date, boolean repaired) {
        IdLinkDown = id_down;
        IdLinkMovie = id_movie_link;
        DownDate = date;
        Repaired = repaired;
    }

	public int getIdLinkDown() {
		return IdLinkDown;
	}

	public void setIdLinkDown(int IdLinkDown) {
		this.IdLinkDown = IdLinkDown;
	}

	public int getIdLinkMovie() {
		return IdLinkMovie;
	}

	public void setIdLinkMovie(int IdLinkMovie) {
		this.IdLinkMovie = IdLinkMovie;
	}

	public String getDownDate() {
		return DownDate;
	}

	public void setDownDate(String DownDate) {
		this.DownDate = DownDate;
	}

	public boolean isRepaired() {
		return Repaired;
	}

	public void setRepaired(boolean repaired) {
		Repaired = repaired;
	}
	
	

    
}