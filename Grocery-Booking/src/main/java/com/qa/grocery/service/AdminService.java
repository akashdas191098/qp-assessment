package com.qa.grocery.service;

import com.qa.grocery.request.GroceryRemoveRequest;
import com.qa.grocery.request.GroceryRequest;
import com.qa.grocery.response.GroceryResponse;
import com.qa.grocery.response.GroceryResponseWithHeader;

public interface AdminService {
	
	GroceryResponse createGrocery(GroceryRequest groceryRequest);
	
	GroceryResponseWithHeader viewAllGroceries(Integer pageNumber,Integer pageSize,Integer id);
	
	String removeGroceries(GroceryRemoveRequest groceryRemoveRequest);
}
