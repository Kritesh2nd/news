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
@Table(name = "commnet")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long commnetId;
    
	@NotBlank
	private String content;
	
	@DateTimeFormat
	private Date commentDate;
	
	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
	private User user;
    
	@ManyToOne
	@JoinColumn(name = "articleId", referencedColumnName = "articleId", nullable = false)
	private Article article;
}
