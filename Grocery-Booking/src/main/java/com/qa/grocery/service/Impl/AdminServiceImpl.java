package com.qa.grocery.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.qa.grocery.constants.ApplicationConstants;
import com.qa.grocery.constants.ErrorConstants;
import com.qa.grocery.entities.GroceryItems;
import com.qa.grocery.entities.GroceryStockInfos;
import com.qa.grocery.entities.User;
import com.qa.grocery.exceptionhandeling.APIException;
import com.qa.grocery.repositories.GroceryItemsRepository;
import com.qa.grocery.repositories.UserRepository;
import com.qa.grocery.request.GroceryRemoveRequest;
import com.qa.grocery.request.GroceryRequest;
import com.qa.grocery.response.GroceryResponse;
import com.qa.grocery.response.GroceryResponseWithHeader;
import com.qa.grocery.response.GroceryShowResponse;
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

	@Override
	public GroceryResponseWithHeader viewAllGroceries(Integer pageNumber, Integer pageSize, Integer id) {
		// TODO Auto-generated method stub
		Optional<User> checkAdmin = userRepository.checkIfAdmin(id);
		if(checkAdmin.isEmpty()) {
			throw new APIException(ErrorConstants.USER_NOT_ADMIN_VIEW);
		}
		Pageable p = PageRequest.of(pageNumber, pageSize);
		Page<GroceryItems> pageGroceries = groceryRepository.findAll(p);
		List<GroceryItems> listGroceries = pageGroceries.getContent();
		List<GroceryShowResponse> items = listGroceries.stream().map(g->buildShowGroceriesResposne(g)).collect(Collectors.toList());
		return buildGroceryResponseWithHeader(items,pageGroceries);
	}

	private GroceryShowResponse buildShowGroceriesResposne(GroceryItems groceryItems) {
		// TODO Auto-generated method stub
		return GroceryShowResponse.builder()
				.id(groceryItems.getId())
				.itemName(groceryItems.getName())
				.totalStock(groceryItems.getGroceryInfos().getStockInCount()).build();
	}
	
	private GroceryResponseWithHeader buildGroceryResponseWithHeader(List<GroceryShowResponse> listGroceries, Page<GroceryItems> pageGroceries) {
		return GroceryResponseWithHeader.builder()
				.currentPage(pageGroceries.getNumber())
				.totalRecordPerPage(pageGroceries.getNumberOfElements())
				.totalRecords(pageGroceries.getTotalElements())
				.totalPages(pageGroceries.getTotalPages())
				.groceries(listGroceries).build();
	}

	@Override
	public String removeGroceries(GroceryRemoveRequest groceryRemoveRequest) {
		// TODO Auto-generated method stub
		Optional<User> checkAdmin = userRepository.checkIfAdmin(groceryRemoveRequest.getUserId());
		if(checkAdmin.isEmpty()) {
			throw new APIException(ErrorConstants.USER_NOT_ADMIN_DELETE);
		}
		Optional<GroceryItems> grocery = groceryRepository.findById(groceryRemoveRequest.getItemId());
		if(grocery.isEmpty()) {
			throw new APIException(ErrorConstants.GROCERY_NOT_FOUND + groceryRemoveRequest.getItemId());
		}
		groceryRepository.deleteById(grocery.get().getId());;
		return ApplicationConstants.GROCERY_DELETION+groceryRemoveRequest.getItemId();
	}

}
