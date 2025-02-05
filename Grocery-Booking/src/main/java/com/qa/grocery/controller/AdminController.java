package com.qa.grocery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.grocery.constants.ApplicationConstants;
import com.qa.grocery.request.GroceryRemoveRequest;
import com.qa.grocery.request.GroceryRequest;
import com.qa.grocery.request.GroceryStockUpdateRequest;
import com.qa.grocery.request.GroceryUpdateRequest;
import com.qa.grocery.response.GroceryResponse;
import com.qa.grocery.response.GroceryResponseWithHeader;
import com.qa.grocery.response.GroceryShowResponse;
import com.qa.grocery.service.AdminService;

@RestController
@RequestMapping("/api/admin")
/*
 * Admin Responsibilities:
 */
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	/*
	 * - Add new grocery items to the system
	 */
	@PostMapping("/create-grocery")
	public GroceryResponse createGrocery(@RequestBody GroceryRequest groceryRequest) {
		return adminService.createGrocery(groceryRequest);
	}
	
	/*
	 * - View existing grocery items
	 */
	@GetMapping("/viewAllGroceries/{id}")
	public GroceryResponseWithHeader viewAllGroceries(@RequestHeader(value = "pageNumber", defaultValue = ApplicationConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestHeader(value = "pageSize", defaultValue = ApplicationConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @PathVariable("id") Integer id) {
		return adminService.viewAllGroceries(pageNumber, pageSize, id);
	}
	/*
	 * - Remove grocery items from the system
	 */
	@DeleteMapping("/delete-grocery")
	public String deleteGroceries(@RequestBody GroceryRemoveRequest groceryRemoveRequest) {
		return adminService.removeGroceries(groceryRemoveRequest);
	}
	
	/*
	 * - update grocery
	 */
	
	@PutMapping("/update-grocery")
	public GroceryResponse updateGrocery(@RequestBody GroceryUpdateRequest updateRequest) {
		return adminService.updateGrocery(updateRequest);
	}
	
	/*
	 * - update grocery stock
	 */
	@PutMapping("/update-stock")
	public GroceryShowResponse updateStock(@RequestBody GroceryStockUpdateRequest groceryStockUpdateRequest) {
		return adminService.updateGroceryStock(groceryStockUpdateRequest);
	}
	

}
