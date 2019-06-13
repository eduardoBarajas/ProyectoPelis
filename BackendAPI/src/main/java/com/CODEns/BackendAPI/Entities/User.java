package com.CODEns.BackendAPI.Entities;

public class User {
    private int id_user;
    private String name;
    private String username;
    private String private_key;
    private String email;
    private String creation_date;
    private int role;

    public User(String user) {
        this.username = user;
    }

    public String getName() { return name; }

    public String getUsername() { return this.username; }

    public int getId() { return this.id_user; }

    public String getPrivateKey() { return private_key; }

    public String getEmail() { return email; }

    public String getCreationDate() { return creation_date; }

    public int getRole() { return role; }

}