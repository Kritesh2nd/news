package com.exm.news.dto.user;

import jakarta.validation.constraints.NotBlank;

public class PasswordReset {

//	TODO remove unnecessary tags
    private Long userId;
	
    private String password;
	
	public PasswordReset() {}
	
	public PasswordReset(Long userId, @NotBlank(message = "please enter data") String password) {
		super();
		this.userId = userId;
		this.password = password;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
