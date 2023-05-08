package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.MyAccountPageObject;
import pageObjects.nopCommerce.PageGeneratorManager;
import pageObjects.nopCommerce.RegisterPageObject;

public class Level_06_Page_Generator_Manager_3 extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		firstName = "Geni";
		lastName = "Nguyen";
		validEmail = "afc" + generateRandomNumber() + "@acb.com";
		notFoundEmail = "afc" + generateRandomNumber() + "@mail.com";
		invalidEmail = "abc@gds!!";
		validPassword = "123456";
		invalidPassword = validPassword + "123";

		driver.get("https://demo.nopcommerce.com/");
		homePage = PageGeneratorManager.getHomePage(driver);

		System.out.println("Pre-conditions - Step 01: Click to register link");
		registerPage = homePage.clickToRegisterLink();

		System.out.println("Pre-conditions - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(validEmail);
		registerPage.inputToPassowrdTextBox(validPassword);
		registerPage.inputToConfirmPasswordTextBox(validPassword);

		System.out.println("Pre-conditions - Step 03: Click to register button");
		registerPage.clickToRegisterButton();

		System.out.println("Pre-conditions - Step 04: Verify success registration");
		Assert.assertEquals(registerPage.getSuccessRegisterMessage(), "Your registration completed");

	}

	@Test
	public void Login_01_Empty_Data() {
		System.out.println("Login_01_Empty_Data - Step 01: Click to login link");
		loginPage = homePage.clickToLoginLink();

		System.out.println("Login_01_Empty_Data - Step 02: Click to login button");
		loginPage.clickToLoginButton();

		System.out.println("Login_01_Empty_Data - Step 03: Verify error message display");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextBox(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		loginPage = homePage.clickToLoginLink();
		// từ trang home --> click login link --> qua trang login

		loginPage.inputToEmailTextBox(invalidEmail);
		loginPage.inputToPasswordTextBox(validPassword);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextBox(), "Wrong email");
	}

	@Test
	public void Login_03_Unregistered_Email() {
		loginPage = homePage.clickToLoginLink();

		loginPage.inputToEmailTextBox(notFoundEmail);
		loginPage.inputToPasswordTextBox(validPassword);

		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorLoginMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void Login_04_Valid_Email_Empty_Password() {
		loginPage = homePage.clickToLoginLink();

		loginPage.inputToEmailTextBox(validEmail);
		loginPage.inputToPasswordTextBox("");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorLoginMessage(),
			"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_05_Valid_Email_Wrong_Password() {
		loginPage = homePage.clickToLoginLink();

		loginPage.inputToEmailTextBox(validEmail);
		loginPage.inputToPasswordTextBox(invalidPassword);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorLoginMessage(), "Login was unsuccessful. Please correct the errors and try again."
			+ "\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_06_Login_Success() {
		loginPage = homePage.clickToLoginLink();

		loginPage.inputToEmailTextBox(validEmail);
		loginPage.inputToPasswordTextBox(validPassword);

		homePage = loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getTitle(driver), "nopCommerce demo store");
		Assert.assertTrue(homePage.isMyAccountDisplayed());
		
		// click to my account
		myAccountPage = homePage.clickToMyAccountLink();
		
		// verify data
		Assert.assertEquals(myAccountPage.getValueAtFirstNameTextbox(), firstName);
		Assert.assertEquals(myAccountPage.getValueAtLastNameTextbox(), lastName);
		Assert.assertEquals(myAccountPage.getValueAtEmailTextbox(), validEmail);
	}	

	public int generateRandomNumber() {
		return new Random().nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private LoginPageObject loginPage;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private MyAccountPageObject myAccountPage;
	private String firstName, lastName, validPassword, invalidPassword, validEmail, invalidEmail, notFoundEmail;

	
}
