package com.exm.news.security.provider;

import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.AuthenticationProvider;

import com.exm.news.entity.app.Authority;
import com.exm.news.entity.app.User;
import com.exm.news.repository.app.UserRepository;
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
	
		User user = userRespository.findUserByEmail(basicUserAuth.getEmail());
		
		if(user == null) {
			return new UserAuth(false, null, null, null, null);
		}
		
		if(passwordEncoder.matches(basicUserAuth.getPassword(), user.getPassword())) {
			
			Set<GrantedAuthority> authoritySet = new HashSet<GrantedAuthority>(); 
			for(Authority auth : user.getAuthorities() ) {
				authoritySet.add(new SimpleGrantedAuthority(auth.getName()));
			}
			List<GrantedAuthority> authorityList = new ArrayList<>(authoritySet); 
					
			return new UserAuth(true, user.getEmail(), null, null, authorityList);
		}

		return new UserAuth(false, user.getEmail(), null, null, null);
	}

	
	@Override
	public boolean supports(Class<?> authentication) {
		return UserAuth.class.equals(authentication);
	}
}
