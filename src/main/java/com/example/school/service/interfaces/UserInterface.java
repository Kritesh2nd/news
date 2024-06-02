package com.example.school.service.interfaces;

import java.util.List;

import com.example.school.dto.user.GeneralUserDto;
import com.example.school.dto.user.RegisterUserDto;

public interface UserInterface {
	
	public List<GeneralUserDto> getUserList();
	public GeneralUserDto getUserById(long id);
	public void addUser(RegisterUserDto registerUser);
	public void updateUserById(long id);
	public void deleteUserById(long id);
	
}
