package com.exm.news.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exm.news.constant.PathConstant;
import com.exm.news.dto.article.ArticleDto;
import com.exm.news.dto.article.GetArticleDto;
import com.exm.news.dto.article.GetDateAndCategory;
import com.exm.news.dto.article.GetTwoDatesWithCategory;
import com.exm.news.response.BasicResponseDto;
import com.exm.news.service.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;

@RequestMapping(PathConstant.ARTICLE)
@RestController
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@GetMapping(PathConstant.CAT)
	public ResponseEntity<String> cat(){
		return new ResponseEntity<String>("Cat Article",HttpStatus.OK);
	}
	
	@GetMapping(PathConstant.LIST_ALL)
	public ResponseEntity<List<ArticleDto>> allArticleList(){
		return new ResponseEntity<List<ArticleDto>>(articleService.getAllArticles(),HttpStatus.OK);
	}
	
	@GetMapping(PathConstant.LIST)
	public ResponseEntity<List<ArticleDto>> allArticleListDate(){
		return null;
	}
	
	@GetMapping(PathConstant.GET_BY_ID)
	public ResponseEntity<ArticleDto> getArticleById(@PathVariable Long id){
		return new ResponseEntity<ArticleDto>(articleService.getArticleDtoById(id),HttpStatus.OK);
	}
	
	@GetMapping(PathConstant.LIST_BY_DATE)
	public ResponseEntity<List<ArticleDto>> getArticleByDate(@RequestBody String dateTime){
		return new ResponseEntity<List<ArticleDto>>(articleService.listArticlesDate(dateTime),HttpStatus.OK);
	}
	
	@GetMapping(PathConstant.LIST_BY_CATEGORY)
	public ResponseEntity<List<ArticleDto>> getArticleByCategory(@RequestBody String category){
		return new ResponseEntity<List<ArticleDto>>(articleService.listArticlesCategory(category),HttpStatus.OK);
	}
	
	@GetMapping(PathConstant.LIST_BY_DATE_AND_CATEGORY)
	public ResponseEntity<List<ArticleDto>> getArticleByCategory(@RequestBody GetDateAndCategory dateCategory){
		return new ResponseEntity<List<ArticleDto>>(articleService.listArticlesDateCategory(dateCategory),HttpStatus.OK);
	}
	
	@GetMapping(PathConstant.LIST_BY_DATE_RANGES)
	public ResponseEntity<List<ArticleDto>> findArticleByTwoDates(@RequestBody GetTwoDatesWithCategory twoDateCategory){
		System.out.println("Controller LIST_BY_Date_RANGES");
		return new ResponseEntity<List<ArticleDto>>(articleService.listArticleByTwoDates(twoDateCategory),HttpStatus.OK);
	}
	
	@GetMapping(PathConstant.LIST_BY_DATE_RANGES_WITH_CATEGORY)
	public ResponseEntity<List<ArticleDto>> findArticleByTwoDatesWithRange(@RequestBody GetTwoDatesWithCategory twoDateCategory){
		return new ResponseEntity<List<ArticleDto>>(articleService.listArticleByTwoDatesWithCategory(twoDateCategory),HttpStatus.OK);
	}

	
	
	
	@PostMapping(PathConstant.ADD)
	public ResponseEntity<BasicResponseDto> writeArticles(@RequestBody GetArticleDto article){
		return new ResponseEntity<BasicResponseDto>(articleService.writeArticle(article),HttpStatus.OK);
	}
	
	@PostMapping(PathConstant.ADD_ALL)
	public ResponseEntity<BasicResponseDto> writeAllArticle(@RequestBody List<GetArticleDto> articles){
		return new ResponseEntity<BasicResponseDto>(articleService.writeAllArticle(articles),HttpStatus.OK);
	}
	
	@PostMapping(PathConstant.UPDATE)
	public ResponseEntity<BasicResponseDto> updateArticle(@RequestBody ArticleDto article){
		return new ResponseEntity<BasicResponseDto>(articleService.editArticle(article),HttpStatus.OK);
	}
	
	@PostMapping(PathConstant.DELETE_BY_ID)
	public ResponseEntity<BasicResponseDto> deleteById(@PathVariable Long id){
		System.out.println("Controller delete");
		return new ResponseEntity<BasicResponseDto>(articleService.deleteArticleById(id),HttpStatus.OK);
	}
	
	@PostMapping(PathConstant.DELETE_ALL)
	public ResponseEntity<BasicResponseDto> deleteAll(){
		System.out.println("Controller delete all");
		return new ResponseEntity<BasicResponseDto>(articleService.deleteAllArticles(),HttpStatus.OK);
	}
	
}

















