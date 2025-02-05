package com.qa.grocery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.grocery.request.CreateUserRequest;
import com.qa.grocery.response.UserUpdateResponse;
import com.qa.grocery.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/create")
	public String createUser(@RequestBody CreateUserRequest userRequest) {
		return userService.createUser(userRequest);
	}
	
	@PutMapping("/update/{id}")
	public UserUpdateResponse updateUser(@PathVariable("id") Integer id) {
		return userService.updateUser(id);
	}
}
