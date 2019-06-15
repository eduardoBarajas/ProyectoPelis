package com.CODEns.BackendAPI.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_user;
    private String name;
    private String username;
    private String private_key;
    private String email;
    private String creation_date;
    private int role;

    public User(String user) {
        this.name = user;
    }
    public User() {

    }

    public String getName() { return name; }

    public String getUsername() { return this.username; }

    public int getId() { return this.id_user; }

    public String getPrivateKey() { return private_key; }

    public String getEmail() { return email; }

    public String getCreationDate() { return creation_date; }

    public int getRole() { return role; }

    public void setName(String name) { this.name = name; }

    public void setUsername(String username) { this.username = username; }

    public void setId(int id_user) { this.id_user = id_user; }

    public void setPrivateKey(String private_key) { this.private_key = private_key; }

    public void setEmail(String email) { this.email = email; }

    public void setCreationDate(String date) { this.creation_date = date; }

    public void setRole(int role) { this.role = role; }
}