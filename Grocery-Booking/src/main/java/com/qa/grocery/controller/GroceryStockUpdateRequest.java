package com.qa.grocery.controller;

import lombok.Data;

@Data
public class GroceryStockUpdateRequest {
	
	private Integer id;
	private Integer newStock;
}
