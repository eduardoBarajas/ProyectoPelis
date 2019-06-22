package com.CODEns.BackendAPI.DTOs;

import com.CODEns.BackendAPI.Entities.User;

/*
 * La clase DTO de usuario, es identica a la entidad de usuario con la diferencia de contener campos extras, 
 * como Status y Message que son para la comunicacion con el cliente que recibira la informacion.
 */

public class UserDTO {
	private int IdUser;
    private String Name;
    private String Username;
    private String PrivateKey;
    private String Email;
    private String CreationDate;
    private String Status;
    private String Message;
    
    public UserDTO(User user, String Status, String Message) {
    	this.CreationDate = user.getCreationDate();
    	this.Email = user.getEmail();
    	this.IdUser = user.getId();
    	this.Name = user.getName();
    	this.PrivateKey = user.getPrivateKey();
    	this.Username = user.getUsername();
    	this.Status = Status;
    	this.Message = Message;
    }
    
    public UserDTO(String Status, String Message) {
    	this.Status = Status;
    	this.Message = Message;
    }
    
    public UserDTO(User user) {
    	this.CreationDate = user.getCreationDate();
    	this.Email = user.getEmail();
    	this.IdUser = user.getId();
    	this.Name = user.getName();
    	this.PrivateKey = user.getPrivateKey();
    	this.Username = user.getUsername();
    }
    
	public int getId() {
		return IdUser;
	}
	public void setIdUser(int IdUser) {
		this.IdUser = IdUser;
	}
	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String Username) {
		this.Username = Username;
	}
	public String getPrivateKey() {
		return PrivateKey;
	}
	public void setPrivateKey(String PrivateKey) {
		this.PrivateKey = PrivateKey;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String Email) {
		this.Email = Email;
	}
	public String getCreationDate() {
		return CreationDate;
	}
	public void setCreationDate(String CreationDate) {
		this.CreationDate = CreationDate;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String Status) {
		this.Status = Status;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String Message) {
		this.Message = Message;
	}
}
