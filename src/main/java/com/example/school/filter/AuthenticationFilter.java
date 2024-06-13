package com.example.school.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.school.repository.UserRepository;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@Component

@Order(2)
public class AuthenticationFilter implements Filter {

	@Autowired
	private UserRepository userRepository;
	
    @Override   
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
    	
    	Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);
    	System.out.println("servletRequest: ");
    	log.info("User is being authenticated");
    	
    	filterChain.doFilter(servletRequest, servletResponse);
    }
    
}

