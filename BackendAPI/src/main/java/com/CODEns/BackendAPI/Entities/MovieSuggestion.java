package com.CODEns.BackendAPI.Entities;

public class MovieSuggestion {
    private int id_suggestion;
    private String suggestion_date;
    private String suggestion;
    private int id_user;

    public MovieSuggestion(int id, int id_user, String date, String sug) {
        id_suggestion = id;
        this.id_user = id_user;
        suggestion_date = date;
        suggestion = sug;
    }

    public int getId() { return id_suggestion; }

    public String getSuggestionDate() { return suggestion_date; }

    public String getSuggestion() { return getSuggestion(); }

    public int getIdUser() { return id_user; }

}