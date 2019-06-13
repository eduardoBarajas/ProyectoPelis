package com.CODEns.BackendAPI.Entities;

public class Genre {
    private int id_genre;
    private String title;

    public Genre(int id, String title) {
        this.id_genre = id;
        this.title = title;
    }

    public String getTitle() { return title; }

    public int getId() { return id_genre; }
}