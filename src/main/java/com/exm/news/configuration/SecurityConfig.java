package com.exm.news.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.exm.news.security.filter.CustomFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomFilter customJwtFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				
				.csrf(csrf -> csrf.disable())
				
				.addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class)
				.authorizeHttpRequests(authorize -> authorize
						
						.requestMatchers("/user/add").permitAll()
						.requestMatchers("/user/me").permitAll()
						.requestMatchers("/user/list").permitAll()
						.requestMatchers("/auth/**").permitAll()
						
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

	// add constructor getter setter
}
