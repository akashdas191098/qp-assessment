package com.qa.grocery.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroceryResponse {
	
	private Integer id;
	private String itemName;
	private double price;
}
