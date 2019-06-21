package com.CODEns.BackendAPI.DTOs;

import com.CODEns.BackendAPI.Entities.MovieSuggestion;

public class MovieSuggestionDTO {
	private int IdSuggestion;
    private String SuggestionDate;
    private String Suggestion;
    private int IdUser;
    private boolean Added;
    private String Status;
    private String Message;

    public MovieSuggestionDTO(MovieSuggestion mov, String status, String message) {
        this.IdSuggestion = mov.getIdSuggestion();
        this.SuggestionDate = mov.getSuggestionDate();
        this.Suggestion = mov.getSuggestion();
        this.IdUser = mov.getIdUser();
        this.Added = mov.isAdded();
        this.Status = status;
        this.Message = message;
    }
    
    public MovieSuggestionDTO(String status, String message) {
        this.Status = status;
        this.Message = message;
    }
    
    public MovieSuggestionDTO(MovieSuggestion mov) {
        this.IdSuggestion = mov.getIdSuggestion();
        this.SuggestionDate = mov.getSuggestionDate();
        this.Suggestion = mov.getSuggestion();
        this.IdUser = mov.getIdUser();
        this.Added = mov.isAdded();
    }

	public int getIdSuggestion() {
		return IdSuggestion;
	}

	public void setIdSuggestion(int IdSuggestion) {
		this.IdSuggestion = IdSuggestion;
	}

	public String getSuggestionDate() {
		return SuggestionDate;
	}

	public void setSuggestionDate(String SuggestionDate) {
		this.SuggestionDate = SuggestionDate;
	}

	public String getSuggestion() {
		return Suggestion;
	}

	public void setSuggestion(String Suggestion) {
		this.Suggestion = Suggestion;
	}

	public int getIdUser() {
		return IdUser;
	}

	public void setIdUser(int IdUser) {
		this.IdUser = IdUser;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}
	
	public boolean isAdded() {
		return Added;
	}

	public void setAdded(boolean agregada) {
		this.Added = agregada;
	}
	
	
}
