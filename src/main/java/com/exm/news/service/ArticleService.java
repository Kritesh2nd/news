package com.exm.news.service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exm.news.dto.article.ArticleDto;
import com.exm.news.dto.article.GetArticleDto;
import com.exm.news.dto.article.GetDateAndCategory;
import com.exm.news.dto.article.GetTwoDatesWithCategory;
import com.exm.news.model.Article;
import com.exm.news.model.Category;
import com.exm.news.model.User;
import com.exm.news.repository.ArticleRepository;
import com.exm.news.repository.CategoryRepository;
import com.exm.news.repository.UserRepository;
import com.exm.news.response.BasicResponseDto;
import com.exm.news.service.interfaces.ArticleServiceInterface;

@Service		
public class ArticleService implements ArticleServiceInterface{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<ArticleDto> getAllArticles() {
		
		List<Article> articleList = articleRepository.findAll();
		List<ArticleDto> generalArticleList = articleList.stream()
				.map(article -> modelMapper.map(article, ArticleDto.class))
				.collect(Collectors.toList());
		
		return generalArticleList;
	}

	
	@Override
	public ArticleDto getArticleDtoById(Long id){
		
		Article article = getArticleById(id);
		ArticleDto articleById = modelMapper.map(article, ArticleDto.class);
		
		return articleById;
	}
	
	@Override
	public List<ArticleDto> listArticlesDate(String dateTime) {
		
		checkValidDate(dateTime);
		
		List<ArticleDto> generalArticleList = new ArrayList<ArticleDto>();
		List<Article> articleList = articleRepository.findArticleByDate(dateTime);
		
		generalArticleList = articleList.stream()
				.map(article -> modelMapper.map(article, ArticleDto.class))
				.collect(Collectors.toList());

		return generalArticleList;
	}

	@Override
	public List<ArticleDto> listArticlesCategory(String categoryName) {
		
		Category category = categoryRepository.findByCategoryName(categoryName);
		
		if(category == null) {
			throw new NoSuchElementException("Category not found");
		}
		
		List<Article> articleList = articleRepository.findArticleByCategory(category.getCategoryId());
		List<ArticleDto> generalArticleList = articleList.stream()
				.map(article -> modelMapper.map(article, ArticleDto.class))
				.collect(Collectors.toList());
		
		return generalArticleList;
	}

	@Override
	public List<ArticleDto> listArticlesDateCategory(GetDateAndCategory dateCategory) {
		
		Category category = categoryRepository.findByCategoryName(dateCategory.getCategory());
		
		if(category == null && checkValidDate(dateCategory.getDateTime())) {
			throw new NoSuchElementException("Category not found OR invalid date time");
		}
		
		List<Article> articleList = articleRepository.findArticleByDateAndCategory(dateCategory.getDateTime(),category.getCategoryId());
		List<ArticleDto> generalArticleList = articleList.stream()
				.map(article -> modelMapper.map(article, ArticleDto.class))
				.collect(Collectors.toList());
		
		return generalArticleList;
	}
	
	@Override
	public List<ArticleDto> listArticleByTwoDates(GetTwoDatesWithCategory twoDateCategory) {
		
		checkValidDate(twoDateCategory.getStartDate());
		checkValidDate(twoDateCategory.getEndDate());
		
		List<ArticleDto> generalArticleList = new ArrayList<ArticleDto>();
		List<Article> articleList = articleRepository.findArticleByTwoDates(twoDateCategory.getStartDate(),twoDateCategory.getEndDate());

		generalArticleList = articleList.stream()
				.map(article -> modelMapper.map(article, ArticleDto.class))
				.collect(Collectors.toList());

		return generalArticleList;
	}


	@Override
	public List<ArticleDto> listArticleByTwoDatesWithCategory(GetTwoDatesWithCategory twoDateCategory) {
		
		checkValidDate(twoDateCategory.getStartDate());
		checkValidDate(twoDateCategory.getEndDate());
		
		Category category = categoryRepository.findByCategoryName(twoDateCategory.getCategory());
		
		if(category == null) {
			throw new NoSuchElementException("Category not found");
		}
		
		List<ArticleDto> generalArticleList = new ArrayList<ArticleDto>();
		
		List<Article> articleList = articleRepository.findArticleByTwoDatesWithCategory(twoDateCategory.getStartDate(),twoDateCategory.getEndDate(),category.getCategoryId());
		
		generalArticleList = articleList.stream()
				.map(article -> modelMapper.map(article, ArticleDto.class))
				.collect(Collectors.toList());

		return generalArticleList;
	}
	
	

	
	
	@Override
	public BasicResponseDto writeArticle(GetArticleDto newArticle) {
		
		Article article = modelMapper.map(newArticle, Article.class);
		
		User articleAuther = userRepository.findUserById(Long.parseLong("1"));
		Category articleCategory = categoryRepository.findByCategoryName(newArticle.getCategory());
		
		if(articleCategory == null) {
			throw new NoSuchElementException("Category not found");
		}
		
		article.setAuthor(articleAuther);
		article.setCategory(articleCategory);
		article.setPublicationDate(LocalDateTime.now());
		
		articleRepository.save(article);
		
		return new BasicResponseDto("New article addedsuccesfully",true);
	}

	@Override
	public BasicResponseDto writeAllArticle(List<GetArticleDto> articles) {
		for(GetArticleDto a : articles) {
			writeArticle(a);
		}
		return new BasicResponseDto("All articles added successfully.",true);
	}

	@Override 
	public BasicResponseDto deleteArticleById(Long id) {
		System.out.println("Service delete: id: "+id);
		Article deleteArticle= getArticleById(id);
		
		articleRepository.delete(deleteArticle);
		
		return new BasicResponseDto("Article deleted successfully.",true);
	}  

	@Override
	public BasicResponseDto deleteAllArticles() {
		System.out.println("Service delete all");
		articleRepository.deleteAll();
		return new BasicResponseDto("All articles deleted successfully.",true);
	}

	@Override
	public BasicResponseDto editArticle(ArticleDto article) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Article getArticleById(Long id) {
		Article article = articleRepository.findArticleById(id);
		
		if(article == null) {
			throw new NoSuchElementException("Article not found");
		}
		
		return article;
	}
	
	public boolean checkValidDate(String dateStr) {
		String dateFormat = "yyyy-MM-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        try {
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
        	throw new DateTimeException("Error in date time format: "+e);
        }
	}
	
}

