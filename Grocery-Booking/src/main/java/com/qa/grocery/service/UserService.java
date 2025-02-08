package com.qa.grocery.service;

import java.util.List;

import com.qa.grocery.request.CreateUserRequest;
import com.qa.grocery.request.UserBuyRequest;
import com.qa.grocery.response.GroceryBuyResponseWithTotalCost;
import com.qa.grocery.response.GroceryResponseWithHeader;
import com.qa.grocery.response.UserUpdateResponse;

public interface UserService {
	
	String createUser(CreateUserRequest userRequest);
	
	UserUpdateResponse updateUser(Integer id);

	GroceryBuyResponseWithTotalCost buyGrocery(List<UserBuyRequest> userGroceryRequest, Integer userId);

	GroceryResponseWithHeader viewAvailableGroceries(Integer pageNumber, Integer pageSize, Integer userId);
}
