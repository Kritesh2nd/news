package com.example.school.dto.article;

import com.example.school.model.User;

public class ShortArticleDto {

	private Long articleId;
	
	private String title;
    
    private String shortContent;
    
    private String imageUrl;

    private User author;

    public ShortArticleDto() {}
    
    public ShortArticleDto(Long articleId, String title, String shortContent, String imageUrl, User author) {
		super();
		this.articleId = articleId;
		this.title = title;
		this.shortContent = shortContent;
		this.imageUrl = imageUrl;
		this.author = author;
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

	public String getShortContent() {
		return shortContent;
	}

	public void setShortContent(String shortContent) {
		this.shortContent = shortContent;
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

}
