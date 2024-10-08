package com.exm.news.dto.user;

import java.util.List;

public class GeneralUserDto {
	
//	TODO remove unnecessary tags
    private Long userId;

    private String username;

    private String email;

    private String firstName;
    
    private String lastName;

    private List<String> role;

    public GeneralUserDto() {}

	public GeneralUserDto(Long userId, String username, String email, String firstName, String lastName,
			List<String> role) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
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

	public List<String> getRole() {
		return role;
	}

	public void setRole(List<String> role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "GeneralUserDto [userId=" + userId + ", username=" + username + ", email=" + email + ", firstName="
				+ firstName + ", lastName=" + lastName + ", role=" + role + "]";
	}
    
	
}
