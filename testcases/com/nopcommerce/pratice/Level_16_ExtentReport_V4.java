package com.nopcommerce.pratice;
//package com.nopcommerce.user;
//
//import java.lang.reflect.Method;
//import java.util.Random;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//
//import commons.BaseTest;
//import commons.PageGeneratorManager;
//import pageObjects.nopCommerce.user.UserHomePageObject;
//import pageObjects.nopCommerce.user.UserLoginPageObject;
//import pageObjects.nopCommerce.user.UserRegisterPageObject;
//import reportConfig.ExtentManager;
//
//public class Level_16_ExtentReport_V4 extends BaseTest {
//	private ExtentReports extent;
//	private ExtentTest test;
//
//	@Parameters("browser")
//	@BeforeClass
//	public void beforeClass(String browserName) {
//		driver = getBrowserDriver(browserName);
//		firstName = "Geni";
//		lastName = "Nguyen";
//		emailAddress = "afc" + generateRandomNumber() + "@acb.com";
//		password = "123456";
//		driver.get("https://demo.nopcommerce.com/");
//		homePage = PageGeneratorManager.getUserHomePage(driver);
//
//	}
//
//	@BeforeMethod
//	public void setup() {
//		extent = ExtentManager.getInstance();
//		test = extent.createTest("Test case report for Extent report V4", "This is an example test");
//	}
//
//	@Test
//	public void User_01_Register(Method method) {
//		test.log(Status.INFO, "Register - Step 01: Navigate to 'Register' page");
//		registerPage = homePage.openRegisterPage();
//
//		test.log(Status.INFO, "Register - Step 02: Enter to FirstName textbox with value is '" + firstName + "'");
//		registerPage.inputToFirstNameTextBox(firstName);
//
//		test.log(Status.INFO, "Register - Step 03: Enter to Lastname textbox with value is '" + lastName + "'");
//		registerPage.inputToLastNameTextBox(lastName);
//
//		test.log(Status.INFO, "Register - Step 04: Enter to Email Address textbox with value is '" + emailAddress + "'");
//		registerPage.inputToEmailTextBox(emailAddress);
//
//		test.log(Status.INFO, "Register - Step 05: Enter to Password textbox with value is '" + password + "'");
//		registerPage.inputToPassowrdTextBox(password);
//
//		test.log(Status.INFO, "Register - Step 06: Enter to Confirm Password textbox with value is '" + password + "'");
//		registerPage.inputToConfirmPasswordTextBox(password);
//
//		test.log(Status.INFO, "Register - Step 08: Click to 'Register' button");
//		registerPage.clickToRegisterButton();
//
//		test.log(Status.INFO, "Register - Step 09: Verify register success message is displayed");
//		Assert.assertEquals(registerPage.getSuccessRegisterMessage(), "Your registration completed");
//
//	}
//
//	@Test
//	public void User_02_Login(Method method) {
//
//		test.log(Status.INFO, "Login - Step 01: Navigate to Login page");
//		homePage = registerPage.clickToContinueButton();
//		loginPage = homePage.openLoginPage();
//
//		test.log(Status.INFO, "Login - Step 02: Enter to Email textbox with value is '" + emailAddress + "'");
//		loginPage.inputToEmailTextBox(emailAddress);
//
//		test.log(Status.INFO, "Login - Step 03: Enter to Password textbox with value is '" + password + "'");
//		loginPage.inputToPasswordTextBox(password);
//
//		test.log(Status.INFO, "Login - Step 04: Click to Login button");
//		homePage = loginPage.clickToLoginButton();
//
//		test.log(Status.INFO, "Login - Step 06: Verify the 'My Account' Link is displayed");
//		Assert.assertFalse(homePage.isMyAccountDisplayed());
//
//		test.log(Status.INFO, "Login - Step 05: Verify the correct title of Home Page");
//		Assert.assertEquals(loginPage.getTitle(driver), "nopCommerce demo storee");
//
//	}
//
//	public int generateRandomNumber() {
//		return new Random().nextInt(99999);
//	}
//
//	@AfterClass
//	public void afterClass() {
//		driver.quit();
//	}
//
//	private WebDriver driver;
//	private UserLoginPageObject loginPage;
//	private UserHomePageObject homePage;
//	private UserRegisterPageObject registerPage;
//	private String firstName, lastName, password, emailAddress;
//}
