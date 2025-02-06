package com.qa.grocery.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.grocery.constants.ApplicationConstants;
import com.qa.grocery.constants.ErrorConstants;
import com.qa.grocery.entities.User;
import com.qa.grocery.exceptionhandeling.APIException;
import com.qa.grocery.repositories.GroceryItemsRepository;
import com.qa.grocery.repositories.UserRepository;
import com.qa.grocery.request.CreateUserRequest;
import com.qa.grocery.response.UserUpdateResponse;
import com.qa.grocery.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	UserRepository userRepositories;
	
	@Autowired
	GroceryItemsRepository groceryRepo;
	
	@Override
	public String createUser(CreateUserRequest userRequest) {
		// TODO Auto-generated method stub
		User user = buildUser(userRequest);
		User createdUser = userRepositories.save(user);
		return ApplicationConstants.USER_CREATION+createdUser.getId();
	}

	private User buildUser(CreateUserRequest userRequest) {
		// TODO Auto-generated method stub
		return User.builder().firstName(userRequest.getFirstName())
				.lastName(userRequest.getLastName())
				.email(userRequest.getEmail())
				.password(userRequest.getPassword())
				.isAdmin(false).build();
	}

	@Override
	public UserUpdateResponse updateUser(Integer id) {
		// TODO Auto-generated method stub
		Optional<User> user = userRepositories.findById(id);
		if(user.isEmpty()) {
			throw new APIException(ErrorConstants.USER_NOT_FOUND+id);
		}
		User updatedUser = user.get();
		updatedUser.setAdmin(true);
		userRepositories.save(updatedUser);
		return builUserResponse(updatedUser);
	}

	private UserUpdateResponse builUserResponse(User updatedUser) {
		// TODO Auto-generated method stub
		return UserUpdateResponse.builder()
				.id(updatedUser.getId())
				.isAdmin(updatedUser.isAdmin()).build();
	}

}
