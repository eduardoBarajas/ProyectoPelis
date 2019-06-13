package com.CODEns.BackendAPI.Entities;

public class MovieLinkDown {
    private int id_link_down;
    private int id_link_movie;
    private String down_date;

    public MovieLinkDown(int id_down, int id_movie_link, String date) {
        id_link_down = id_down;
        id_link_movie = id_movie_link;
        down_date = date;
    }

    public int getId() { return id_link_down; }

    public int getIdLinkMovie() { return id_link_movie; }

    public String getDownDate() { return down_date; }
}