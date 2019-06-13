package com.CODEns.BackendAPI.Entities;

public class MovieFavorite {
    private int id_favorite_movie;
    private int id_movie;

    public MovieFavorite(int id_fav, int id_mov) {
        this.id_favorite_movie = id_fav;
        id_movie = id_mov;
    }

    public int getId() { return id_favorite_movie; }

    public int getIdMovie() { return id_movie; }
}