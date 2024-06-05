package com.example.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.school.model.Article;

@Repository
public interface ArticleRepository  extends JpaRepository<Article, Long>{
	@Query(nativeQuery = true, value="SELECT * FROM article a where u.title = :title")
	Article findArticleByTitle(String title);
	
	@Query(nativeQuery = true, value="SELECT * FROM article a where a.article_id = :id")
	Article findArticleById(Long id);
	
//	@Query(nativeQuery = true, value="SELECT * FROM article a where a.publication_date = :dateTime")apple
	@Query(nativeQuery = true, value="SELECT * FROM article WHERE DATE(publication_date) = :dateTime;")
	List<Article> findArticleByDate(String dateTime);
	
	@Query(nativeQuery = true, value="SELECT * FROM article a where a.category_id = :categoryId")
	List<Article> findArticleByCategory(Long categoryId);
	
	@Query(nativeQuery = true, value="SELECT * FROM article WHERE DATE(publication_date) = :dateTime AND category_id = :categoryId")
	List<Article> findArticleByDateAndCategory(String dateTime, Long categoryId);
	
	//SELECT * FROM article WHERE DATE(publication_date) BETWEEN '2024-06-04' AND '2024-06-04';
	@Query(nativeQuery = true, value="SELECT * FROM article WHERE DATE(publication_date) BETWEEN :startDate AND :endDate")
	List<Article> findArticleByTwoDates(String startDate, String endDate);
	
	@Query(nativeQuery = true, value="SELECT * FROM article WHERE DATE(publication_date) BETWEEN :startDate AND :endDate AND category_id = :categoryId")
	List<Article> findArticleByTwoDatesWithCategory(String startDate, String endDate, Long categoryId);
}

/*
 SELECT * FROM article a where a.publication_date = "2024-06-04 17:35:53" AND a.category_id = 1
 
 2024-06-04 17:35:53
 
 
 
 */