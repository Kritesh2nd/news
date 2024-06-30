package com.exm.news.dto.article;

import java.time.LocalDateTime;

import com.exm.news.dto.user.GeneralUserDto;
import com.exm.news.model.Category;

public class ArticleDto {
	
    private Long articleId;

    private String title;
    
    private String shortContent;
    
    private String imageUrl;
    
    private String content;

    private LocalDateTime publicationDate;

    private GeneralUserDto author;

    private Category category;

    public ArticleDto() {}
    
    public ArticleDto(Long articleId, String title, String shortContent, String imageUrl, String content,
			LocalDateTime publicationDate, GeneralUserDto author, Category category) {
		super();
		this.articleId = articleId;
		this.title = title;
		this.shortContent = shortContent;
		this.imageUrl = imageUrl;
		this.content = content;
		this.publicationDate = publicationDate;
		this.author = author;
		this.category = category;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(LocalDateTime publicationDate) {
		this.publicationDate = publicationDate;
	}

	public GeneralUserDto getAuthor() {
		return author;
	}

	public void setAuthor(GeneralUserDto author) {
		this.author = author;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}



