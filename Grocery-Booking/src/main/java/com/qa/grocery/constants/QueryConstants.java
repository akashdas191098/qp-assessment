package com.qa.grocery.constants;

public class QueryConstants {
	
	public static final String CHECK_USER_IS_ADMIN = "select * from users u where u.id=:id and u.is_admin=true";
	
	public static final String STOCK_CHECK = "select * from grocery_stock_details gs where gs.id=:id and gs.in_stock=true and gs.total_item>=:count";
}
