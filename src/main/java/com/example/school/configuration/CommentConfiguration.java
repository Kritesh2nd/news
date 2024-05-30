package com.example.school.configuration;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.school.model.Article;
import com.example.school.model.Comment;
import com.example.school.model.User;
import com.example.school.repository.ArticleRepository;
import com.example.school.repository.CommentRepository;
import com.example.school.repository.UserRepository;

@Configuration
public class CommentConfiguration {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ArticleRepository articelRepository;
	
	@Bean
	CommandLineRunner commentCommanddLineRuuner(CommentRepository commentRepository) {
		return args -> {
			try {
				LocalDateTime ldt = LocalDateTime.now();
				
				Optional<User> user1 = userRepository.findById((long)1);
				Optional<User> user2 = userRepository.findById((long)2);
				
				Optional<Article> article1 = articelRepository.findById((long)1);
				Optional<Article> article2 = articelRepository.findById((long)2);
				
				Comment comment1 = new Comment("Is the cat found?",ldt,user1.get(),article1.get());
				Comment comment2 = new Comment("Golden cat lost again",ldt,user2.get(),article1.get());
				Comment comment3 = new Comment("WOW, a dog search.",ldt,user2.get(),article2.get());
				Comment comment4 = new Comment("Good dog.",ldt,user1.get(),article2.get());
				
				commentRepository.save(comment1);
				commentRepository.save(comment2);
				commentRepository.save(comment3);
				commentRepository.save(comment4);
			}
			catch(Exception e) {
				System.out.println("##### Commnet ##### data already exists in database");
			}
		};
	}
}
