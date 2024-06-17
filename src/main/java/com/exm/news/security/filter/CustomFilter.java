package com.exm.news.security.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exm.news.security.authentication.CustomAuth;
import com.exm.news.security.manager.AuthManager;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomFilter extends OncePerRequestFilter{

	@Autowired
	private AuthManager authManager;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		final String authorization = request.getHeader("Authorization");
		
		if(authorization == null) {
			chain.doFilter(request, response);
			return;
		}
		System.out.println("authorization: "+authorization);
		
		String base64Credentials = authorization.substring("Basic".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);

        // credentials = username:password
        final String[] values = credentials.split(":", 2);
        String username = values[0];
        String password = values[1];     
		
        System.out.println("username: "+username+", password: "+password);

        CustomAuth ca = new CustomAuth(false, username,password);
        
        System.out.println("ca: "+ca.getUsername()+", "+ca.getPassword());
        
        CustomAuth auth = (CustomAuth) authManager.authenticate(ca);
		System.out.println("auth: "+auth.toString());
		if(auth.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(auth);
			chain.doFilter(request, response); //only when auth works
			System.out.println("auth.isAuthenticated(): "+auth.isAuthenticated());
			return;
		}
        
        chain.doFilter(request, response);
		
	}

}
