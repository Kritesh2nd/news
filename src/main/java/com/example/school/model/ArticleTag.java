package com.example.school.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "articleTag")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleTag {
	
	private long articleId;
	
	private long tagId;
}
