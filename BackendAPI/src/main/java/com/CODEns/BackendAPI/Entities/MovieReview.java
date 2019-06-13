package com.CODEns.BackendAPI.Entities;

public class MovieReview {
    private int id_review;
    private double calification;
    private String review;
    private int id_user;

    public MovieReview(int id, int id_user, double cal, String rev) {
        id_review = id;
        this.id_user = id_user;
        calification = cal;
        review = rev;
    }

    public int getId() { return id_review; }

    public double getCalification() { return calification; }

    public String getReview() { return review; }

    public int getIdUser() { return id_user; }

}