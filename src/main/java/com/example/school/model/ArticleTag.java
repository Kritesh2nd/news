package com.example.school.model;
import jakarta.persistence.*;

@Entity
@Table(name = "article_tag")
public class ArticleTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_tag_id")
    private Long articleTagId;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;

    public ArticleTag() {}
	
    public ArticleTag(Article article, Tag tag) {
		super();
		this.article = article;
		this.tag = tag;
	}

	public Long getArticleTagId() {
		return articleTagId;
	}

	public void setArticleTagId(Long articleTagId) {
		this.articleTagId = articleTagId;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

    
    
}
