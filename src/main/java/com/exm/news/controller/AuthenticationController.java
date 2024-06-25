package com.exm.news.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exm.news.service.UserService;

import jakarta.validation.Valid;

import com.exm.news.constant.PathConstant;
import com.exm.news.response.LoginResponse;
import com.exm.news.dto.user.RegisterUserDto;
import com.exm.news.response.BasicResponseDto;

@RestController
@RequestMapping("/")
public class AuthenticationController {

	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasAnyAuthority('admin')")
	@GetMapping("admin")
	public String admin() {
		return "Admin access";
	}
	
	@PreAuthorize("hasAnyAuthority('admin','editor')")
	@GetMapping("editor")
	public String editor() {
		return "Editor access";
	}
	
	@PreAuthorize("hasAnyAuthority('admin','editor','reader')")
	@GetMapping("reader")
	public String reader() {
		return "Reader access";
	}
	
	@PostMapping(PathConstant.SIGNUP)
    public ResponseEntity<BasicResponseDto> register(@RequestBody @Valid RegisterUserDto registerUserDto) {
        return ResponseEntity.ok(userService.signup(registerUserDto));
    }

    @PostMapping(PathConstant.LOGIN)
    public ResponseEntity<LoginResponse> authenticate() {
    	return ResponseEntity.ok(userService.getUserToken());
    }
    
    @PreAuthorize("hasAnyAuthority('admin','editor','reader')")
    @GetMapping(PathConstant.ME)
    public ResponseEntity<?> authenticatedUser() {
        return ResponseEntity.ok(userService.getMe());
    }

}
