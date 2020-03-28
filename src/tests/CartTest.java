package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.CartPage;
import pages.StoreItemPage;

import org.testng.annotations.BeforeClass;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CartTest {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	@BeforeClass
	public void setup() throws FileNotFoundException, IOException {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.locators =  new Properties();
		locators.load(new FileInputStream("config/project.properties"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test(priority = 1)
	public void addToCartTest() {
		StoreItemPage sip = new StoreItemPage(driver, locators, waiter);
		CartPage cp = new CartPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();
		
		sip.addAllToCart();
		sa.assertTrue(sip.isAdded());		
	}
	
	@Test(priority = 2)
	public void totalCostTest() {
		CartPage cp = new CartPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();
		
		sa.assertTrue(cp.isEqual());
	}
	
	@Test(priority = 3)
	public void clearCookiesTest() {
		StoreItemPage sip = new StoreItemPage(driver, locators, waiter);
		CartPage cp = new CartPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();
		
		sip.addAllToCart();
		cp.deleteAllCookies();
		sa.assertTrue(cp.isEmpty());		
	}
	
	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}
