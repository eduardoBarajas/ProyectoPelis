package com.CODEns.BackendAPI.Entities;

public class Actor {
    private int id_actor;
    private String name;

    public Actor(int id, String name) {
        this.id_actor = id;
        this.name = name;
    }

    public String getName() { return name; }

    public int getId() { return id_actor; }
}