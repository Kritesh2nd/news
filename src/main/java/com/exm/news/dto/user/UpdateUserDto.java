package com.exm.news.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UpdateUserDto {
	
	@NotBlank(message = "input field cannot be blank")
    private Long userId;
	
	@NotBlank(message = "input field cannot be blank")
    private String username;

    @Email(message = "invalid email format")
    private String email;
 
    @NotBlank(message = "input field cannot be blank")
    private String firstName;

    @NotBlank(message = "input field cannot be blank")
    private String lastName;

    public UpdateUserDto() {}

	public UpdateUserDto(@NotBlank(message = "input field cannot be blank") Long userId,
			@NotBlank(message = "input field cannot be blank") String username,
			@Email(message = "invalid email format") String email,
			@NotBlank(message = "input field cannot be blank") String firstName,
			@NotBlank(message = "input field cannot be blank") String lastName) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

}
