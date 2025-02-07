package com.qa.grocery.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GroceryBuyResponse {
	
	private Integer id;
	private String itemName;
	private double price;
	private Integer quantity;
}
