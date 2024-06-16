package com.exm.news.exception;

@SuppressWarnings("serial")
public class ProcessNotAllowedException extends RuntimeException{
    String message;
    
    public ProcessNotAllowedException() {}

	public ProcessNotAllowedException(String message) {
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
