package com.example.school.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.school.model.Tag;
import com.example.school.repository.TagRepository;

@Configuration
public class TagConfiguration {
	
	@Bean
	CommandLineRunner tagCommanddLineRuuner(TagRepository tagRepository) {
		return args -> {
			try {
				Tag tag1 = new Tag("Politics");
				Tag tag2 = new Tag("Economy");
				Tag tag3 = new Tag("Technology");
				Tag tag4 = new Tag("Sports");
				Tag tag5 = new Tag("Health");
				Tag tag6 = new Tag("Education");
				Tag tag7 = new Tag("Environment");
				Tag tag8 = new Tag("Entertainment");
				Tag tag9 = new Tag("Crime");
				Tag tag10 = new Tag("Science");
				
				tagRepository.save(tag1);
				tagRepository.save(tag2);
				tagRepository.save(tag3);
				tagRepository.save(tag4);
				tagRepository.save(tag5);
				tagRepository.save(tag6);
				tagRepository.save(tag7);
				tagRepository.save(tag8);
				tagRepository.save(tag9);
				tagRepository.save(tag10);
			}
			catch(Exception e) {
				System.out.println("##### Tag ##### data already exists in database");
			}
//			userRepository.save(new User("liya","password","liya@gmail.com","Liya","Elf","admin"));
//			userRepository.save(new User("lifa","password","lifa@gmail.com","Lifa","Elf","admin"));
		};
	}
}
