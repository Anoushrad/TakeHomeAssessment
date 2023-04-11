package com.webstaurant.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webstaurant.qa.utils.CommonUI;

public class CartPage {

	@FindBy(xpath = "//*[text()='Cart']")
	WebElement cartMenuLink;

	@FindBy(id = "cartItemCountSpan")
	WebElement cartItemCount;

	@FindBy(xpath = "//*[contains(text(),'Empty Cart')]")
	WebElement emptyCartButton;

	@FindBy(id = "//*[@class='itemDelete']//button")
	List<WebElement> deleteCartItemIcon;

	@FindBy(xpath = "//*[contains(text(),'Your cart')]")
	WebElement emptyCartHeaderText;

	@FindBy(xpath = "//footer//button[contains(text(),'Empty')]")
	WebElement emptyCartConfirmButon;

	public CartPage() {
		PageFactory.initElements(CommonUI.driver, this);
	}

	public int getCartItemCount() {
		return Integer.parseInt(cartItemCount.getText());
	}

	public void navigateToCartPage() {
		CommonUI.driver.navigate().refresh();
		cartMenuLink.click();
	}

	public void deleteITemFromCart() throws InterruptedException {

		if (deleteCartItemIcon.size() == 0) {
			this.deleteCartItemIcon = CommonUI.driver.findElements(By.xpath("//*[@class='itemDelete']//button"));
		}
		System.out.println(deleteCartItemIcon.size());
		for (WebElement el : deleteCartItemIcon) {
			el.click();
			Thread.sleep(500);
		}
	}

	public String getEmptyCardHeaderText() {
		return emptyCartHeaderText.getText();
	}

	public void emptyTheCart() {
		emptyCartButton.click();
		emptyCartConfirmButon.click();
	}
}
