package com.CODEns.BackendAPI.DTOs;

import java.util.LinkedList;
import java.util.List;

public class MovieUniqueCheckDTO {
	
	private String message;
	private String status;
	private String name;
    private int year;
	
    public MovieUniqueCheckDTO() {  }
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
