package com.nopcommerce.pratice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

//@Listeners(commons.MethodListener.class)
public class Level_12_Custom_Assert extends BaseTest {

	@BeforeClass
	public void beforeClass() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();


		firstName = "Geni";
		lastName = "Nguyen";
		emailAddress = "afc" + generateRandomNumber() + "@acb.com";
		password = "123456";

		driver.get("https://demo.nopcommerce.com/");
		homePage = PageGeneratorManager.getUserHomePage(driver);


	}

	@Test
	public void User_01_Register() {
		registerPage = homePage.openRegisterPage();
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPassowrdTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);
		registerPage.clickToRegisterButton();

		System.out.println("Assert 01");
		verifyEquals(registerPage.getSuccessRegisterMessage(), "Your registration completed");
		
		homePage = registerPage.clickToContinueButton();
	}

	@Test
	public void User_02_Login() {
		loginPage = homePage.openLoginPage();
		
		loginPage.inputToEmailTextBox(emailAddress);
		loginPage.inputToPasswordTextBox(password);
		homePage = loginPage.clickToLoginButton();
		
		System.out.println("Assert 02 - Failed");
		verifyEquals(loginPage.getTitle(driver), "nopCommerce demo storeee");
		
		System.out.println("Assert 03");
		verifyTrue(homePage.isMyAccountDisplayed());
		
		System.out.println("Assert 04 - Failed");
		verifyEquals(loginPage.getTitle(driver), "nopCommercee");



	}

	@Test
	public void User_03_My_Account() {
		customerInfoPage = homePage.openMyAccountPage(driver);
		
		Assert.assertTrue(customerInfoPage.isCustomerInfoDisplayed());
		
		System.out.println("Assert 05");
		verifyEquals(customerInfoPage.getValueAtFirstNameTextbox(), firstName);
		verifyEquals(customerInfoPage.getValueAtLastNameTextbox(), lastName);
		verifyEquals(customerInfoPage.getValueAtEmailTextbox(), emailAddress);
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private UserLoginPageObject loginPage;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserCustomerInfoPageObject customerInfoPage;
	private String firstName, lastName, password, emailAddress;
}
