package com.webstaurant.qa.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(features = "src/test/resources/features/", 
					glue = "com.webstaurant.qa.steps", 
					tags="@prod",
					plugin = {
							"summary", "pretty", 
							"html:target/cucumber-reports.html", "json:target/cucumber-reports",
							"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" })
public class Runner {

}
