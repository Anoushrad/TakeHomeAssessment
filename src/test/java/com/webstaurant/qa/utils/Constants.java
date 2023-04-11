package com.webstaurant.qa.utils;

import com.webstaurant.qa.pages.CartPage;
import com.webstaurant.qa.pages.SearchPage;

public class Constants {
	public static final int DEFAULT_WAIT_TIME = 5;
 	public static final String ENV_FILE_PATH = "./src/test/resources/config/Environment.json";

	public JsonUtils jsonUtilsInstance;
	public SearchPage searchPageInstance;
	public CartPage cartPageInstance;
	public CommonUI commonUIInstance;
	
	
	public Constants() {
		jsonUtilsInstance = new JsonUtils();
		commonUIInstance = new CommonUI();
		searchPageInstance = new SearchPage();
		cartPageInstance = new CartPage();
		
	}
}
