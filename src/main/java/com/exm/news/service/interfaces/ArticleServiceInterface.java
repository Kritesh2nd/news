package com.exm.news.service.interfaces;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.exm.news.dto.article.ArticleDto;
import com.exm.news.dto.article.GetArticleDto;
import com.exm.news.dto.article.GetDateAndCategory;
import com.exm.news.dto.article.GetTwoDatesWithCategory;
import com.exm.news.response.BasicResponseDto;

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
	public BasicResponseDto writeArtileWithImages(GetArticleDto newArticle, MultipartFile... files) throws IOException;
	
//	CR[U]D
	public BasicResponseDto editArticle(ArticleDto newArticle);
	
//	CRU[D]
	public BasicResponseDto deleteArticleById(Long id);
	public BasicResponseDto deleteAllArticles();
}
