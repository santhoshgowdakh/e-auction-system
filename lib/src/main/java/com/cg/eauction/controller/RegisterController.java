package com.cg.eauction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.eauction.dto.UserDtoAdd;
import com.cg.eauction.service.UserServiceImpl;

@CrossOrigin(origins= "*" ,maxAge = 3600)
@RestController
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	UserServiceImpl userService;
	
	@PostMapping("/add")
	public ResponseEntity<String> addUser(@RequestBody UserDtoAdd userDtoAdd){
		int userId=userService.addUsers(userDtoAdd);
		return new ResponseEntity<String>(" User Registered successfully user Id :"+userId, HttpStatus.OK);
	}
}
