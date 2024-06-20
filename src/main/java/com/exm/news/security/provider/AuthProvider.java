package com.exm.news.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.exm.news.model.User;
import com.exm.news.repository.UserRepository;
import com.exm.news.security.authentication.UserAuth;

@Component
public class AuthProvider implements AuthenticationProvider{

	@Autowired
	private UserRepository userRespository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		
		UserAuth ua = (UserAuth) authentication;
		System.out.println("ca.getPassword() top "+ua.getPassword());
		
		User user = userRespository.findUserByEmail(ua.getEmail());
		
		System.out.println("ca.getPassword() bottom "+ua.getPassword());
		System.out.println("user.get().getPassword() "+user.getPassword());
		
		if(user.getUserId() == null) {
			throw new UsernameNotFoundException("username not found");
		}
		
		if(passwordEncoder.matches(ua.getPassword(), user.getPassword())) {
			return new UserAuth(true, null, null, null);
		}
		
		throw new BadCredentialsException("Bad Credentials Exception");
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UserAuth.class.equals(authentication);
	}
	
	
}
