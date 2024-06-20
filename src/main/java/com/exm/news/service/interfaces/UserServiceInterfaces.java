package com.exm.news.service.interfaces;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.exm.news.dto.user.GeneralUserDto;
import com.exm.news.dto.user.LoginUserDto;
import com.exm.news.dto.user.RegisterUserDto;
import com.exm.news.response.BasicResponseDto;

public interface UserServiceInterfaces {

//	C[R]UD
	public List<GeneralUserDto> getGeneralUserList();
	public GeneralUserDto getGeneralUserById(Long id);
	public UserDetails authenticate(LoginUserDto input);	
	
//	[C]RUD
	public BasicResponseDto signup(RegisterUserDto input);
	
//	CR[U]D
	public BasicResponseDto updateUser(GeneralUserDto newUserData);
	public BasicResponseDto updateUserPassword(Long id,String password);
	
//	CRU[D]
	public BasicResponseDto deleteUser(Long id);
	public BasicResponseDto deleteAllUsers();
	
	
	
}
