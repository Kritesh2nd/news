package com.exm.news.security.provider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.exm.news.model.Authority;
import com.exm.news.model.User;
import com.exm.news.repository.UserRepository;
import com.exm.news.security.authentication.UserAuth;
import com.exm.news.service.JwtService;

@Component
public class TokenAuthProvider implements AuthenticationProvider{
	
	@Autowired
	private UserRepository userRespository;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		UserAuth userTokenAuth = (UserAuth) authentication;
		
		System.out.println("TokenAuthProvider, userTokenAuth: "+userTokenAuth.getEmail());
		
		User user = userRespository.findUserByEmail(userTokenAuth.getEmail());
		
		System.out.println("user to string: "+user.toString());
		
		if(user == null || userTokenAuth.getToken()==null) {
			throw new UsernameNotFoundException("username or token not found");
		}
		
		if(jwtService.isTokenExpired(userTokenAuth.getToken())) {
			System.out.println("token expired");
			throw new UsernameNotFoundException("username or token not found");
		}
		
		if(jwtService.isTokenValid(userTokenAuth.getToken(), userTokenAuth)){
			System.out.println("token not expired");
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
