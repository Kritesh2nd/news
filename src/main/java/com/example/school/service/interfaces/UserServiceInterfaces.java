package com.example.school.service.interfaces;

import java.util.List;

import com.example.school.dto.user.GeneralUserDto;
import com.example.school.dto.user.RegisterUserDto;

import com.example.school.response.BasicResponseDto;

public interface UserServiceInterfaces {

//	C[R]UD
	public List<GeneralUserDto> getGeneralUserList();
	public GeneralUserDto getGeneralUserById(Long id);
	
//	[C]RUD
	public BasicResponseDto addUser(RegisterUserDto newUser);
	
//	CR[U]D
	public BasicResponseDto updateUser(GeneralUserDto newUserData);
	public BasicResponseDto updateUserPassword(Long id,String password);
	
//	CRU[D]
	public BasicResponseDto deleteUser(Long id);
	public BasicResponseDto deleteAllUsers();
}
