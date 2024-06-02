package com.example.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.school.service.UserService;

@RequestMapping("/user")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/list")
	public ResponseEntity<?> userList(){
		return new ResponseEntity<>(userService.getUserList(),HttpStatus.OK);
	}
	
	@PostMapping("/get")
	public ResponseEntity<?> userLogin(@RequestParam("id") long id){
		return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
	}
}
