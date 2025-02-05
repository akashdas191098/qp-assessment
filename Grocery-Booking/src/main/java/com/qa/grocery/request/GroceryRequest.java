package com.qa.grocery.request;

import lombok.Data;

@Data
public class GroceryRequest {
	
	private Integer userId;
	private String itemName;
	private String madeBy;
	private double price;
	private String expaireDate;
	private Integer totalStock;
}
