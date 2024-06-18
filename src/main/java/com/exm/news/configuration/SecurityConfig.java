package com.exm.news.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.exm.news.security.filter.JwtAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	@Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				
				.csrf(csrf -> csrf.disable())
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
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


	public SecurityConfig() {}
	
	public SecurityConfig(AuthenticationProvider authenticationProvider,
			JwtAuthenticationFilter jwtAuthenticationFilter) {
		super();
		this.authenticationProvider = authenticationProvider;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}


	public AuthenticationProvider getAuthenticationProvider() {
		return authenticationProvider;
	}


	public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
		this.authenticationProvider = authenticationProvider;
	}


	public JwtAuthenticationFilter getJwtAuthenticationFilter() {
		return jwtAuthenticationFilter;
	}


	public void setJwtAuthenticationFilter(JwtAuthenticationFilter jwtAuthenticationFilter) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}
	
	
	
}
