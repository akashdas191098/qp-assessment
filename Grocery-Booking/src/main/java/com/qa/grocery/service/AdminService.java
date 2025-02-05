package com.qa.grocery.service;

import com.qa.grocery.request.GroceryRequest;
import com.qa.grocery.response.GroceryResponse;

public interface AdminService {
	
	GroceryResponse createGrocery(GroceryRequest groceryRequest);
}
