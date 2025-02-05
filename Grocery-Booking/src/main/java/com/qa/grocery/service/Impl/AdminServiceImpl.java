package com.qa.grocery.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.grocery.constants.ErrorConstants;
import com.qa.grocery.entities.GroceryItems;
import com.qa.grocery.entities.GroceryStockInfos;
import com.qa.grocery.entities.User;
import com.qa.grocery.exceptionhandeling.APIException;
import com.qa.grocery.repositories.GroceryItemsRepository;
import com.qa.grocery.repositories.UserRepository;
import com.qa.grocery.request.GroceryRequest;
import com.qa.grocery.response.GroceryResponse;
import com.qa.grocery.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	GroceryItemsRepository groceryRepository;

	@Override
	public GroceryResponse createGrocery(GroceryRequest groceryRequest) {
		// TODO Auto-generated method stub
		Optional<User> checkAdmin = userRepository.checkIfAdmin(groceryRequest.getUserId());
		if(checkAdmin.isEmpty()) {
			throw new APIException(ErrorConstants.USER_NOT_ADMIN);
		}
		
		GroceryItems groceryItem = buildGroceries(groceryRequest);
		
		GroceryItems createdGrocery = groceryRepository.save(groceryItem);
		
		return buildCreateGroceryResponse(createdGrocery);
	}

	private GroceryResponse buildCreateGroceryResponse(GroceryItems groceryItems) {
		// TODO Auto-generated method stub
		return GroceryResponse.builder()
				.id(groceryItems.getId())
				.itemName(groceryItems.getName())
				.price(groceryItems.getPrice()).build();
	}

	private GroceryItems buildGroceries(GroceryRequest groceryRequest) {
		// TODO Auto-generated method stub
		return GroceryItems.builder()
				.name(groceryRequest.getItemName())
				.price(groceryRequest.getPrice())
				.mfgBy(groceryRequest.getMadeBy())
				.groceryInfos(builGroceryInfos(groceryRequest))
				.expairyDate(groceryRequest.getExpaireDate())
				.build();
	}

	private GroceryStockInfos builGroceryInfos(GroceryRequest groceryRequest) {
		// TODO Auto-generated method stub
		return GroceryStockInfos.builder()
				.inStock(true)
				.stockInCount(groceryRequest.getTotalStock()).build();
	}

}
