package com.qa.grocery.request;

import lombok.Data;

@Data
public class GroceryStockUpdateRequest {
	
	private Integer id;
	private Integer newStock;
}
