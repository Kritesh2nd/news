package com.example.school.service.interfaces;

import java.util.List;

import com.example.school.dto.user.GeneralUserDto;
import com.example.school.dto.user.RegisterUserDto;
import com.example.school.model.User;
import com.example.school.response.BasicResponseDto;

public interface UserServiceInterfaces {
/*
 * C[R]UD
 * get all user list
 * get user by id
 * 
 * [C]RUD
 * add user
 * 
 * CR[U]D
 * update user
 * 
 * CRU[D]
 * delete user
 * delete all users
 * 
 * */
	
//	public List<User> getUserList();
	public List<GeneralUserDto> getGeneralUserList();
//	public User getUserById(Long id);
	public GeneralUserDto getGeneralUserById(Long id);
	public BasicResponseDto addUser(RegisterUserDto newUser);
	public BasicResponseDto updateUser(GeneralUserDto newUserData);
	public BasicResponseDto updateUserPassword(Long id,String password);
	public BasicResponseDto deleteUser(Long id);
	public BasicResponseDto deleteAllUsers();
}
