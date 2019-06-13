package com.CODEns.BackendAPI.Entities;

public class MovieLink {
    private int id_link_movie;
    private String link;

    public MovieLink(int id, String link) {
        id_link_movie = id;
        this.link = link;
    }

    public int getId() { return id_link_movie; }

    public String getLink() { return link; }
}