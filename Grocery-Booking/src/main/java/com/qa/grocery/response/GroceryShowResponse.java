package com.qa.grocery.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroceryShowResponse  {
	
	private Integer id;
	private String  itemName;
	private double price;
	private Integer totalStock;
}
