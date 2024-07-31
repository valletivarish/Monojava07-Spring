package com.monocept.myapp.exception;


import java.time.LocalDateTime;

public class ResponseErrorNotFound {
	private int status;
	private String message;
	private LocalDateTime date;
	public ResponseErrorNotFound() {
		super();
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	
}
