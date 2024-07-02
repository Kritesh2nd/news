package com.exm.news.service;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.exm.news.dto.article.ArticleDto;
import com.exm.news.dto.article.ArticleImagesDto;
import com.exm.news.dto.article.GetArticleDto;
import com.exm.news.dto.article.GetDateAndCategory;
import com.exm.news.dto.article.GetTwoDatesWithCategory;
import com.exm.news.dto.user.GeneralUserDto;
import com.exm.news.entity.app.Article;
import com.exm.news.entity.app.ArticleImage;
import com.exm.news.entity.app.Authority;
import com.exm.news.entity.app.Category;
import com.exm.news.entity.app.User;
import com.exm.news.repository.app.ArticleImageRepository;
import com.exm.news.repository.app.ArticleRepository;
import com.exm.news.repository.app.CategoryRepository;
import com.exm.news.repository.app.UserRepository;
import com.exm.news.response.BasicResponseDto;
import com.exm.news.security.authentication.UserAuth;
import com.exm.news.service.interfaces.ArticleServiceInterface;

@Service		
public class ArticleService implements ArticleServiceInterface{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private ArticleImageRepository articleImageRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<ArticleImagesDto> getAllArticlesImages(){
		
		List<Article> articleList = articleRepository.findAll();
		List<ArticleImagesDto> articleImagesDto = articleList.stream()
				.map(article -> articleToArticleImageDto(article))
				.collect(Collectors.toList());
		
		return articleImagesDto;
	}
	
	
	@Override
	public List<ArticleDto> getAllArticles() {
		
		List<Article> articleList = articleRepository.findAll();
		List<ArticleDto> generalArticleList = articleList.stream()
				.map(article -> articleToArticleDto(article))
				.collect(Collectors.toList());
		
		return generalArticleList;
	}
	
	@Override
	public ArticleDto getArticleDtoById(Long id){
		
		Article article = getArticleById(id);
		ArticleDto articleById = articleToArticleDto(article);
		
		return articleById;
	}
	
	@Override
	public List<ArticleDto> listArticlesDate(String dateTime) {
		
		checkValidDate(dateTime);
		
		List<ArticleDto> generalArticleList = new ArrayList<ArticleDto>();
		List<Article> articleList = articleRepository.findArticleByDate(dateTime);
		
		generalArticleList = articleList.stream()
				.map(article -> articleToArticleDto(article))
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
				.map(article -> articleToArticleDto(article))
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
				.map(article -> articleToArticleDto(article))
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
				.map(article -> articleToArticleDto(article))
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
				.map(article -> {
					return articleToArticleDto(article);
				})
				.collect(Collectors.toList());

		return generalArticleList;
	}
	
	

	
	
	@Override
	public BasicResponseDto writeArticle(GetArticleDto newArticle) {
		Article article = modelMapper.map(newArticle, Article.class);
		
		UserAuth userAuth = (UserAuth) SecurityContextHolder.getContext().getAuthentication();
		
		User articleAuther = userRepository.findUserByEmail(userAuth.getEmail()); 
		
		Category articleCategory = categoryRepository.findByCategoryName(newArticle.getCategory());
		
		if(articleCategory == null) {
			throw new NoSuchElementException("Category not found");
		}
		
		article.setAuthor(articleAuther);
		article.setCategory(articleCategory);
		article.setPublicationDate(LocalDateTime.now());
		
		articleRepository.save(article);
		
		return new BasicResponseDto("New article added succesfully",true);
	}

	
	
	@Override
	public BasicResponseDto writeArtileWithImages(GetArticleDto newArticle, MultipartFile... files) throws IOException {
		
		writeArticle(newArticle);
		Article LastArticle = articleRepository.findLastArticle();
		
		List<ArticleImage> articleImages = new ArrayList<ArticleImage>();
		
		for(MultipartFile file : files) {
			System.out.println("file type: "+file.getContentType());
			String imgFile = Base64.getEncoder().encodeToString(file.getBytes());
			ArticleImage articleImage = new ArticleImage(Long.valueOf(0),imgFile,LastArticle);
			articleImages.add(articleImage);
		}
		
		articleImageRepository.saveAll(articleImages);
		return new BasicResponseDto("New article added with image succesfully",true);
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
		
		Article deleteArticle= getArticleById(id);
		articleRepository.delete(deleteArticle);
		
		return new BasicResponseDto("Article deleted successfully.",true);
	}
	

	@Override
	public BasicResponseDto deleteAllArticles() {
		
		articleRepository.deleteAll();
		
		return new BasicResponseDto("All articles deleted successfully.",true);
	}

	@Override
	public BasicResponseDto editArticle(ArticleDto newArticle) {
		Article updateArticle = getArticleById(newArticle.getArticleId());
		
		if(updateArticle==null) {
			System.out.println();
		}
		Article article = modelMapper.map(newArticle, Article.class);
		
		UserAuth userAuth = (UserAuth) SecurityContextHolder.getContext().getAuthentication();
		
		User articleAuther = userRepository.findUserByEmail(userAuth.getEmail()); 
		
		String categoryName = newArticle.getCategory().getCategoryName();
		
		Category articleCategory = categoryRepository.findByCategoryName(categoryName);
		
		if(articleCategory == null) {
			throw new NoSuchElementException("Category not found");
		}
		
		article.setAuthor(articleAuther);
		article.setCategory(articleCategory);
		article.setPublicationDate(LocalDateTime.now());
		
		articleRepository.save(article);
		
		return new BasicResponseDto("Article updated succesfully",true);
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
	
	public GeneralUserDto userToGeneralUser(User user) {
		
		GeneralUserDto generalUser = modelMapper.map(user, GeneralUserDto.class);
		Set<Authority> authorities = user.getAuthorities();
		
		List<String> authorityNames = authorities.stream()
                .map(Authority::getName)
                .collect(Collectors.toList());

		generalUser.setRole(authorityNames);
		
		return generalUser;
	}
	
	public ArticleDto articleToArticleDto(Article article) {
		
		ArticleDto articleDto = modelMapper.map(article, ArticleDto.class);
		GeneralUserDto generalUser = userToGeneralUser(article.getAuthor());
		
		articleDto.setAuthor(generalUser);
		
		return articleDto;
		
	}
	
public ArticleImagesDto articleToArticleImageDto(Article article) {
		
	ArticleImagesDto articleImagesDto = modelMapper.map(article, ArticleImagesDto.class);
	GeneralUserDto generalUser = userToGeneralUser(article.getAuthor());
	
	List<ArticleImage> articleImages = articleImageRepository.findImagesByArticleId(article.getArticleId());
		
	articleImagesDto.setAuthor(generalUser);
	List<String> images = new ArrayList<String>();
	for(ArticleImage artiImg : articleImages) {
		byte[] decodedBytes = Base64.getDecoder().decode(artiImg.getImage());
		String decodedStringImage = new String(decodedBytes);
		
		
		images.add(decodedStringImage);
	}
	articleImagesDto.setImages(images);
	
	return articleImagesDto;
}



public byte[] getImage(Long articleId) {
    if (articleId == null) {
        throw new NoSuchElementException("ImageId is null");
    }
    List<ArticleImage> articleImages = articleImageRepository.findImagesByArticleId(articleId);
    
    if(articleImages.isEmpty()) {
    	
    }

    byte[] imageBytes = java.util.Base64.getDecoder().decode(articleImages.get(0).getImage());
    
    return imageBytes;
}

}

