package com.CODEns.BackendAPI.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MovieLinks {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idLinkMovie;
    private int idMovie;
    @Column(name="link",columnDefinition="LONGTEXT")
    private String link;

    public MovieLinks() { }
    
    public MovieLinks(int id_link, int id_movie, String link) {
        this.idLinkMovie = id_link;
        this.idMovie = id_movie;
        this.link = link;
    }

    public MovieLinks(int idMovie, String link) {
		this.idMovie = idMovie;
		this.link = link;
	}
    
    public int getIdLinkMovie() {
		return idLinkMovie;
	}

	public void setIdLinkMovie(int idLinkMovie) {
		this.idLinkMovie = idLinkMovie;
	}

	public int getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(int idMovie) {
		this.idMovie = idMovie;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}