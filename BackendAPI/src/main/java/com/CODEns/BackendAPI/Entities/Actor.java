package com.CODEns.BackendAPI.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Actor {
	@Id
	@GeneratedValue
    private int id_actor;
    private String name;

    public Actor(int id, String name) {
        this.id_actor = id;
        this.name = name;
    }

    public String getName() { return name; }

    public int getId() { return id_actor; }
}