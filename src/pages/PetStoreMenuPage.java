package pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PetStoreMenuPage {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public PetStoreMenuPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}
	
	public List<WebElement> getLeftNav() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("leftNav")));
	}

	public void clickLeftNav(String species) {
		List<WebElement> left = this.getLeftNav();
		for (int i = 0; i < left.size(); i++) {
			if(left.get(i).getText().contains(species)) {
				left.get(i).click();
				break;
			}
		}
	}
	
	public boolean isLeftNavRight(String species) {
		this.clickLeftNav(species);		
		return this.isItRightPage(species);		
	}
	
	public boolean checkLeftNavLinks() {
		List<WebElement> left = this.getLeftNav();
		boolean isOk = false;
		for (int i = 0; i < left.size(); i++) {
			int status = verifyURLStatus(left.get(i).getAttribute("href"));
			if(status < 400) {
				isOk = true;
			}
		} return isOk;
	}
	
	public List<WebElement> getTopNav() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("topNav")));
	}

	public void clickTopNav(String species) {
		List<WebElement> top = this.getTopNav();
		for (int i = 0; i < top.size(); i++) {
			if(top.get(i).getText().contains(species)) {
				top.get(i).click();
				break;
			}
		}
	}
	
	public boolean isTopNavRight(String species) {
		this.clickTopNav(species);		
		return this.isItRightPage(species);		
	}
	
	public boolean checkTopNavLinks() {
		List<WebElement> top = this.getTopNav();
		boolean isOk = false;
		for (int i = 0; i < top.size(); i++) {
			int status = verifyURLStatus(top.get(i).getAttribute("href"));
			if(status < 400) {
				isOk = true;
			}
		} return isOk;
	}
	
	public List<WebElement> getImgNav() {
		return this.driver.findElements(By.xpath(this.locators.getProperty("imgNav")));
	}

	public void clickImgNav(String species) {
		List<WebElement> img = this.getImgNav();
		for (int i = 0; i < img.size(); i++) {
			if(img.get(i).getText().contains(species)) {
				img.get(i).click();
				break;
			}
		}
	}
	
	public boolean isImgNavRight(String species) {
		this.clickImgNav(species);		
		return this.isItRightPage(species);		
	}
	
	public boolean checkImgNavLinks() {
		List<WebElement> img = this.getImgNav();
		boolean isOk = false;
		for (int i = 0; i < img.size(); i++) {
			int status = verifyURLStatus(img.get(i).getAttribute("href"));
			if(status < 400) {
				isOk = true;
			}
		} return isOk;
	}
		
	public boolean isItRightPage(String species) {
		boolean isRight = false;
		switch (species) {
		case "fish":
			if (this.driver.getCurrentUrl().contains("categoryId=FISH")) {
				isRight = true;
			}
			break;
		case "dogs":
			if (this.driver.getCurrentUrl().contains("categoryId=DOGS")) {
				isRight = true;
			}
		case "reptiles":
			if (this.driver.getCurrentUrl().contains("categoryId=REPTILES")) {
				isRight = true;
			}
		case "cats":
			if (this.driver.getCurrentUrl().contains("categoryId=CATS")) {
				isRight = true;
			}
		case "birds":
			if (this.driver.getCurrentUrl().contains("categoryId=BIRDS")) {
				isRight = true;
			}
			break;
		default:
			isRight = false;
			break;
		}
			return isRight;
	}  
	
	public int verifyURLStatus(String urlString) {
		int status = 404;
		try {
			URL link = new URL(urlString);
			HttpURLConnection hConn = null;
			hConn = (HttpURLConnection) link.openConnection();
			hConn.setRequestMethod("GET");
			hConn.connect();
			status = hConn.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}
}
