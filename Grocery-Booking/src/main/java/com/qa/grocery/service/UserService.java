package com.qa.grocery.service;

import com.qa.grocery.request.CreateUserRequest;
import com.qa.grocery.response.UserUpdateResponse;

public interface UserService {
	
	String createUser(CreateUserRequest userRequest);
	
	UserUpdateResponse updateUser(Integer id);
}
