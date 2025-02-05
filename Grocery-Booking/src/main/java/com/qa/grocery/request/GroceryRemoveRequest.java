package com.qa.grocery.request;

import lombok.Data;

@Data
public class GroceryRemoveRequest {
	
	private Integer userId;
	private Integer itemId;

}
