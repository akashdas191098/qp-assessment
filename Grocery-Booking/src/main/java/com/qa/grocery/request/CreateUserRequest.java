package com.qa.grocery.request;

import lombok.Data;

@Data
public class CreateUserRequest {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private boolean isAdmin;
}
