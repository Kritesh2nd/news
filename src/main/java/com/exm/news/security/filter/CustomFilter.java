package com.exm.news.security.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.exm.news.security.authentication.UserAuth;
import com.exm.news.security.manager.AuthManager;
//import com.exm.news.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomFilter extends OncePerRequestFilter{

	
	@Autowired
	private AuthManager authManager;
	
//	@Autowired 
//	private UserDetailsService userDetailsService;
	
//	@Autowired
//	private JwtService jwtService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authHeader = request.getHeader("Authorization");
		
		
		if(authHeader == null) {
			filterChain.doFilter(request, response);
			return;
		}
		
		System.out.println("authorization: "+authHeader);
		
		if(authHeader.startsWith("Basic ")) {
			String username = extractUsernameAndPassword(authHeader)[0];
			String password = extractUsernameAndPassword(authHeader)[1];
			UserAuth ua = new UserAuth(false, username,password,null);
			
			UserAuth auth = (UserAuth) authManager.authenticate(ua);
			
			if(auth.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		else if(authHeader.startsWith("Bearer ")) {
//			final String jwt = authHeader.substring(7);
//            final String userEmail = jwtService.extractEmail(jwt);
//			
//			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
		}
		
		filterChain.doFilter(request, response);
		
		
		
	}

	
	
	private String[] extractUsernameAndPassword(String authorization) {
		String base64Credentials = authorization.substring("Basic".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);

        final String[] values = credentials.split(":", 2); // values = [username, password]
        
        return values;
	}
}
