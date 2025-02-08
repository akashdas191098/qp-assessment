package com.qa.grocery.constants;

public class QueryConstants {
	
	public static final String CHECK_USER_IS_ADMIN = "select * from users u where u.id=:id and u.is_admin=true";
	
	public static final String STOCK_CHECK = "select * from grocery_stock_details gs where gs.id=:id and gs.in_stock=true and gs.total_item>=:count";
	
	//public static final String FETCH_GROCERY = "select * from groceries gs join grocery_stock_details gd on gs.stock_info_id=gd.id where gd.in_stock=true";
	
	public static final String FETCH_GROCERY = "select * from groceries gs where gs.stock_info_id in (select gd.id from grocery_stock_details gd where gd.in_stock=true)";
}
