package com.exm.news.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.exm.news.security.filter.CustomFilter;


@Configuration
public class SecurityConfig {
	
	@Autowired
	private CustomFilter customFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.addFilterAt(customFilter, UsernamePasswordAuthenticationFilter.class)
				.authorizeHttpRequests((authorize) -> authorize
						
						.requestMatchers("/user/add").permitAll()
						
						.requestMatchers("/article/list").permitAll()
						.requestMatchers("/article/listAll").permitAll()
						.requestMatchers("/article/{id}").permitAll()
						.requestMatchers("/article/listByDate").permitAll()
						.requestMatchers("/article/listByDateRange").permitAll()
						.requestMatchers("/article/listByCategory").permitAll()
						.requestMatchers("/article/listByDateAndCategory").permitAll()
						.requestMatchers("/article/listByDateRangeWithCategory").permitAll()
						
				        .anyRequest().authenticated()
				)
				
				.build();
	}

	public SecurityConfig() {}
	public SecurityConfig(CustomFilter customFilter) {
		super();
		this.customFilter = customFilter;
	}


	public CustomFilter getCustomFilter() {
		return customFilter;
	}


	public void setCustomFilter(CustomFilter customFilter) {
		this.customFilter = customFilter;
	}
	
}
