package com.example.school.exception;

@SuppressWarnings("serial")
public class DuplicateElementException extends RuntimeException{

	private String message;

	public DuplicateElementException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
