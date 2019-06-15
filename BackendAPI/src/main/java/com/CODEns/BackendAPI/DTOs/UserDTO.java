package com.CODEns.BackendAPI.DTOs;

import com.CODEns.BackendAPI.Entities.User;

public class UserDTO {
	private int id_user;
    private String name;
    private String username;
    private String private_key;
    private String email;
    private String creation_date;
    private String status;
    private String message;
    
    public UserDTO(User user, String status, String message) {
    	this.creation_date = user.getCreationDate();
    	this.email = user.getEmail();
    	this.id_user = user.getId();
    	this.name = user.getName();
    	this.private_key = user.getPrivateKey();
    	this.username = user.getUsername();
    	this.status = status;
    	this.message = message;
    }
    
    public UserDTO(String status, String message) {
    	this.status = status;
    	this.message = message;
    }
    
    public UserDTO(User user) {
    	this.creation_date = user.getCreationDate();
    	this.email = user.getEmail();
    	this.id_user = user.getId();
    	this.name = user.getName();
    	this.private_key = user.getPrivateKey();
    	this.username = user.getUsername();
    }
    
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPrivate_key() {
		return private_key;
	}
	public void setPrivate_key(String private_key) {
		this.private_key = private_key;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
