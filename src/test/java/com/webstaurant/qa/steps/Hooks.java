package com.webstaurant.qa.steps;

import java.sql.SQLException;

import com.webstaurant.qa.utils.CommonUI;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks  {

	public static Scenario scenario;

	
	@Before
	public void setUp(Scenario scenario) {
		Hooks.scenario = scenario;
		 
	}

	@After
	public void tearDown() throws SQLException {

		if(CommonUI.driver != null) {
			CommonUI.driver.quit();
		}
	}
}
