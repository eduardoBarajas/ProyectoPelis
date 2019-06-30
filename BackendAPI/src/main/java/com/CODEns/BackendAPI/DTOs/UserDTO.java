package com.CODEns.BackendAPI.DTOs;

import com.CODEns.BackendAPI.Entities.User;

/*
 * La clase DTO de usuario, es identica a la entidad de usuario con la diferencia de contener campos extras, 
 * como Status y Message que son para la comunicacion con el cliente que recibira la informacion.
 */

public class UserDTO {
	private int idUser;
    private String name;
    private String username;
    private String email;
	private String creationDate;
	private int role;
    private String status;
    private String message;
    
    public UserDTO(User user, String Status, String Message) {
    	this.creationDate = user.getCreationDate();
    	this.email = user.getEmail();
    	this.idUser = user.getId();
    	this.name = user.getName();
		this.username = user.getUsername();
		this.role = user.getRole();
    	this.status = Status;
    	this.message = Message;
    }
    
    public UserDTO(String Status, String Message) {
    	this.status = Status;
    	this.message = Message;
    }
    
    public UserDTO(User user) {
    	this.creationDate = user.getCreationDate();
    	this.email = user.getEmail();
    	this.idUser = user.getId();
    	this.name = user.getName();
		this.username = user.getUsername();
		this.role = user.getRole();
	}
	
	public String getName() { return name; }

    public String getUsername() { return this.username; }

    public int getId() { return this.idUser; }

    public String getEmail() { return email; }

    public String getCreationDate() { return creationDate; }

    public int getRole() { return role; }

    public void setname(String name) { this.name = name; }

    public void setUsername(String username) { this.username = username; }

    public void setId(int IdUser) { this.idUser = IdUser; }

    public void setEmail(String Email) { this.email = Email; }

    public void setCreationDate(String date) { this.creationDate = date; }

	public void setRole(int Role) { this.role = Role; }
	
	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return this.message;
	}

	public String getStatus() {
		return this.status;
	}
    
	
}
