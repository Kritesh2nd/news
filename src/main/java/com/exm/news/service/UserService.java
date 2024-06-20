package com.exm.news.service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.exm.news.dto.user.GeneralUserDto;
import com.exm.news.dto.user.LoginUserDto;
import com.exm.news.dto.user.RegisterUserDto;
import com.exm.news.model.Authority;
import com.exm.news.model.User;
import com.exm.news.repository.UserRepository;
import com.exm.news.response.BasicResponseDto;
import com.exm.news.security.authentication.UserAuth;
import com.exm.news.security.manager.AuthManager;
import com.exm.news.security.provider.AuthProvider;
import com.exm.news.service.interfaces.UserServiceInterfaces;

@Service
public class UserService implements UserServiceInterfaces{

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthProvider authProvider;
	
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
	public UserDetails authenticate(LoginUserDto input) {
		
		Authentication userAuth = modelMapper.map(input, Authentication.class);
		
		UserAuth auth = (UserAuth) authProvider.authenticate(userAuth);
		
		if(!auth.isAuthenticated()) {
			throw new UsernameNotFoundException("Username not found"); 
		}
		
		User registeredUser = userRepository.findUserByEmail(auth.getEmail());
		UserDetails userDetail = modelMapper.map(registeredUser, UserDetails.class);
		
		return userDetail;
	}
	
	
	
	

	@Override
	public BasicResponseDto signup(RegisterUserDto newUser) {
//		TODO use of native query
//		Using native query findUserByEmail()
		User userWithEmail = userRepository.findUserByEmail(newUser.getEmail());
		if(userWithEmail!=null) {
			return new BasicResponseDto("User with this email already exits.",false);
		}

		
		User user = modelMapper.map(newUser, User.class);
		
		String encryptedPassword = encodePassword(user.getPassword());
		user.setPassword(encryptedPassword);
		
//		adding user without authority
		userRepository.save(user);
		
		Set<User> userSet = new HashSet<User>();
		User newAddedUser = userRepository.findUserByEmail(newUser.getEmail());
		userSet.add(newAddedUser);
		
		Set<Authority> authoritySet = new HashSet<Authority>();
		Authority auth = new Authority(Long.valueOf(1),"user",userSet);		
		authoritySet.add(auth);
		
//		updating previously added used by adding new authority
		user.setAuthorities(authoritySet);
		
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
		User user = userRepository.findUserById(id);
		if(user == null) {
			throw new NoSuchElementException("User not found");
		}
		return user;
	}
	
	public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

}







