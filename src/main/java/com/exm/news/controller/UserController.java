package com.exm.news.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exm.news.constant.PathConstant;
import com.exm.news.dto.user.GeneralUserDto;
import com.exm.news.dto.user.UpdateAuthorityDto;
import com.exm.news.response.BasicResponseDto;
import com.exm.news.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

@RequestMapping(PathConstant.USER)
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping(PathConstant.CAT)
	public ResponseEntity<String> cat(){
		return new ResponseEntity<String>("Cat User",HttpStatus.OK);
	}
	
	@GetMapping(PathConstant.LIST)
	public ResponseEntity<List<GeneralUserDto>> userList(){
		return new ResponseEntity<List<GeneralUserDto>>(userService.getGeneralUserList(),HttpStatus.OK);
	}
	
	@GetMapping(PathConstant.GET_BY_ID)
	public ResponseEntity<GeneralUserDto> getUserById(@PathVariable Long id){
		return new ResponseEntity<GeneralUserDto>(userService.getGeneralUserById(id),HttpStatus.OK);
	}
	
	@PostMapping(PathConstant.UPDATE)
	public ResponseEntity<BasicResponseDto> updateUser(@RequestBody GeneralUserDto user){
		return new ResponseEntity<BasicResponseDto>(userService.updateUser(user),HttpStatus.OK);
	}
	
	@PostMapping(PathConstant.UPDATE_ROLE)
	public ResponseEntity<BasicResponseDto> updateUserRole(@RequestBody UpdateAuthorityDto userAuthority){
		return new ResponseEntity<BasicResponseDto>(userService.updateUserAuthority(userAuthority),HttpStatus.OK);
	}
	
	@PostMapping(PathConstant.DELETE_ALL)
	public ResponseEntity<BasicResponseDto> deleteAll(){
		return new ResponseEntity<BasicResponseDto>(userService.deleteAllUsers(),HttpStatus.OK);
	}
	
	@PostMapping(PathConstant.DELETE_BY_ID)
	public ResponseEntity<BasicResponseDto> deleteById(@PathVariable Long id){
		return new ResponseEntity<BasicResponseDto>(userService.deleteUser(id),HttpStatus.OK);
	}
	
	@PostMapping(PathConstant.DELETE_ROLE)
	public ResponseEntity<BasicResponseDto> deleteUserRole(@RequestBody UpdateAuthorityDto userAuthority){
		return new ResponseEntity<BasicResponseDto>(userService.removeUserAuthority(userAuthority),HttpStatus.OK);
	}

}
