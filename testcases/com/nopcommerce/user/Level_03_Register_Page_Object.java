package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

public class Level_03_Register_Page_Object {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private String firstName, lastName, password, emailAddress;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		homePage = new HomePageObject(driver);
		registerPage = new RegisterPageObject(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		firstName = "Geni";
		lastName = "Nguyen";
		password = "123456";
		emailAddress = "afc" + generateFakeNumber() + "@acb.com";
		driver.get("https://demo.nopcommerce.com/");
	}

	@Test
	public void TC_01_Register_Empty_Data() {
		System.out.println("Home page - Step 01: Click to register link");
		homePage.clickToRegisterLink();

		System.out.println("Register page - Step 02: Click to register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register page - Step 02: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextBox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastNameTextBox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextBox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextBox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextBox(), "Password is required.");

	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		System.out.println("Home page - Step 01: Click to register link");
		homePage.clickToRegisterLink();

		System.out.println("Register page - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox("asdb12#@123");
		registerPage.inputToPassowrdTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);

		System.out.println("Register page - Step 03: Click to register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register page - Step 04: Verify error message of email field");
		Assert.assertEquals(registerPage.getErrorWrongEmailMessage(), "Wrong email");
	}

	@Test
	public void TC_03_Register_Success() {
		System.out.println("Home page - Step 01: Click to register link");
		homePage.clickToRegisterLink();
		System.out.println("Register page - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPassowrdTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);

		System.out.println("Register page - Step 03: Click to register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register page - Step 04: Verify success registration");
		Assert.assertEquals(registerPage.getSuccessRegisterMessage(), "Your registration completed");

	}

	@Test
	public void TC_04_Register_Existing_Email() {
		System.out.println("Home page - Step 01: Click to register link");
		homePage.clickToRegisterLink();

		System.out.println("Register page - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPassowrdTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);

		System.out.println("Register page - Step 03: Click to register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register page - Step 04: Verify error message of email field");
		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");

	}

	@Test
	public void TC_05_Register_Password_Less_Than_6_Chars() {

		System.out.println("Home page - Step 01: Click to register link");
		homePage.clickToRegisterLink();

		System.out.println("Register page - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPassowrdTextBox("1234");
		registerPage.inputToConfirmPasswordTextBox("1234");

		System.out.println("Register page - Step 03: Click to register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register page - Step 04: Verify error message of password field");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextBox(), "Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		System.out.println("Home page - Step 01: Click to register link");
		homePage.clickToRegisterLink();

		System.out.println("Register page - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPassowrdTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password + "1");

		System.out.println("Register page - Step 03: Click to register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register page - Step 04: Verify error message of confirm password field");
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
