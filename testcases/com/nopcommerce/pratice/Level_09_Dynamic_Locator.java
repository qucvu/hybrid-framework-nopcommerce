package com.nopcommerce.pratice;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;

public class Level_09_Dynamic_Locator extends BaseTest {

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
	public void User_01_Register_Login() {
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

		loginPage = homePage.openLoginPage();

		loginPage.inputToEmailTextBox(emailAddress);
		loginPage.inputToPasswordTextBox(password);
		homePage = loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getTitle(driver), "nopCommerce demo store");
		Assert.assertTrue(homePage.isMyAccountDisplayed());
		customerInfoPage = homePage.openMyAccountPage(driver);

		Assert.assertTrue(customerInfoPage.isCustomerInfoDisplayed());

		Assert.assertEquals(customerInfoPage.getValueAtFirstNameTextbox(), firstName);
		Assert.assertEquals(customerInfoPage.getValueAtLastNameTextbox(), lastName);
		Assert.assertEquals(customerInfoPage.getValueAtEmailTextbox(), emailAddress);

	}

	@Test
	public void User_04_Dynamic_Page_1() {

		// từ customer info --> address
		addressPage = (UserAddressPageObject) customerInfoPage.openDynamicPageAtMyAccountByPageName(driver, "Addresses");

		// Address --> my product review
		myProductReviewPage = (UserMyProductReviewPageObject) addressPage.openDynamicPageAtMyAccountByPageName(driver, "My product reviews");

		// my product review --> reward point
		rewardPointPage = (UserRewardPointPageObject) myProductReviewPage.openDynamicPageAtMyAccountByPageName(driver, "Reward points");

		// reward point --> address
		addressPage = (UserAddressPageObject) rewardPointPage.openDynamicPageAtMyAccountByPageName(driver, "Addresses");

		// address --> reward point
		rewardPointPage = (UserRewardPointPageObject) addressPage.openDynamicPageAtMyAccountByPageName(driver, "Reward points");

		// reward point --> my product review
		myProductReviewPage = (UserMyProductReviewPageObject) rewardPointPage.openDynamicPageAtMyAccountByPageName(driver, "My product reviews");

		// my product review --> customer info page
		customerInfoPage = (UserCustomerInfoPageObject) myProductReviewPage.openDynamicPageAtMyAccountByPageName(driver, "Customer info");
	}

	@Test
	public void User_04_Dynamic_Page_2() {
		customerInfoPage.openDynamicPageAtMyAccountByName(driver, "Addresses");
		addressPage = PageGeneratorManager.getUserAddressPage(driver);
		
		// Address --> my product review
		addressPage.openDynamicPageAtMyAccountByName(driver, "My product reviews");
		myProductReviewPage = PageGeneratorManager.getUserMyProductReviewPage(driver);
		
		// my product review --> reward point
		myProductReviewPage.openDynamicPageAtMyAccountByPageName(driver, "Reward points");
		rewardPointPage = PageGeneratorManager.getUserRewardPointPage(driver);

		// reward point --> address
		rewardPointPage.openDynamicPageAtMyAccountByPageName(driver, "Addresses");
		addressPage = PageGeneratorManager.getUserAddressPage(driver);

		// address --> reward point
		addressPage.openDynamicPageAtMyAccountByPageName(driver, "Reward points");
		rewardPointPage = PageGeneratorManager.getUserRewardPointPage(driver);

		// reward point --> my product review
		rewardPointPage.openDynamicPageAtMyAccountByPageName(driver, "My product reviews");
		myProductReviewPage = PageGeneratorManager.getUserMyProductReviewPage(driver);

		// my product review --> customer info page
		myProductReviewPage.openDynamicPageAtMyAccountByPageName(driver, "Customer info");
		customerInfoPage = PageGeneratorManager.getUserCustomerInfoPage(driver);

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
	private UserCustomerInfoPageObject customerInfoPage;
	private UserRewardPointPageObject rewardPointPage;
	private UserAddressPageObject addressPage;
	private UserMyProductReviewPageObject myProductReviewPage;
	private String firstName, lastName, password, emailAddress;

}
