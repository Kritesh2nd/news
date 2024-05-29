package com.example.school.model;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Article {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long articleId;
    
	@NotBlank
	private String title;
    
	@NotBlank
    private String content;
    
	@DateTimeFormat
    private Date publicationDate;
    
	@ManyToOne
	@JoinColumn(name = "autherId", referencedColumnName = "userId", nullable = false)
	private User user;
    
	@ManyToOne
	@JoinColumn(name = "categoryId", referencedColumnName = "categoryId", nullable = false)
	private Category category;
	
}
