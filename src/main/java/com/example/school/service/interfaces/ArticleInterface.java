package com.example.school.service.interfaces;

import java.util.List;

import com.example.school.model.Article;

public interface ArticleInterface {
/*
 * C[R]UD
 * get all article
 * get article of the given day
 * get article from this date to that date
 * get article of this tag, category, date range 
 * 
 * [C]RUD
 * add new article
 * 
 * CR[U]D
 * edit article
 * 
 * CRU[D]
 * delete all article
 * delete article by id
 * delete article of this date
 * delete article from this date that date
 * 
 * */
	
	public List<Article> getAllArticle();
	
}
