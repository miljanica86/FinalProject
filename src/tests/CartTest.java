package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import pages.CartPage;
import pages.StoreItemPage;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CartTest {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;


	@BeforeClass
	@Parameters("browser")
	public void setup(String browser) throws Exception {
		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "driver-lib\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", "driver-lib\\msedgedriver.exe");
			driver = new EdgeDriver();
		} else {
			throw new Exception("Browser is not correct");
		}
		this.locators =  new Properties();
		locators.load(new FileInputStream("config/project.properties"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
