package com.exm.news.security.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.exm.news.security.provider.AuthProvider;

@Component
public class AuthManager implements AuthenticationManager{

	@Autowired
	private AuthProvider provider;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("AuthManager----");
		System.out.println(authentication);
		System.out.println(authentication.getClass());
		if(provider.supports(authentication.getClass())) {
			return provider.authenticate(authentication);
		}
		
		throw new BadCredentialsException("Bad Credentials Exception");
	}

}
