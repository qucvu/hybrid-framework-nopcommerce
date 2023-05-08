package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.AddressPageObject;
import pageObjects.nopCommerce.CustomerInfoPageObject;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.LoginPageObject;
import pageObjects.nopCommerce.MyProductReviewPageObject;
import pageObjects.nopCommerce.PageGeneratorManager;
import pageObjects.nopCommerce.RegisterPageObject;
import pageObjects.nopCommerce.RewardPointPageObject;

public class Level_07_Switch_Page extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		firstName = "Geni";
		lastName = "Nguyen";
		emailAddress = "afc" + generateRandomNumber() + "@acb.com";
		password = "123456";

		driver.get("https://demo.nopcommerce.com/");
		homePage = PageGeneratorManager.getHomePage(driver);


	}

	@Test
	public void User_01_Register() {
		registerPage = homePage.openRegisterPage();

		System.out.println("Pre-conditions - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPassowrdTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);

		System.out.println("Pre-conditions - Step 03: Click to register button");
		registerPage.clickToRegisterButton();

		System.out.println("Pre-conditions - Step 04: Verify success registration");
		Assert.assertEquals(registerPage.getSuccessRegisterMessage(), "Your registration completed");
		
		homePage = registerPage.clickToContinueButton();
	}

	@Test
	public void User_02_Login() {
		loginPage = homePage.openLoginPage();
		
		loginPage.inputToEmailTextBox(emailAddress);
		loginPage.inputToPasswordTextBox(password);
		homePage = loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getTitle(driver), "nopCommerce demo store");
		Assert.assertTrue(homePage.isMyAccountDisplayed());


	}

	@Test
	public void User_03_My_Account() {
		customerInfoPage = homePage.openCustomerInfoPage();
		
		Assert.assertTrue(customerInfoPage.isCustomerInfoDisplayed());
		
		Assert.assertEquals(customerInfoPage.getValueAtFirstNameTextbox(), firstName);
		Assert.assertEquals(customerInfoPage.getValueAtLastNameTextbox(), lastName);
		Assert.assertEquals(customerInfoPage.getValueAtEmailTextbox(), emailAddress);

	}

	@Test
	public void User_04_Switch_Page() {
		
		// từ customer info --> address
		addressPage = customerInfoPage.openAddressPage(driver);
		
		// Address --> my product review
		myProductReviewPage = addressPage.openMyProductReviewPage(driver);
		
		// my product review --> reward point
		rewardPointPage = myProductReviewPage.openRewardPointPage(driver);
		
		// reward point --> address 
		addressPage = registerPage.openAddressPage(driver);
		
		// address --> reward point
		rewardPointPage = addressPage.openRewardPointPage(driver);
		
		// reward point --> my product review
		myProductReviewPage = rewardPointPage.openMyProductReviewPage(driver);
		
		// my product review --> customer info page
		customerInfoPage = myProductReviewPage.openCustomerInfoPage(driver);
	}
	
	@Test
	public void User_04_Switch_Role() {
		// role admin --> role user
		
		
		// role user --> role admin
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
	private CustomerInfoPageObject customerInfoPage;
	private RewardPointPageObject rewardPointPage;
	private AddressPageObject addressPage;
	private MyProductReviewPageObject myProductReviewPage;
	private String firstName, lastName, password, emailAddress;

	
}
