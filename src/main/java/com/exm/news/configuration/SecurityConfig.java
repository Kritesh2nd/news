package com.exm.news.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.exm.news.security.filter.BearerTokenFilter;
import com.exm.news.security.filter.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private BasicAuthenticationFilter customJwtFilter;
	
	@Autowired
	private BearerTokenFilter bearerFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				
				.csrf(csrf -> csrf.disable())
//				.cors(cors -> cors.disable())
				.addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(bearerFilter, UsernamePasswordAuthenticationFilter.class)
				
				.authorizeHttpRequests(authorize -> authorize
						
						.requestMatchers("/signup").permitAll()
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

}
