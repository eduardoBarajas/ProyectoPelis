package com.CODEns.BackendAPI.Entities;

public class Tag {
    private int id_tag;
    private String title;

    public Tag(int id, String title) {
        this.id_tag = id;
        this.title = title;
    }

    public String getTitle() { return title; }

    public int getId() { return id_tag; }
}