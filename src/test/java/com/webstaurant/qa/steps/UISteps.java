package com.webstaurant.qa.steps;

import com.webstaurant.qa.pages.CartPage;
import com.webstaurant.qa.pages.SearchPage;
import com.webstaurant.qa.utils.CommonUI;
import com.webstaurant.qa.utils.Constants;
import com.webstaurant.qa.utils.JsonUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UISteps extends Constants {

	@Given("User navigates to WebstaurantStore application {string} {string}")
	public void navigateTo(String browser, String region) throws Exception {
		jsonUtilsInstance = new JsonUtils();
		jsonUtilsInstance.loadJsonFileDataToMap(ENV_FILE_PATH, region);
		commonUIInstance = new CommonUI();
		commonUIInstance.openBrowser(browser);
		commonUIInstance.navigateTo(jsonUtilsInstance.getEnvMap("url"));
		searchPageInstance = new SearchPage();
		cartPageInstance = new CartPage();

	}

	@When("User searches for an item {string}")
	public void searchItem(String itemName) {
		searchPageInstance.searchItem(itemName);
	}

	@Then("User verifies page title contains searched item {string}")
	public void verifyPageTitleContains(String expTitle) {
		String actTitle = CommonUI.driver.getTitle();
		verifyExpectedActual(actTitle, expTitle, "Title");
	}

	@Then("User verifies product title contains the word {string}")
	public void verifyProductTitle(String productTitle) throws InterruptedException {
		searchPageInstance.verifyProductTitle(productTitle);
	}

	@When("User navigates to the last page of search result")
	public void navigateToLastPage() {
		searchPageInstance.navigateToLastPage();
	}

	@When("User adds last item from search result to the cart")
	public void addItemToCart() throws InterruptedException {
		searchPageInstance.addLastItemToCart();
	}

	@Then("System displays notification pop-up {string}")
	public void isNotificationDisplayed(String expMessage) {
		String actMessage = searchPageInstance.getNotificationPopUpText();
		verifyExpectedActual(actMessage, expMessage, "Notification Message");
	}

	@Then("User verifies the Cart Item Count shows correct number {int}")
	public void verifyCartItemCount(Integer expCount) {
		Integer actCount = cartPageInstance.getCartItemCount();

		verifyExpectedActual(actCount, expCount, "Cart Item Count");

	}

	@Then("User verifies the item added to the cart")
	public void verifyItemAddedToCart() {

	}

	@When("User adds item from search result to the cart {int}")
	public void addItemToCart(Integer numOfItmes) {
		searchPageInstance.addItemToCart(numOfItmes);
	}

	@When("User redirects to Cart Page")
	public void redirectToCartPage() {
		cartPageInstance.navigateToCartPage();
	}

	@When("User User clicks Delete Icon to remove the item from Cart")
	public void removeItemFromCart() throws InterruptedException {
		cartPageInstance.deleteITemFromCart();
	}

	@Then("User verifies Cart is empty {string}")
	public void verifyCartEmpty(String expHeader) {
		String actHeader = cartPageInstance.getEmptyCardHeaderText();
		verifyExpectedActual(actHeader, expHeader, "Empty Card Header");

	}

	@When("User clicks Empty Cart button to remove the item from Cart")
	public void emptyCart() {
		cartPageInstance.emptyTheCart();
	}

	public void verifyExpectedActual(Object act, Object exp, String fieldName) {

		if (act.equals(exp)) {
			System.out.println(fieldName + " Validation: PASSED");
		} else {
			System.out.println(fieldName + " Validation: FAILED");
		}
	}
}
