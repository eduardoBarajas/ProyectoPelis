package com.CODEns.BackendAPI.DTOs;

import java.util.LinkedList;
import java.util.List;

public class ResponseDTO<T> {
	
	private String message;
	private String status;
	private List<T> responses = new LinkedList<>();
	
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

	public List<T> getResponses() {
		return responses;
	}

	public void setResponses(List<T> responses) {
		this.responses = responses;
	}
}
