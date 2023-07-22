package com.nopcommerce.pratice;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_03_Register_Page_Object {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private String firstName, lastName, password, emailAddress;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		firstName = "Geni";
		lastName = "Nguyen";
		password = "123456";
		emailAddress = "afc" + generateFakeNumber() + "@acb.com";
		driver.get("https://demo.nopcommerce.com/");
		homePage = new UserHomePageObject(driver);
	}

	@Test
	public void Register_01_Empty_Data() {
		System.out.println("Register_01 - Step 01: Click to register link");
		homePage.openRegisterPage();
		registerPage = new UserRegisterPageObject(driver);

		System.out.println("Register_01 - Step 02: Click to register button");
		registerPage.clickToRegisterButton();
		System.out.println("Register_01 - Step 02: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextBox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastNameTextBox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextBox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextBox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextBox(), "Password is required.");

	}

	@Test
	public void Register_02_Invalid_Email() {
		System.out.println("Register_02 - Step 01: Click to register link");
		homePage.openRegisterPage();
		// nhảy qua  trang register
		registerPage = new UserRegisterPageObject(driver);

		System.out.println("Register_02 - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox("asdb12#@123");
		registerPage.inputToPassowrdTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);

		System.out.println("Register_02 - Step 03: Click to register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_02 - Step 04: Verify error message of email field");
		Assert.assertEquals(registerPage.getErrorWrongEmailMessage(), "Wrong email");
	}

	@Test
	public void Register_03_Register_Success() {
		System.out.println("Register_03 - Step 01: Click to register link");
		homePage.openRegisterPage();
		
		// nhảy qua  trang register
		registerPage = new UserRegisterPageObject(driver);
		System.out.println("Register_03 - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPassowrdTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);

		System.out.println("Register_03 - Step 03: Click to register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_03 - Step 04: Verify success registration");
		Assert.assertEquals(registerPage.getSuccessRegisterMessage(), "Your registration completed");
		


	}

	@Test
	public void Register_04_Existing_Email() {
		System.out.println("Register_04 - Step 01: Click to register link");
		homePage.openRegisterPage();
		// nhảy qua  trang register
		registerPage = new UserRegisterPageObject(driver);

		System.out.println("Register_04 - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPassowrdTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);

		System.out.println("Register_04 - Step 03: Click to register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_04 - Step 04: Verify error message of email field");
		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");

	}

	@Test
	public void Register_05_Password_Less_Than_6_Chars() {
		System.out.println("Register_05 - Step 01: Click to register link");
		homePage.openRegisterPage();
		// nhảy qua  trang register
		registerPage = new UserRegisterPageObject(driver);

		System.out.println("Register_05 - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPassowrdTextBox("1234");
		registerPage.inputToConfirmPasswordTextBox("1234");

		System.out.println("Register_05 - Step 03: Click to register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_05 - Step 04: Verify error message of password field");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextBox(), "Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void Register_06_Invalid_Confirm_Password() {
		System.out.println("Register_06 - Step 01: Click to register link");
		homePage.openRegisterPage();
		
		// nhảy qua  trang register
		registerPage = new UserRegisterPageObject(driver);

		System.out.println("Register_06 - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPassowrdTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password + "1");

		System.out.println("Register_06 - Step 03: Click to register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_06 - Step 04: Verify error message of confirm password field");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextBox(), "The password and confirmation password do not match.");

	}

	public int generateFakeNumber() {
		return new Random().nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
