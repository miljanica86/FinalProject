package tests;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.PetStoreMenuPage;

public class PetStoreMenuTest {
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
			System.setProperty("webdriver.edge.driver", "driver-lib\\\\msedgedriver.exe");
			driver = new EdgeDriver();
		} else {
			throw new Exception("Browser is not correct");
		}
		this.locators = new Properties();
		locators.load(new FileInputStream("config/project.properties"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void verifyUrlTest() {
		PetStoreMenuPage psmp = new PetStoreMenuPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();

		sa.assertTrue(psmp.checkLeftNavLinks());
		sa.assertTrue(psmp.checkTopNavLinks());
		sa.assertTrue(psmp.checkImgNavLinks());
	}

	@Test
	public void linkToRightPageTest() {
		driver.navigate().to(this.locators.getProperty("storeMenuUrl"));

		PetStoreMenuPage psmp = new PetStoreMenuPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();
		List<String> species = new ArrayList<String>(Arrays.asList("fish", "dogs", "reptiles", "cats", "birds"));

		for (int i = 0; i < species.size(); i++) {
			sa.assertTrue(psmp.isLeftNavRight(species.get(i)));
		}

		for (int i = 0; i < species.size(); i++) {
			sa.assertTrue(psmp.isTopNavRight(species.get(i)));
		}

		for (int i = 0; i < species.size(); i++) {
			sa.assertTrue(psmp.isImgNavRight(species.get(i)));
		}
	}

	@Test
	public void topMenuContentTest() {
		PetStoreMenuPage psmp = new PetStoreMenuPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();

		psmp.clickCartPage();
		sa.assertTrue(psmp.isClickedCartPage());

		psmp.clickSignInPage();
		sa.assertTrue(psmp.isClickedSignInPage());

		psmp.clickHelpPage();
		sa.assertTrue(psmp.isClickedHelpPage());
	}

	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}
