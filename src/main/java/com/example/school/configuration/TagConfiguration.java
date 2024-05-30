package com.example.school.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.school.repository.TagRepository;

@Configuration
public class TagConfiguration {
	
	@Bean
	CommandLineRunner tagCommanddLineRuuner(TagRepository tagRepository) {
		return args -> {
//			userRepository.save(new User("liya","password","liya@gmail.com","Liya","Elf","admin"));
//			userRepository.save(new User("lifa","password","lifa@gmail.com","Lifa","Elf","admin"));
		};
	}
}
