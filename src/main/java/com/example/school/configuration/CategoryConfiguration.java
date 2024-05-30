package com.example.school.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.school.repository.UserRepository;

//import com.example.school.repository.CategoryRepository;

@Configuration
public class CategoryConfiguration {
	
	@Bean
	CommandLineRunner categoryCommanddLineRuuner(UserRepository categoryRepository ) {
		return args -> {
			
		};
	}
}
