package com.example.school.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.school.model.User;
import com.example.school.repository.UserRepository;

@Configuration
public class UserConfiguration {
	
	@Bean
	CommandLineRunner userCommanddLineRuuner(UserRepository userRepository) {
		return args -> {
			try {
				userRepository.save(new User("liya","password","liya@gmail.com","Liya","Elf","admin"));
				userRepository.save(new User("lifa","password","lifa@gmail.com","Lifa","Elf","admin"));
			}
			catch(Exception e) {
				System.out.println("##### User ##### data already exists in database");
			}
		};
	}
}
