package com.example.school.service.interfaces;

import java.util.List;

import com.example.school.response.BasicResponseDto;

import com.example.school.dto.article.ArticleDto;
import com.example.school.dto.article.GetArticleDto;
import com.example.school.dto.article.GetDateAndCategory;
import com.example.school.dto.article.GetTwoDatesWithCategory;

public interface ArticleServiceInterface {

//	C[R]UD
	public ArticleDto getArticleDtoById(Long id);
	public List<ArticleDto> getAllArticles();
	public List<ArticleDto> listArticlesDate(String dateTime);
	public List<ArticleDto> listArticlesCategory(String category);
	public List<ArticleDto> listArticlesDateCategory(GetDateAndCategory dateCategory);
	
	public List<ArticleDto> listArticleByTwoDates(GetTwoDatesWithCategory twoDateCategory);
	public List<ArticleDto> listArticleByTwoDatesWithCategory(GetTwoDatesWithCategory twoDateCategory);
	
//	[C]RUD
	public BasicResponseDto writeArticle(GetArticleDto newArticle);
	public BasicResponseDto writeAllArticle(List<GetArticleDto> articles);
	
//	CR[U]D
	public BasicResponseDto editArticle(ArticleDto article);
	
//	CRU[D]
	public BasicResponseDto deleteArticleById(Long id);
	public BasicResponseDto deleteAllArticles();
}
