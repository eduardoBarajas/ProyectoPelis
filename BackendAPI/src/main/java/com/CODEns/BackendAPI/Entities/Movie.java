package com.CODEns.BackendAPI.Entities;

public class Movie {
    private int id_movie;
    private String title;
    private String original_title;
    private String synopsis;
    private int id_tags;
    private int id_genres;
    private int lenght;
    private String poster_link;
    private int id_cast;
    private int id_reviews;
    private int id_movie_links;

    public Movie(String title) {
        this.title = title;
    }

    public int getId() { return id_movie; }

    public int getIdTags() { return id_tags; }

    public int getIdGenres() { return id_genres; }

    public int getIdCast() { return id_cast; }

    public int getIdReviews() { return id_reviews; }

    public int getIdMovieLinks() { return id_movie_links; }

    public int getLenght() { return lenght; }

    public String getTitle() { return title; }

    public String getOriginalTitle() { return original_title; }

    public String getSynopsis() { return synopsis; }

    public String getPosterLink() { return poster_link; }

}