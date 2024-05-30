package com.example.school.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "article_tag")

public class ArticleTag {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "article_tag_id")
	private long articleTagId;
	
	@Column(name = "article_id")
	private long articleId;
	
	@Column(name = "tag_id")
	private long tagId;
	
	public ArticleTag() {}

	public ArticleTag(long articleTagId, long articleId, long tagId) {
		super();
		this.articleTagId = articleTagId;
		this.articleId = articleId;
		this.tagId = tagId;
	}

	public void setArticleTagId(long articleTagId) {
		this.articleTagId = articleTagId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}
	
	
}
