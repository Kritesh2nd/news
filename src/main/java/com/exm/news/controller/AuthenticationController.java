package com.exm.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exm.news.constant.PathConstant;
import com.exm.news.dto.user.LoginUserDto;
import com.exm.news.dto.user.RegisterUserDto;
import com.exm.news.model.User;
import com.exm.news.response.BasicResponseDto;
import com.exm.news.response.LoginResponse;
import com.exm.news.service.JwtService;
import com.exm.news.service.UserService;

@RestController
@RequestMapping("/")
public class AuthenticationController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	@GetMapping("/cat")
	public String cat() {
		return "Auth cat";
	}
	
	@PostMapping(PathConstant.SIGNUP)
    public ResponseEntity<BasicResponseDto> register(@RequestBody RegisterUserDto registerUserDto) {
        return ResponseEntity.ok(userService.signup(registerUserDto));
    }

    @PostMapping(PathConstant.LOGIN)
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        
    	UserDetails userDetail = userService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(userDetail);

        LoginResponse loginResponse = new LoginResponse();
        
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
    
    @GetMapping(PathConstant.ME)
    public ResponseEntity<?> authenticatedUser() {
		System.out.println("authenticatedUser me");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("authentication.getPrincipal() me: "+authentication.getPrincipal());
        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

}
