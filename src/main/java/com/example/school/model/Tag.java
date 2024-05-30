package com.example.school.model;

import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tag")
public class Tag {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "tag_id")
	private long tagId;
	
	@NotBlank
	@Column(name = "tag_name")
	private String tagName;
	
	@ManyToMany
    @JoinTable(
        name = "article_tag",
        joinColumns = @JoinColumn(name = "tag_id"),
        inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    private List<Article> article;

	public Tag() {}

	public Tag(long tagId, @NotBlank @UniqueElements String tagName, List<Article> article) {
		super();
		this.tagId = tagId;
		this.tagName = tagName;
		this.article = article;
	}

	public long getTagId() {
		return tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public List<Article> getArticle() {
		return article;
	}

	public void setArticle(List<Article> article) {
		this.article = article;
	}
	
}
