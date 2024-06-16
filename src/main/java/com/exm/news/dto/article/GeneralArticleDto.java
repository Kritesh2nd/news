package com.exm.news.dto.article;

import java.time.LocalDateTime;

import com.exm.news.model.User;

public class GeneralArticleDto {

	private Long articleId;
	
	private String title;
    
    private String content;
    
    private String imageUrl;

    private User author;
    
    private LocalDateTime publicationDate;

    public GeneralArticleDto() {}
    
    public GeneralArticleDto(Long articleId, String title, String content, String imageUrl, User author,
			LocalDateTime publicationDate) {
		super();
		this.articleId = articleId;
		this.title = title;
		this.content = content;
		this.imageUrl = imageUrl;
		this.author = author;
		this.publicationDate = publicationDate;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public LocalDateTime getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(LocalDateTime publicationDate) {
		this.publicationDate = publicationDate;
	}
    
}
