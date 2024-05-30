package com.example.school.configuration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.school.model.Article;
import com.example.school.model.ArticleTag;
import com.example.school.model.Tag;
import com.example.school.repository.ArticleRepository;
import com.example.school.repository.ArticleTagRepository;
import com.example.school.repository.TagRepository;

@Configuration
public class ArticleTagConfiguration {
	
	@Autowired
	private ArticleRepository articelRepository;
	
	@Autowired
	private TagRepository tagRepository;
	
	
	@Bean
	CommandLineRunner articleTagCommanddLineRuuner(ArticleTagRepository articleTagRepository) {
		return args -> {
			try {
				Optional<Article> article1 = articelRepository.findById((long)1);
				Optional<Article> article2 = articelRepository.findById((long)2);
				
				Optional<Tag> tag1 = tagRepository.findById((long)1);
				Optional<Tag> tag2 = tagRepository.findById((long)2);
				
				ArticleTag articleTag1 = new ArticleTag(article1.get(),tag1.get());
				ArticleTag articleTag2 = new ArticleTag(article1.get(),tag2.get());
				ArticleTag articleTag3 = new ArticleTag(article2.get(),tag1.get());
				ArticleTag articleTag4 = new ArticleTag(article2.get(),tag2.get());
				
				articleTagRepository.save(articleTag1);
				articleTagRepository.save(articleTag2);
				articleTagRepository.save(articleTag3);
				articleTagRepository.save(articleTag4);
			}
			catch(Exception e) {
				System.out.println("##### article ##### data already exists in database");
			}
		};
	}
}
