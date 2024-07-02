package com.exm.news.security.authentication;


import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.exm.news.entity.app.User;

import java.util.Collection;

@SuppressWarnings("serial")
public class SecurityUser implements UserDetails{
	
	private User user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return List.of(() -> "read");
		return user.getAuthorities()
				.stream()
				.map(SecurityAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	public SecurityUser() {}
	
	public SecurityUser(User user) {
		super();
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
}
