package com.example.school.exception;

public class ProcessNotAllowedException extends RuntimeException{
    String message;
    
    public ProcessNotAllowedException(String message) {
		super();
		this.message=message;
	}
}
