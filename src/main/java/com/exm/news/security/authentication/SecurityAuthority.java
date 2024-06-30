package com.exm.news.security.authentication;


import org.springframework.security.core.GrantedAuthority;

import com.exm.news.model.Authority;

@SuppressWarnings("serial")
public class SecurityAuthority implements GrantedAuthority{

	private Authority authority;
	
	@Override
	public String getAuthority() {
		return authority.getName();
	}

	public SecurityAuthority() {}
	
	public SecurityAuthority(Authority authority) {
		super();
		this.authority = authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

}
