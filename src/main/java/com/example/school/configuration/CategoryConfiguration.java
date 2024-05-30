package com.example.school.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.school.model.Category;
import com.example.school.repository.CategoryRepository;

@Configuration
public class CategoryConfiguration {
	
	@Bean
	CommandLineRunner categoryCommanddLineRuuner(CategoryRepository categoryRepository ) {
		return args -> {
			try {
				
				Category category1 = new Category("World News");
				Category category2 = new Category("National News");
				Category category3 = new Category("Business");
				Category category4 = new Category("Technology");
				Category category5 = new Category("Sports");
				Category category6 = new Category("Health");
				Category category7 = new Category("Lifestyle");
				Category category8 = new Category("Entertainment");
				Category category9 = new Category("Science and Environment");
				Category category10 = new Category("Opinion");
				categoryRepository.save(category1);
				categoryRepository.save(category2);
				categoryRepository.save(category3);
				categoryRepository.save(category4);
				categoryRepository.save(category5);
				categoryRepository.save(category6);
				categoryRepository.save(category7);
				categoryRepository.save(category8);
				categoryRepository.save(category9);
				categoryRepository.save(category10);
			}
			catch(Exception e) {
				System.out.println("##### Category ##### data already exists in database");
			}
		};
	}
}
