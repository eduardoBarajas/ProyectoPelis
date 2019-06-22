package com.CODEns.BackendAPI.DTOs;

import com.CODEns.BackendAPI.Entities.MovieLinkDown;

public class MovieLinkDownDTO {
	private int IdLinkDown;
    private int IdLinkMovie;
    private String DownDate;
    private boolean Repaired;
    private String Status;
    private String Message;

    public MovieLinkDownDTO(MovieLinkDown movieLink, String status, String message) {
        IdLinkDown = movieLink.getIdLinkDown();
        IdLinkMovie = movieLink.getIdLinkMovie();
        DownDate = movieLink.getDownDate();
        Repaired = movieLink.isRepaired();
        this.Status = status;
        this.Message = message;
    }
    
    public MovieLinkDownDTO(String status, String message) {
        this.Status = status;
        this.Message = message;
    }
    
    public MovieLinkDownDTO(MovieLinkDown movieLink) {
        IdLinkDown = movieLink.getIdLinkDown();
        IdLinkMovie = movieLink.getIdLinkMovie();
        DownDate = movieLink.getDownDate();
        Repaired = movieLink.isRepaired();
    }

	public int getIdLinkDown() {
		return IdLinkDown;
	}

	public void setIdLinkDown(int IdLinkDown) {
		this.IdLinkDown = IdLinkDown;
	}

	public int getIdLinkMovie() {
		return IdLinkMovie;
	}

	public void setIdLinkMovie(int IdLinkMovie) {
		this.IdLinkMovie = IdLinkMovie;
	}

	public String getDownDate() {
		return DownDate;
	}

	public void setDownDate(String DownDate) {
		this.DownDate = DownDate;
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
	
	public boolean isRepaired() {
		return Repaired;
	}

	public void setRepaired(boolean repaired) {
		Repaired = repaired;
	}
	
	
}
