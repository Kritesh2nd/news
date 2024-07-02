package com.exm.news.entity.app;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "images")
public class ArticleImage {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long imgId;

	@Lob
    @Column(name = "image", columnDefinition = "MEDIUMBLOB")
    private String image;

	@ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

	public ArticleImage() {}

	public ArticleImage(Long imgId, String image, Article article) {
		super();
		this.imgId = imgId;
		this.image = image;
		this.article = article;
	}

	public Long getImgId() {
		return imgId;
	}

	public void setImgId(Long imgId) {
		this.imgId = imgId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
		
}
