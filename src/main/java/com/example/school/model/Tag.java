package com.example.school.model;

import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "tag")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tag {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long tagId;
	
	@NotBlank @UniqueElements
	private String tagName;
	
	@ManyToMany
    @JoinTable(
        name = "articleTag",
        joinColumns = @JoinColumn(name = "tagId"),
        inverseJoinColumns = @JoinColumn(name = "articleId")
    )
    private List<Article> article;
}
