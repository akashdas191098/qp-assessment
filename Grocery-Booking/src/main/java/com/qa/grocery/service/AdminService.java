package com.qa.grocery.service;

import com.qa.grocery.request.GroceryRemoveRequest;
import com.qa.grocery.request.GroceryRequest;
import com.qa.grocery.request.GroceryStockUpdateRequest;
import com.qa.grocery.request.GroceryUpdateRequest;
import com.qa.grocery.response.GroceryResponse;
import com.qa.grocery.response.GroceryResponseWithHeader;
import com.qa.grocery.response.GroceryShowResponse;

public interface AdminService {
	
	GroceryResponse createGrocery(GroceryRequest groceryRequest);
	
	GroceryResponseWithHeader viewAllGroceries(Integer pageNumber,Integer pageSize,Integer id);
	
	String removeGroceries(GroceryRemoveRequest groceryRemoveRequest);

	GroceryResponse updateGrocery(GroceryUpdateRequest updateRequest);

	GroceryShowResponse updateGroceryStock(GroceryStockUpdateRequest groceryStockUpdateRequest);
}
