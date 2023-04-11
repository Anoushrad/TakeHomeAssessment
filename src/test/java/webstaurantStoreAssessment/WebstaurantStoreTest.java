package webstaurantStoreAssessment;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebstaurantStoreTest {

	public static void main(String[] args) throws InterruptedException {
		// Setting system properties of ChromeDriver

		WebDriverManager.chromedriver().setup();

		// Creating an object of ChromeDriver
		WebDriver driver = new ChromeDriver();
		// Maximizing the browser window
		driver.manage().window().maximize();
		// Deleting all the browser cookies
		driver.manage().deleteAllCookies();
		// Specifying page load timeout in seconds
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		// Specifying implicit wait timeout in seconds
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// Navigating to the WebstaurantStore website
		driver.get("https://www.webstaurantstore.com/");
		// Searching for 'stainless work table'
		WebElement searchBox = driver.findElement(By.id("searchval"));
		searchBox.sendKeys("stainless work table");
		WebElement searchButton = driver.findElement(By.xpath("//input[@value='Search']"));
		searchButton.click();
		// Checking the search result ensuring every product has the word 'Table' in its
		// title
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='description']/h2/a"));
		for (WebElement product : products) {
			String title = product.getText();
			if (!title.contains("Table")) {
				System.out.println("Product title does not contain 'Table': " + title);
			}
		}

		// Adding the last of found item on last page to Cart
		WebElement lastPageButton = driver.findElement(By.xpath("//a[@title='Last Page']"));
		lastPageButton.click();
		List<WebElement> lastPageProducts = driver.findElements(By.xpath("//div[@class='description']/h2/a"));
		WebElement lastProduct = lastPageProducts.get(lastPageProducts.size() - 1);
		lastProduct.click();
		WebElement addToCartButton = driver.findElement(By.xpath("//input[@value='Add to Cart']"));
		addToCartButton.click();
		// Emptying Cart
		WebElement cartLink = driver.findElement(By.xpath("//span[@class='cart-label']"));
		cartLink.click();
		WebElement emptyCartButton = driver.findElement(By.xpath("//input[@value='Empty Cart']"));
		emptyCartButton.click();
		driver.switchTo().alert().accept();
		// Closing the browser window
		driver.quit();
	}
}
