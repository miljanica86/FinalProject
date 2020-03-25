package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.PetStoreMenuPage;

public class PetStoreMenuTest {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	@BeforeClass
	public void setup() throws FileNotFoundException, IOException {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.locators = new Properties();
		locators.load(new FileInputStream("config/project.properties"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void verifyUrlTest() {
		driver.navigate().to(this.locators.getProperty("storeMenuUrl"));

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
		List<String> species = new ArrayList<String>
				(Arrays.asList("fish", "dogs", "reptiles", "cats", "birds"));

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

	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}
