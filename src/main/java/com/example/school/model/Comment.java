package com.example.school.model;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "comment")
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private long commentId;
    
	@NotBlank
	private String content;
	
	@DateTimeFormat
	@Column(name = "comment_date")
	private Date commentDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
	private User user;
    
	@ManyToOne
	@JoinColumn(name = "article_id", referencedColumnName = "article_id", nullable = false)
	private Article article;
	
	public Comment() {}

	public Comment(long commentId, @NotBlank String content, Date commentDate, User user, Article article) {
		super();
		this.commentId = commentId;
		this.content = content;
		this.commentDate = commentDate;
		this.user = user;
		this.article = article;
	}

	public long getCommnetId() {
		return commentId;
	}

	public void setCommnetId(long commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	
}
