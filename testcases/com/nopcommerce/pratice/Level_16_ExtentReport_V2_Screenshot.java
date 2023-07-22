package com.nopcommerce.pratice;

import java.lang.reflect.Method;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_16_ExtentReport_V2_Screenshot extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		firstName = "Geni";
		lastName = "Nguyen";
		emailAddress = "afc" + generateRandomNumber() + "@acb.com";
		password = "123456";

		driver.get("https://demo.nopcommerce.com/");
		homePage = PageGeneratorManager.getUserHomePage(driver);


	}

	@Test
	public void User_01_Register(Method method) {
		// comment due to remove extent report V2
//		ExtentManager.startTest(method.getName(), "User_01_Register");
		
//		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 01: Navigate to 'Register' page");
		registerPage = homePage.openRegisterPage();
		
//		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 02: Enter to FirstName textbox with value is '" + firstName + "'");
		registerPage.inputToFirstNameTextBox(firstName);
		
//		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToLastNameTextBox(lastName);
		
//		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 04: Enter to Email Address textbox with value is '" + emailAddress + "'");
		registerPage.inputToEmailTextBox(emailAddress);

//		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 05: Enter to Password textbox with value is '" + password + "'");
		registerPage.inputToPassowrdTextBox(password);
		
//		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 06: Enter to Confirm Password textbox with value is '" + password + "'");
		registerPage.inputToConfirmPasswordTextBox(password);
		
//		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 08: Click to 'Register' button");
		registerPage.clickToRegisterButton();

//		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 09: Verify register success message is displayed");
		Assert.assertEquals(registerPage.getSuccessRegisterMessage(), "Your registration completed");
		
//		ExtentManager.endTest();
	}

	@Test
	public void User_02_Login(Method method) {
//		ExtentManager.startTest(method.getName(), "User_02_Login");
		
//		ExtentManager.getTest().log(LogStatus.INFO, "Login - Step 01: Navigate to Login page");
		homePage = registerPage.clickToContinueButton();
		loginPage = homePage.openLoginPage();
		
//		ExtentManager.getTest().log(LogStatus.INFO, "Login - Step 02: Enter to Email textbox with value is '" + emailAddress + "'");
		loginPage.inputToEmailTextBox(emailAddress);
		
//		ExtentManager.getTest().log(LogStatus.INFO, "Login - Step 03: Enter to Password textbox with value is '" + password + "'");
		loginPage.inputToPasswordTextBox(password);
		
//		ExtentManager.getTest().log(LogStatus.INFO, "Login - Step 04: Click to Login button");
		homePage = loginPage.clickToLoginButton();
		
//		ExtentManager.getTest().log(LogStatus.INFO, "Login - Step 06: Verify the 'My Account' Link is displayed");
		Assert.assertFalse(homePage.isMyAccountDisplayed());
		
//		ExtentManager.getTest().log(LogStatus.INFO, "Login - Step 05: Verify the correct title of Home Page");
		Assert.assertEquals(loginPage.getTitle(driver), "nopCommerce demo storee");
		
//		ExtentManager.endTest();
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
