package com.exm.news.security.provider;

import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.exm.news.model.User;
import com.exm.news.model.Authority;
import com.exm.news.repository.UserRepository;
import com.exm.news.security.authentication.UserAuth;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;

@Component
public class BasicAuthProvider implements AuthenticationProvider{

	@Autowired
	private UserRepository userRespository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		UserAuth basicUserAuth = (UserAuth) authentication;
		
		System.out.println("BasicAuthProvider, basicUserAuth: "+basicUserAuth.getEmail());
		User user = userRespository.findUserByEmail(basicUserAuth.getEmail());
		
		
		if(user == null) {
			throw new UsernameNotFoundException("username not found");
		}
		
		if(passwordEncoder.matches(basicUserAuth.getPassword(), user.getPassword())) {
			
			Set<GrantedAuthority> authoritySet = new HashSet<GrantedAuthority>(); 
			for(Authority auth : user.getAuthorities() ) {
				authoritySet.add(new SimpleGrantedAuthority(auth.getName()));
			}
			List<GrantedAuthority> authorityList = new ArrayList<>(authoritySet); 
					
			return new UserAuth(true, user.getEmail(), null, null, authorityList);
		}
	
		throw new BadCredentialsException("Bad Credentials Exception");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UserAuth.class.equals(authentication);
	}
	
}
