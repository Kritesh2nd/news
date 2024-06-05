package com.example.school.dto.article;

public class MicroArticleDto {
	
	private Long articleId;
	
	private String title;
    
    private String imageUrl;

    public MicroArticleDto() {}
    
    public MicroArticleDto(Long articleId, String title, String imageUrl) {
		super();
		this.articleId = articleId;
		this.title = title;
		this.imageUrl = imageUrl;
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
    
}
