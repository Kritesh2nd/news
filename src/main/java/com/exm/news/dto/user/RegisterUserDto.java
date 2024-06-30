package com.exm.news.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterUserDto {
	
	@NotBlank(message = "input field cannot be blank")
    private String username;

    @Email(message = "invalid email format")
    private String email;
 
    @NotBlank(message = "input field cannot be blank")
    private String firstName;

    @NotBlank(message = "input field cannot be blank")
    private String lastName;
 
    @NotBlank(message = "input field cannot be blank")
    private String password;

    public RegisterUserDto() {}

	public RegisterUserDto(@NotBlank(message = "input field cannot be blank") String username,
			@Email(message = "invalid email format") String email,
			@NotBlank(message = "input field cannot be blank") String firstName,
			@NotBlank(message = "input field cannot be blank") String lastName,
			@NotBlank(message = "input field cannot be blank") String password) {
		super();
		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
