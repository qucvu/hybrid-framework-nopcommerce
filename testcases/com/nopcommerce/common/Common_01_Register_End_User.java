package com.nopcommerce.common;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Common_01_Register_End_User extends BaseTest {
	@Parameters("browser")
	@BeforeTest(description = "Create a new common User for all Classes Test")
	public void Register(String browserName) {
		driver = getBrowserDriver(browserName);
		firstName = "Geni";
		lastName = "Nguyen";
		emailAddress = "afc" + generateRandomNumber() + "@acb.com";
		password = "123456";

		driver.get("https://demo.nopcommerce.com/");
		homePage = PageGeneratorManager.getUserHomePage(driver);

//		ExtentTestManager.getTest().log(Status.INFO,"Register - Step 01: Navigate to 'Register' page");
		registerPage = homePage.openRegisterPage();

//		ExtentTestManager.getTest().log(Status.INFO,"Register - Step 02: Enter to FirstName textbox with value is '" + firstName + "'");
		registerPage.inputToFirstNameTextBox(firstName);

//		ExtentTestManager.getTest().log(Status.INFO,"Register - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToLastNameTextBox(lastName);

//		ExtentTestManager.getTest().log(Status.INFO,"Register - Step 04: Enter to Email Address textbox with value is '" + emailAddress + "'");
		registerPage.inputToEmailTextBox(emailAddress);

//		ExtentTestManager.getTest().log(Status.INFO,"Register - Step 05: Enter to Password textbox with value is '" + password + "'");
		registerPage.inputToPassowrdTextBox(password);

//		ExtentTestManager.getTest().log(Status.INFO,"Register - Step 06: Enter to Confirm Password textbox with value is '" + password + "'");
		registerPage.inputToConfirmPasswordTextBox(password);

//		ExtentTestManager.getTest().log(Status.INFO,"Register - Step 08: Click to 'Register' button");
		registerPage.clickToRegisterButton();

//		ExtentTestManager.getTest().log(Status.INFO,"Register - Step 09: Verify register success message is displayed");
		verifyEquals(registerPage.getSuccessRegisterMessage(), "Your registration completed");

//		ExtentTestManager.getTest().log(Status.INFO,"Register - Step 10: Click to Continue button");
		homePage = registerPage.clickToContinueButton();
		driver.quit();
	}


	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private String firstName, lastName;
	public static String password, emailAddress;
}
