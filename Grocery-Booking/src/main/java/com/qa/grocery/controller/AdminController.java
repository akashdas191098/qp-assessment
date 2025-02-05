package com.qa.grocery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.grocery.request.GroceryRequest;
import com.qa.grocery.response.GroceryResponse;
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

}
