package com.exm.news.security.filter;

import java.util.Base64;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.exm.news.security.manager.AuthManager;

import io.jsonwebtoken.ExpiredJwtException;

import com.exm.news.security.authentication.UserAuth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class BasicAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private AuthManager authManager;
	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authHeader = request.getHeader("Authorization");
		
//		try {
		if(authHeader == null) {
			filterChain.doFilter(request, response);
			return;
		}
		if(authHeader.startsWith("Basic ")) {
			System.out.println("basic auth");
			String username = extractUsernameAndPassword(authHeader)[0];
			String password = extractUsernameAndPassword(authHeader)[1];
			UserAuth ua = new UserAuth(false, username,password,null,null);
			
			UserAuth auth = (UserAuth) authManager.authenticate(ua);
			
			if(auth.isAuthenticated()) {				
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
			
			
//			else {
//				throw new BadCredentialsException("Bad Credentials Exceptionnn");
//			}
			
		}
		filterChain.doFilter(request, response);
		
//		}
//		catch(ExpiredJwtException | BadCredentialsException ex) {
//			
//			System.out.println("ex: "+ex.toString());
//			throw new BadCredentialsException("bad cred excp");
//		}
	}
	
	
	private String[] extractUsernameAndPassword(String authorization) {
		String base64Credentials = authorization.substring("Basic".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);

        final String[] values = credentials.split(":", 2); // values = [username, password]
        
        return values;
	}
}
