package com.CODEns.BackendAPI.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MovieSuggestion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int IdSuggestion;
    private String SuggestionDate;
    private String Suggestion;
    private boolean Added;
    private int IdUser;

    public MovieSuggestion(int id, int IdUser, String date, String sug, boolean agregada) {
        IdSuggestion = id;
        this.IdUser = IdUser;
        SuggestionDate = date;
        Suggestion = sug;
        this.Added = agregada;
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

	public boolean isAdded() {
		return Added;
	}

	public void setAdded(boolean agregada) {
		this.Added = agregada;
	}
	
	

}