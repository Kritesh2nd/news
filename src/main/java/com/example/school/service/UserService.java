package com.example.school.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.school.dto.user.GeneralUserDto;
import com.example.school.dto.user.RegisterUserDto;
import com.example.school.model.User;
import com.example.school.repository.UserRepository;
import com.example.school.response.BasicResponseDto;
import com.example.school.service.interfaces.UserServiceInterfaces;

@Service
public class UserService implements UserServiceInterfaces, UserDetailsService{

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public List<GeneralUserDto> getGeneralUserList() {
		List<User> userList = userRepository.findAll();
		List<GeneralUserDto> generalUserList = userList.stream()
				.map(user -> modelMapper.map(user, GeneralUserDto.class))
				.collect(Collectors.toList());

		return generalUserList;
	}

	@Override
	public GeneralUserDto getGeneralUserById(Long id) {
		User user = getUserById(id);

		GeneralUserDto generalUser = modelMapper.map(user, GeneralUserDto.class);

		return generalUser;
	}

	@Override
	public BasicResponseDto addUser(RegisterUserDto newUser) {
//		TODO use of native query
//		Using native query findUserByEmail()
		User userWithEmail = userRepository.findUserByEmail(newUser.getEmail());
		if(userWithEmail!=null) {
			return new BasicResponseDto("User with this email already exits.",false);
		}

		User user = modelMapper.map(newUser, User.class);
		String encryptedPassword = encodePassword(user.getPassword());
		user.setPassword(encryptedPassword);
		userRepository.save(user);
		
		return new BasicResponseDto("New user added successfully.",true);
	}


	@Override
	public BasicResponseDto updateUser(GeneralUserDto newUserData) {
		try {
			User updatedUser = getUserById((Long) newUserData.getUserId());
			updatedUser.setUsername(newUserData.getUsername());
			updatedUser.setEmail(newUserData.getEmail());
			updatedUser.setFirstName(newUserData.getFirstName());
			updatedUser.setLastName(newUserData.getLastName());
			updatedUser.setRole(newUserData.getUsername());

			userRepository.save(updatedUser);
		}
		catch(Exception e) {
//			return new BasicResponseDto (String.format("Unable to update user for id %f", newUserData.getUserId()),false);
			return new BasicResponseDto ("Unable to update user for id: "+ newUserData.getUserId(),false);
		}
		return new BasicResponseDto("User updated successfully.",true);
	}


	@Override
	public BasicResponseDto updateUserPassword(Long id, String password) {
		User newUser = getUserById(id);

		newUser.setPassword(password);
		userRepository.save(newUser);

		return new BasicResponseDto("Password updated successfully.",true);
	}

	@Override
	public BasicResponseDto deleteUser(Long id) {
		User deleteUser = getUserById(id);

		userRepository.delete(deleteUser);

		return new BasicResponseDto("User deleted successfully.",true);
	}

	@Override
	public BasicResponseDto deleteAllUsers() {
		userRepository.deleteAll();
		return new BasicResponseDto("All users deleted successfully.",true);
	}


	
	public User getUserById(Long id) {
//		Using native query findUserById()
		User user = userRepository.findUserById(id);
		if(user == null) {
			throw new NoSuchElementException("User not found");
		}
		return user;
	}
	
	public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userWithEmail = userRepository.findUserByEmail(username);
		return userWithEmail;
	}
}







