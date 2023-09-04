package com.nopcommerce.common;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Common_01_Register_Cookie extends BaseTest {
	@Parameters("browser")
	@BeforeTest(description = "Create a new common User for all Classes Test")
	public void Register(String browserName) {
		driver = getBrowserDriver(browserName, GlobalConstants.PORTAL_PAGE_URL);
		firstName = "Geni" + generateRandomNumber();
		lastName = "Nguyen";
		emailAddress = "afc" + generateRandomNumber() + "@acb.com";
		password = "123456";

		homePage = PageGeneratorManager.getUserHomePage(driver);

		 log.info("Register - Step 01: Navigate to 'Register' page");
		 registerPage = homePage.openRegisterPage();
		
		 log.info("Register - Step 02: Enter to FirstName textbox with value is '" + firstName + "'");
		 registerPage.inputToFirstNameTextBox(firstName);
		
		 log.info("Register - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		 registerPage.inputToLastNameTextBox(lastName);
		
		 log.info("Register - Step 04: Enter to Email Address textbox with value is '" + emailAddress + "'");
		 registerPage.inputToEmailTextBox(emailAddress);
		
		 log.info("Register - Step 05: Enter to Password textbox with value is '" + password + "'");
		 registerPage.inputToPassowrdTextBox(password);
		
		 log.info("Register - Step 06: Enter to Confirm Password textbox with value is '" + password + "'");
		 registerPage.inputToConfirmPasswordTextBox(password);
		
		 log.info("Register - Step 08: Click to 'Register' button");
		 registerPage.clickToRegisterButton();
		
		 log.info("Register - Step 09: Verify register success message is displayed");
		 verifyEquals(registerPage.getSuccessRegisterMessage(), "Your registration completed");
		
		 log.info("Register - Step 10: Click to Continue button");
		 homePage = registerPage.clickToContinueButton();
		
		 log.info("Pre-condition - Step 11: Navigate to Login page");
		 loginPage = homePage.openLoginPage();
		
		 log.info("Pre-condition - Step 12: Enter to Email textbox with value is '" + emailAddress + "'");
		 loginPage.inputToEmailTextBox(emailAddress);
		
		 log.info("Pre-condition - Step 13: Enter to Password textbox with value is '" + password + "'");
		 loginPage.inputToPasswordTextBox(password);
		
		 log.info("Pre-condition - Step 14: Click to Login button");
		 homePage = loginPage.clickToLoginButton();

		loggedCookies = homePage.getAllCookies(driver);
		driver.quit();

	}
	

	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	public static String firstName, lastName;
	public static String password, emailAddress;
	public static Set<Cookie> loggedCookies;
}
