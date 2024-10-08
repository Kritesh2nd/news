package com.exm.news.dto.article;

import jakarta.validation.constraints.NotBlank;

public class GetArticleDto {
	
	@NotBlank(message = "input field cannot be blank")
    private String title;
    
    private String shortContent;
    
    private String imageUrl;
    
	@NotBlank(message = "input field cannot be blank")
    private String content;
    
	@NotBlank(message = "input field cannot be blank")
    private String category;

    public GetArticleDto() {}
    
    public GetArticleDto(String title, String shortContent, String imageUrl, String content, String category) {
		super();
		this.title = title;
		this.shortContent = shortContent;
		this.imageUrl = imageUrl;
		this.content = content;
		this.category = category;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "GetArticleDto [title=" + title + ", shortContent=" + shortContent + ", imageUrl=" + imageUrl
				+ ", content=" + content + ", category=" + category + "]";
	}

}
