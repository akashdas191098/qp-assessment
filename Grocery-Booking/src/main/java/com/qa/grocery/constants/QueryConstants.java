package com.qa.grocery.constants;

public class QueryConstants {
	
	public static final String CHECK_USER_IS_ADMIN = "select * from users u where u.id=:id and u.is_admin=true";
}
