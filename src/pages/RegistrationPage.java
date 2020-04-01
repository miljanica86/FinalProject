package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExcelUtils;

public class RegistrationPage {
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	public RegistrationPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	}

	public WebElement getUserId() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("userId")));
	}

	public void setUserId(String userId) {
		this.getUserId().clear();
		this.getUserId().sendKeys(userId);
	}

	public WebElement getNewPass() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("newPass")));
	}

	public WebElement getRepeatPass() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("repeatPass")));
	}

	public void setAllPass(String password) {
		this.getNewPass().clear();
		this.getNewPass().sendKeys(password);
		this.getRepeatPass().clear();
		this.getRepeatPass().sendKeys(password);
	}

	public WebElement getFirstName() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("firstName")));
	}

	public void setFirstName(String firstName) {
		this.getFirstName().clear();
		this.getFirstName().sendKeys(firstName);
	}

	public WebElement getLastName() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("lastName")));
	}

	public void setLastName(String lastName) {
		this.getLastName().clear();
		this.getLastName().sendKeys(lastName);
	}

	public WebElement getEmail() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("email")));
	}

	public void setEmail(String email) {
		this.getEmail().clear();
		this.getEmail().sendKeys(email);
	}

	public WebElement getPhone() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("phone")));
	}

	public void setPhone(String phone) {
		this.getPhone().clear();
		this.getPhone().sendKeys(phone);
	}

	public WebElement getAddress1() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("address1")));
	}

	public void setAddress1(String address1) {
		this.getAddress1().clear();
		this.getAddress1().sendKeys(address1);
	}

	public WebElement getAddress2() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("address2")));
	}

	public void setAddress2(String address2) {
		this.getAddress2().clear();
		this.getAddress2().sendKeys(address2);
	}

	public WebElement getCity() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("city")));
	}

	public void setCity(String city) {
		this.getCity().clear();
		this.getCity().sendKeys(city);
	}

	public WebElement getState() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("state")));
	}

	public void setState(String state) {
		this.getState().clear();
		this.getState().sendKeys(state);
	}

	public WebElement getZip() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("zip")));
	}

	public void setZip(String zip) {
		this.getZip().clear();
		this.getZip().sendKeys(zip);
	}

	public WebElement getCountry() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("country")));
	}

	public void setCountry(String country) {
		this.getCountry().clear();
		this.getCountry().sendKeys(country);
	}

	public WebElement getLanguage() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("selectLanguage")));
	}

	public Select getSelectLanguage() {
		return new Select(this.getLanguage());
	}

	public void selectLanguage(String language) {
		this.getSelectLanguage().selectByValue(language);
	}

	public WebElement getFavCategory() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("selectFavCategory")));
	}

	public Select getSelectFavCategory() {
		return new Select(this.getFavCategory());
	}

	public void selectFavCategory(String category) {
		this.getSelectFavCategory().selectByValue(category);
	}

	public WebElement getMyListCheckBox() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("myListCheckBox")));
	}

	public WebElement getMyBannerCheckBox() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("myBannerCheckBox")));
	}

	public WebElement getSaveAccountInfoBtn() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("saveAccountInfoBtn")));
	}

	// Registration method using Excel data
	public void register() {
		ExcelUtils.setExcell(this.locators.getProperty("dataSource"));
		ExcelUtils.setWorkSheet(1);

		for (int i = 1; i < ExcelUtils.getRowNumber() - 1; i++) {
			driver.navigate().to(this.locators.getProperty("registrationUrl"));
			ExcelUtils.setRandomAt(i, 0);
			this.setUserId(ExcelUtils.getDataAt(i, 0));
			this.setAllPass(ExcelUtils.getDataAt(i, 1));
			this.setFirstName(ExcelUtils.getDataAt(i, 2));
			this.setLastName(ExcelUtils.getDataAt(i, 3));
			this.setEmail(ExcelUtils.getDataAt(i, 4));
			this.setPhone(ExcelUtils.getDataAt(i, 5));
			this.setAddress1(ExcelUtils.getDataAt(i, 6));
			this.setAddress2(ExcelUtils.getDataAt(i, 7));
			this.setCity(ExcelUtils.getDataAt(i, 8));
			this.setState(ExcelUtils.getDataAt(i, 9));
			this.setZip(ExcelUtils.getDataAt(i, 10));
			this.setCountry(ExcelUtils.getDataAt(i, 11));
			ExcelUtils.setLangRandomAt(i, 12);
			this.selectLanguage(ExcelUtils.getDataAt(i, 12));
			ExcelUtils.setFavCategRandomAt(i, 13);
			this.selectFavCategory(ExcelUtils.getDataAt(i, 13));
			this.getMyListCheckBox().click();
			this.getMyBannerCheckBox().click();
			this.getSaveAccountInfoBtn().click();
		}
		ExcelUtils.closeExcell();
	}

	public WebElement getLogoImg() {
		return this.driver.findElement(By.xpath(this.locators.getProperty("logoImg")));
	}

	public boolean checkRegistration() {
		return this.getLogoImg().isDisplayed();
	}
}
