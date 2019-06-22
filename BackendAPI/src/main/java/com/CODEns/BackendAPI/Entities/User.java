package com.CODEns.BackendAPI.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * Entidad de usuario @Entity, esta es la usada para la persistencia de datos.
 * la tabla se creara con @Table, por default se crean los campos de la tabla con los nombres de las propiedades pero
 * este comportamiento puede se cambiado utilizando la anotacion @Column.
 */

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdUser;
    private String Name;
    private String Username;
    private String PrivateKey;
    private String Email;
    private String CreationDate;
    private int Role;

    public User(String user) {
        this.Name = user;
    }
    public User() {

    }

    public String getName() { return Name; }

    public String getUsername() { return this.Username; }

    public int getId() { return this.IdUser; }

    public String getPrivateKey() { return PrivateKey; }

    public String getEmail() { return Email; }

    public String getCreationDate() { return CreationDate; }

    public int getRole() { return Role; }

    public void setName(String Name) { this.Name = Name; }

    public void setUserName(String Username) { this.Username = Username; }

    public void setId(int IdUser) { this.IdUser = IdUser; }

    public void setPrivateKey(String PrivateKey) { this.PrivateKey = PrivateKey; }

    public void setEmail(String Email) { this.Email = Email; }

    public void setCreationDate(String date) { this.CreationDate = date; }

    public void setRole(int Role) { this.Role = Role; }
}