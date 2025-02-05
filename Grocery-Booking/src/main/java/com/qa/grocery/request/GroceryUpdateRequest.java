package com.qa.grocery.request;

import lombok.Data;

@Data
public class GroceryUpdateRequest {
	
	private Integer id;
	private String itemName;
	private double price;
}
