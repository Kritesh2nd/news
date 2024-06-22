package com.exm.news.model;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.Set;

@Entity
@Table(name = "users")
public class User{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_authorities",
	 	joinColumns = @JoinColumn(name = "user_id",updatable=true),
	 	inverseJoinColumns = @JoinColumn(name = "authority_id",updatable=true))
	private Set<Authority> authorities;

	public User() {}
	
	public User(Long userId, @NotBlank(message = "please enter data") String username,
			@NotBlank(message = "please enter data") String password,
			@NotBlank(message = "please enter data") String email,
			@NotBlank(message = "please enter data") String firstName,
			@NotBlank(message = "please enter data") String lastName, Set<Authority> authorities) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.authorities = authorities;
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

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", authorities=" + authorities
				+ ", getUserId()=" + getUserId() + ", getUsername()=" + getUsername() + ", getPassword()="
				+ getPassword() + ", getEmail()=" + getEmail() + ", getFirstName()=" + getFirstName()
				+ ", getLastName()=" + getLastName() + ", getAuthorities()=" + getAuthorities() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	
	
}
