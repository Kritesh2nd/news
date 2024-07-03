package com.exm.news.security.authentication;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class TokenAuth implements Authentication{

	private boolean authenticated;
	private String email;
	private String token;
	private List<GrantedAuthority> authority;
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authority;
	}
	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}
	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.authenticated = isAuthenticated;
	}
	
	public TokenAuth() {}
	
	public TokenAuth(boolean authenticated, String email, String token, List<GrantedAuthority> authority) {
		super();
		this.authenticated = authenticated;
		this.email = email;
		this.token = token;
		this.authority = authority;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public List<GrantedAuthority> getAuthority() {
		return authority;
	}
	
	public void setAuthority(List<GrantedAuthority> authority) {
		this.authority = authority;
	}
	
}
