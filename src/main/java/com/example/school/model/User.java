package com.example.school.model;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "user")
public class User implements UserDetails{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

	@NotBlank(message="please enter data")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

	@NotBlank(message="please enter data")
    @Column(name = "password", nullable = false)
    private String password;

	@NotBlank(message="please enter data")
	@Column(name = "email", nullable = false, unique = true)
    private String email;

	@NotBlank(message="please enter data")
    @Column(name = "first_name")
    private String firstName;

	@NotBlank(message="please enter data")
    @Column(name = "last_name")
    private String lastName;

	@NotBlank(message="please enter data")
    @Column(name = "role", nullable = false)
    private String role;

	
	
    @Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", role=" + role + ", getUserId()="
				+ getUserId() + ", getUsername()=" + getUsername() + ", getPassword()=" + getPassword()
				+ ", getEmail()=" + getEmail() + ", getFirstName()=" + getFirstName() + ", getLastName()="
				+ getLastName() + ", getRole()=" + getRole() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	public User() {}
    
    public User(String username, String password, String email, String firstName, String lastName,
			String role) {
		super();
		this.username = username;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

    
    
}
