package com.example.school.configuration;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.school.model.Article;
import com.example.school.model.Category;
import com.example.school.model.User;
import com.example.school.repository.ArticleRepository;
import com.example.school.repository.CategoryRepository;
//import com.example.school.repository.CategoryRepository;
import com.example.school.repository.UserRepository;

@Configuration
public class ArticleConfiguration {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Bean
	CommandLineRunner articleCommanddLineRuuner(ArticleRepository articelRepository) {
		return args -> {
			
			try {
				Optional<User> user = userRepository.findById((long)1);
				Optional<Category> category = categoryRepository.findById((long)1);
				LocalDateTime ldt = LocalDateTime.now();
				Article article1 = new Article("Cat is Missing","One golden cat is missing",ldt,user.get(),category.get());
				Article article2 = new Article("Dod is Searching","One Silver dog is searching a golden cat",ldt,user.get(),category.get());
				articelRepository.save(article1);
				articelRepository.save(article2);
			}
			catch(Exception e) {
				System.out.println("##### Article ##### data already exists in database");
			}
                                      
		};
	}
}
