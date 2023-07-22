package com.nopcommerce.pratice;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_18_Share_Data_B extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {

		driver = getBrowserDriver(browserName, "https://demo.nopcommerce.com/");
		homePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "Geni";
		lastName = "Nguyen";
		emailAddress = "afc" + generateRandomNumber() + "@acb.com";
		password = "123456";

		driver.get("https://demo.nopcommerce.com/");
		homePage = PageGeneratorManager.getUserHomePage(driver);

		log.info("Pre-condition - Step 01: Navigate to 'Register' page");
		registerPage = homePage.openRegisterPage();

		log.info("Pre-condition - Step 02: Enter to FirstName textbox with value is '" + firstName + "'");
		registerPage.inputToFirstNameTextBox(firstName);

		log.info("Pre-condition - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToLastNameTextBox(lastName);

		log.info("Pre-condition - Step 04: Enter to Email Address textbox with value is '" + emailAddress + "'");
		registerPage.inputToEmailTextBox(emailAddress);

		log.info("Pre-condition - Step 05: Enter to Password textbox with value is '" + password + "'");
		registerPage.inputToPassowrdTextBox(password);

		log.info("Pre-condition - Step 06: Enter to Confirm Password textbox with value is '" + password + "'");
		registerPage.inputToConfirmPasswordTextBox(password);

		log.info("Pre-condition - Step 08: Click to 'Register' button");
		registerPage.clickToRegisterButton();

		log.info("Pre-condition - Step 09: Verify register success message is displayed");
		verifyEquals(registerPage.getSuccessRegisterMessage(), "Your registration completed");

		log.info("Pre-condition - Step 10: Click to Continue button");
		homePage = registerPage.clickToContinueButton();

		log.info("Pre-condition - Step 11: Navigate to Login page");
		loginPage = homePage.openLoginPage();

		log.info("Pre-condition - Step 12: Enter to Email textbox with value is '" + emailAddress + "'");
		loginPage.inputToEmailTextBox(emailAddress);

		log.info("Pre-condition - Step 13: Enter to Password textbox with value is '" + password + "'");
		loginPage.inputToPasswordTextBox(password);

		log.info("Pre-condition - Step 14: Click to Login button");
		homePage = loginPage.clickToLoginButton();

		log.info("Pre-condition - Step 15: Verify the 'My Account' Link is displayed");
		verifyTrue(homePage.isMyAccountDisplayed());

	}

	@Test
	public void Search_01_() {

	}

	@Test
	public void Search_02_() {

	}

	@Test
	public void Search_03_() {

	}

	@Test
	public void Search_04_() {

	}

	@Test
	public void Search_05_() {

	}

	public int generateRandomNumber() {
		return new Random().nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private UserLoginPageObject loginPage;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private String firstName, lastName, password, emailAddress;
}
