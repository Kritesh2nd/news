package com.exm.news.dto.user;

public class LoginUserDto {
	
//	TODO remove unnecessary tags
    private String email;
	
    private String password;

	public LoginUserDto() {}
	
	public LoginUserDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
