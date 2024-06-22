package com.exm.news.security.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.exm.news.service.JwtService;
import com.exm.news.security.manager.AuthManager;
import com.exm.news.security.authentication.UserAuth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class BearerTokenFilter extends OncePerRequestFilter{
	
	@Autowired
	private AuthManager authManager;
	
	@Autowired
	private JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		
		if(authHeader == null) {
			filterChain.doFilter(request, response);
			return;
		}
		
		if(authHeader.startsWith("Bearer ")) {
			System.out.println("bearer token[0]");
			
			try {
				final String jwt = authHeader.substring(7);
				
				final String userEmail = jwtService.extractEmail(jwt);
				
				System.out.println(jwt+"\n"+userEmail);
				
				UserAuth userAuth = new UserAuth(false, userEmail,null,jwt,null);
				
				UserAuth auth = (UserAuth) authManager.authenticate(userAuth);
				
				if(auth.isAuthenticated()) {				
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
			}
			catch(Exception e) {
				System.out.println("expired token, error: "+e);
			}
			
            System.out.println("bearer token[000]");
		}
		filterChain.doFilter(request, response);
		return;
	}

}
