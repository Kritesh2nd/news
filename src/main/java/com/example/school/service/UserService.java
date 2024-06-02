package com.example.school.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.school.dto.user.GeneralUserDto;
import com.example.school.dto.user.LoginUserDto;
import com.example.school.dto.user.RegisterUserDto;
import com.example.school.model.User;
import com.example.school.repository.UserRepository;
import com.example.school.service.interfaces.UserInterface;

@Service
public class UserService implements UserInterface{
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<GeneralUserDto> getUserList() {
		List<User> userList = userRepository.findAll();
		List<GeneralUserDto> generalUserDto = userList.stream()
	            .map(user -> modelMapper.map(user, GeneralUserDto.class))
	            .collect(Collectors.toList());
		return generalUserDto;
	}

	@Override
	public GeneralUserDto getUserById(long id) {
		GeneralUserDto user = modelMapper.map(userRepository.findById(id), GeneralUserDto.class);
		return user;
	}

	@Override
	public void addUser(RegisterUserDto registerUser) {
		userRepository.save(modelMapper.map(registerUser, User.class));
	}

	@Override
	public void updateUserById(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserById(long id) {
		// TODO Auto-generated method stub
		
	}
	
	public void login(LoginUserDto user) {
//		User uEmail = userRepository.getByEmail(user.getEmail());
	}
}
