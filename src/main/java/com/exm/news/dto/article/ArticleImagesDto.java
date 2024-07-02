package com.exm.news.dto.article;

import java.time.LocalDateTime;
import java.util.List;

import com.exm.news.dto.user.GeneralUserDto;
import com.exm.news.model.Category;

public class ArticleImagesDto {
    private Long articleId;

    private String title;
    
    private String shortContent;
    
    private List<String> images;
    
    private String content;

    private LocalDateTime publicationDate;

    private GeneralUserDto author;

    private Category category;

    public ArticleImagesDto() {}
    
	public ArticleImagesDto(Long articleId, String title, String shortContent, List<String> images, String content,
			LocalDateTime publicationDate, GeneralUserDto author, Category category) {
		super();
		this.articleId = articleId;
		this.title = title;
		this.shortContent = shortContent;
		this.images = images;
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

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
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
