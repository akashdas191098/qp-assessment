package com.qa.grocery.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroceryBuyResponseWithTotalCost {
	
	private Integer userId;
	private String userName;
	private Integer orderId;
	private double totalAmount;
	private List<GroceryBuyResponse> groceries;

}
