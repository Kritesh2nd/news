package com.example.school.model;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	private enum Role {
		admin, editor, reader
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long userId;
    
	@NotBlank
	private String username;
    
	@NotBlank
    private String password;
    
	@Email @UniqueElements
    private String email;
    
    private String firstName;
    
    private String lastName;
    
    @NotEmpty
    private Role role;
	
}
