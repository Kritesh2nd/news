package com.exm.news.dto.user;

public class UpdateAuthorityDto {

	private Long userId;
	
    private String authority;

    public UpdateAuthorityDto() {}
    
	public UpdateAuthorityDto(Long userId, String authority) {
		super();
		this.userId = userId;
		this.authority = authority;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
    
    
}
