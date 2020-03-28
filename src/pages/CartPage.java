package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public CartPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}
	
	public void deleteAllCookies() {
		driver.navigate().to(locators.getProperty("cartPageUrl"));
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
	}

	public WebElement getEmptyCartMsg() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("emptyCartMsg")));
	}

	public boolean isEmpty() {
		return this.getEmptyCartMsg().getText().contains("Your cart is empty.");
	}
}
