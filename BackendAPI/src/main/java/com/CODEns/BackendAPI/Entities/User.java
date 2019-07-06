package com.CODEns.BackendAPI.Entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/*
 * Entidad de usuario @Entity, esta es la usada para la persistencia de datos.
 * la tabla se creara con @Table, por default se crean los campos de la tabla con los nombres de las propiedades pero
 * este comportamiento puede se cambiado utilizando la anotacion @Column.
 */

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idUser;
    private String name;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(columnDefinition="LONGTEXT")
    private String password;
    private String email;
    private String creationDate;
    private int authority;
    private int enabled;

    public User(int idUser, String name, String username, String password, String email, String creationDate, int authority, int enabled) {
        this.name = name;
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.email = email;
        this.creationDate = creationDate;
        this.authority = authority;
        this.enabled = enabled;
    }    

    public User() {

    }

    public String getName() { return name; }

    public String getUsername() { return this.username; }

    public int getId() { return this.idUser; }

    public String getPassword() { return password; }

    public String getEmail() { return email; }

    public String getCreationDate() { return creationDate; }

    public int getAuthority() { return authority; }

    public void setname(String name) { this.name = name; }

    public void setUsername(String username) { this.username = username; }

    public void setId(int IdUser) { this.idUser = IdUser; }

    public void setPassword(String hash) { this.password = hash; }

    public void setEmail(String Email) { this.email = Email; }

    public void setCreationDate(String date) { this.creationDate = date; }

    public void setAuthority(int authority) { this.authority = authority; }

    public void setEnabled(int enabled) { this.enabled = enabled; }

    public int getEnabled() { return enabled; }
}
