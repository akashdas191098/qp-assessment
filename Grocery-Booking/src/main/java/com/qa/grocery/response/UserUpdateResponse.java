package com.qa.grocery.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateResponse {
	
	private Integer id;
	private boolean isAdmin;

}
