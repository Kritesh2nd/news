package com.exm.news.security.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.exm.news.service.JwtService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

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
	
	@Autowired
	private HandlerExceptionResolver handlerExceptionResolver;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, SignatureException, ExpiredJwtException {
		
		final String authHeader = request.getHeader("Authorization");
		
		if(authHeader == null) {
			filterChain.doFilter(request, response);
			return;
		}
		
		if(authHeader.startsWith("Bearer ")) {
			try {
			
				final String jwt = authHeader.substring(7);
				
				final String userEmail = jwtService.extractEmail(jwt);
				
				UserAuth userAuth = new UserAuth(false, userEmail,null,jwt,null);
				UserAuth auth = (UserAuth) authManager.authenticate(userAuth);
				
				if(auth.isAuthenticated()) {				
					SecurityContextHolder.getContext().setAuthentication(auth);
					filterChain.doFilter(request, response);
					return;
				}else {
					handlerExceptionResolver.resolveException(request, response, null, new BadCredentialsException("bad cred"));
					return;
				}
			}
			catch(UsernameNotFoundException | ExpiredJwtException | SignatureException e) {
				handlerExceptionResolver.resolveException(request, response, null, e);
				return;
			}
		}
		filterChain.doFilter(request, response);
		
	}
}
