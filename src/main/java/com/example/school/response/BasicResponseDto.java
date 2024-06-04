package com.example.school.response;


public class BasicResponseDto {
	private String message;
	private boolean success;
	
	public BasicResponseDto() {}
	
	public BasicResponseDto(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
