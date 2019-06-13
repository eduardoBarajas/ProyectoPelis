package com.CODEns.BackendAPI.Entities;

public class WatchLaterMovie {
    private int id_watch_later_movie;
    private int id_movie;

    public WatchLaterMovie(int id_watch_later_movie, int id_movie) {
        this.id_watch_later_movie = id_watch_later_movie;
        this.id_movie = id_movie;
    }

    public int getId() { return id_watch_later_movie; }

    public int getIdMovie() { return id_movie; }

}