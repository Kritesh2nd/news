package com.exm.news.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exm.news.dto.user.LoginUserDto;
import com.exm.news.dto.user.RegisterUserDto;
import com.exm.news.model.User;
import com.exm.news.response.LoginResponse;
import com.exm.news.security.service.AuthenticationService;
import com.exm.news.security.service.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	@Autowired
	private JwtService jwtService;
	@Autowired
    private AuthenticationService authenticationService;
	
	@GetMapping("/demo")
	public String demo() {
		return "demooooo";
	}
	
	@PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
    	
    	System.out.println("controller - loginUserDto [1]");
    	User authenticatedUser = authenticationService.authenticate(loginUserDto);

    	System.out.println("controller - loginUserDto [2]");
        String jwtToken = jwtService.generateToken(authenticatedUser);

        System.out.println("controller - loginUserDto [3]");
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
