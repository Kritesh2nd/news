package com.exm.news.security.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.exm.news.dto.user.LoginUserDto;
import com.exm.news.dto.user.RegisterUserDto;
import com.exm.news.model.User;
import com.exm.news.repository.UserRepository;

@Service
public class AuthenticationService {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	

	public User signup(RegisterUserDto input) {
		User addUser = modelMapper.map(input, User.class);
		String encodedPassword = passwordEncoder.encode(addUser.getPassword());
		addUser.setPassword(encodedPassword);
        return userRepository.save(addUser);
    }
	

	public User authenticate(LoginUserDto input) {
		System.out.println("SecurityUser[1]: email:"+input.getEmail()+", password:"+input.getPassword());
		
		
		
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
               
        System.out.println("SecurityUser[2]: email:"+input.getEmail()+", password:"+input.getPassword());

        
        return userRepository.findUserByEmailOptonal(input.getEmail())
                .orElseThrow();
    }
}
