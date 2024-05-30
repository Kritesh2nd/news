package com.example.school.model;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;



import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "article")
@Embeddable
public class Article {
	
//	@Autowired
//	private UserRepository userRepository;
	
//	@Autowired
//	private CategoryRepository categoryRepository;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "article_id")
	private long articleId;
    
	@NotBlank
	private String title;
    
	@NotBlank
    private String content;
    
	@DateTimeFormat
	@Column(name = "publication_date")
    private Date publicationDate;
    
	@ManyToOne
	@JoinColumn(name = "auther_id", referencedColumnName = "user_id", nullable = false)
	private User user;
    
	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
	private Category category;

	public Article() {}

	public Article(@NotBlank String title, @NotBlank String content, Date publicationDate, User user,
			Category category) {
		this.title = title;
		this.content = content;
		this.publicationDate = publicationDate;
		this.user = user;
		this.category = category;
	}

	public long getArticleId() {
		return articleId;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public User getUser() {
		return user;
	}

	public Category getCategory() {
		return category;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}
