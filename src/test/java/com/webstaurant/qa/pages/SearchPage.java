package com.webstaurant.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webstaurant.qa.utils.CommonUI;

public class SearchPage {
	private List<String> productTitleList = new ArrayList<>();

	@FindBy(id = "searchval")
	WebElement searchField;

	@FindBy(xpath = "//button[@value='Search']")
	WebElement searchButton;

	@FindBy(xpath = "//input[@value='Add to Cart']")
	List<WebElement> addToCart;

	@FindBy(xpath = "//h2[@class='notification__heading']")
	WebElement notificationPopUpText;

	@FindBy(id = "//*[@id='details']/a[not(contains(@aria-label,'Plus'))]")
	List<WebElement> productList;

	@FindBy(xpath = "//*[@id='paging']//a")
	List<WebElement> pagination;

	public SearchPage() {
		PageFactory.initElements(CommonUI.driver, this);
	}

	public void searchItem(String itemName) {
		searchField.sendKeys(itemName);
		searchButton.click();
	}

	public String getNotificationPopUpText() {
		return notificationPopUpText.getText();
	}

	public int getTotalPageNumber() {
		int totalPages = Integer.parseInt(pagination.get(pagination.size() - 2).getText());
		return totalPages;
	}

	public void navigateToLastPage() {
		int lastPage = getTotalPageNumber();
		CommonUI.driver.findElement(By.xpath("//a[text()='" + lastPage + "']")).click();

	}

	public void addLastItemToCart() throws InterruptedException {
		int lastProductIndex = addToCart.size() - 1;
		addToCart.get(lastProductIndex).click();
	}

	public void addItemToCart(int numOfItemAddToCart) {
		int itemCount = 0;
		while (itemCount < addToCart.size()) {

			if (itemCount == numOfItemAddToCart) {
				break;
			}
			addToCart.get(itemCount).click();
			itemCount++;
		}
	}

	public void verifyProductTitle(String containTitle) throws InterruptedException {
		int totalPages = getTotalPageNumber();
		System.out.println("Total Pages: " + totalPages);
		int pageNumber = 1;
		for (int i = 1; i <= totalPages; i++) {
			productList = CommonUI.driver
					.findElements(By.xpath("//*[@id='details']/a[not(contains(@aria-label,'Plus'))]"));

			for (WebElement product : productList) {
				String productTitle = product.getText();
				productTitleList.add(productTitle);
			}
			System.out.println("PageNumber: " + pageNumber + " Total items:" + productTitleList.size());
			pageNumber++;
			if ((i + 1) > totalPages) {
				break;
			}
			CommonUI.driver.findElement(By.xpath("//a[text()='" + (i + 1) + "']")).click();
		}

		int containedTitleCount = 0;
		int notContainedTitleCount = 0;
		System.out.println("\n\n**************************************************\n\n");
		for (String actProductName : productTitleList) {
			if (actProductName.contains(containTitle)) {
				containedTitleCount++;
			} else {
				notContainedTitleCount++;
				System.out.println("NOT CONTAINED: " + actProductName);
			}
		}

		System.out.println("\n\n**************************************************\n\n");

		System.out.println("Total Products: " + productTitleList.size());
		System.out.println("Total Product Contains word: " + containTitle + " in Title: " + containedTitleCount);
		System.err.println(
				"Total Product DOES NOT Contains word: " + containTitle + " in Title: " + notContainedTitleCount);

		System.out.println("\n\n**************************************************");

	}

}
