package com.qa.grocery.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.grocery.constants.ApplicationConstants;
import com.qa.grocery.constants.ErrorConstants;
import com.qa.grocery.entities.GroceryItems;
import com.qa.grocery.entities.GroceryStockInfos;
import com.qa.grocery.entities.OrderDetails;
import com.qa.grocery.entities.Orders;
import com.qa.grocery.entities.User;
import com.qa.grocery.exceptionhandeling.APIException;
import com.qa.grocery.repositories.GroceryInfosRepository;
import com.qa.grocery.repositories.GroceryItemsRepository;
import com.qa.grocery.repositories.OrderDetailsRepositiories;
import com.qa.grocery.repositories.OrderRepositories;
import com.qa.grocery.repositories.UserRepository;
import com.qa.grocery.request.CreateUserRequest;
import com.qa.grocery.request.UserBuyRequest;
import com.qa.grocery.response.GroceryBuyResponse;
import com.qa.grocery.response.GroceryBuyResponseWithTotalCost;
import com.qa.grocery.response.UserUpdateResponse;
import com.qa.grocery.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	UserRepository userRepositories;
	
	@Autowired
	GroceryItemsRepository groceryRepo;
	
	@Autowired
	GroceryInfosRepository stockRepo;
	
	@Autowired
	OrderDetailsRepositiories orderDetailRepo;
	
	@Autowired
	OrderRepositories orderRepo;
	
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


	@Override
	public GroceryBuyResponseWithTotalCost buyGrocery(List<UserBuyRequest> userGroceryRequest, Integer userId) {
		// TODO Auto-generated method stub
		Optional<User> user = userRepositories.findById(userId);
		if(user.isEmpty()) {
			throw new APIException(ErrorConstants.USER_NOT_FOUND+userId);
		}
		
		User users = user.get();
		List<GroceryBuyResponse> groceryResponse = new ArrayList<GroceryBuyResponse>();
		double totalBillAmount=0;
		Orders order = new Orders();
		
		for(UserBuyRequest b : userGroceryRequest) {
			Optional<GroceryItems> grocery = groceryRepo.findById(b.getId());
			if(grocery.isEmpty()) {
				throw new APIException(ErrorConstants.GROCERY_NOT_FOUND+b.getId());
			}
			Optional<GroceryStockInfos> stockCheck = stockRepo.findById(grocery.get().getGroceryInfos().getId());
			Integer totalStock = b.getTotalCount();
			if(stockCheck.get().getStockInCount()<totalStock) {
				//throw new APIException(ErrorConstants.LIMITED_STOCK+grocery.get().getId());
				totalStock=stockCheck.get().getStockInCount();
			}
			GroceryItems groceries = grocery.get();
			totalBillAmount +=(groceries.getPrice()*totalStock);
			GroceryStockInfos stockCount = stockCheck.get();
			Integer updatedStock = stockCount.getStockInCount()-totalStock;
			if(updatedStock==0) {
				stockCount.setInStock(false);
			}
			stockCount.setStockInCount(updatedStock);
			groceries.setGroceryInfos(stockCount);
			groceryRepo.save(groceries);
			//order = new Orders();
			order.setTotalAmount(totalBillAmount);
			order.setUsers(user.get());
			Orders orders = orderRepo.save(order);
			OrderDetails orderDetails = new OrderDetails();
			orderDetails.setBroughtCount(totalStock);
			orderDetails.setTotalCost(groceries.getPrice()*totalStock);
			orderDetails.setGroceryItems(groceries);
			orderDetails.setOrder(orders);
			OrderDetails orderDetail = orderDetailRepo.save(orderDetails);
			groceryResponse.add(buildGroceryResponse(orderDetail));
		}
		
		return buildTotalCostGroceryResponse(users.getId(),users.getFirstName()+users.getLastName(),order.getTotalAmount(),groceryResponse,order);
	}
	
	private GroceryBuyResponseWithTotalCost buildTotalCostGroceryResponse(Integer id, String userName,
			double totalBillAmount, List<GroceryBuyResponse> groceryResponse,Orders order ) {
		// TODO Auto-generated method stub
		return GroceryBuyResponseWithTotalCost.builder().userId(id)
				.userName(userName)
				.orderId(order.getId())
				.totalAmount(totalBillAmount)
				.groceries(groceryResponse).build();
	}

	private GroceryBuyResponse buildGroceryResponse(OrderDetails orderdetails) {
		return GroceryBuyResponse.builder().id(orderdetails.getGroceryItems().getId())
				.itemName(orderdetails.getGroceryItems().getName())
				.price(orderdetails.getTotalCost())
				.quantity(orderdetails.getBroughtCount())
				.build();
	}

	

}
