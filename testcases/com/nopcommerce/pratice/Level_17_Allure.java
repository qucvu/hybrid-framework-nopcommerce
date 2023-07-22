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
//import io.qameta.allure.Description;
//import io.qameta.allure.Severity;
//import io.qameta.allure.SeverityLevel;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_17_Allure extends BaseTest {

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
	
//	@Description("User 01 - Register")
//	@Severity(SeverityLevel.NORMAL)
	@Test
	public void User_01_Register(Method method) {
		
		registerPage = homePage.openRegisterPage();
		
		registerPage.inputToFirstNameTextBox(firstName);
		
		registerPage.inputToLastNameTextBox(lastName);
		
		registerPage.inputToEmailTextBox(emailAddress);

		registerPage.inputToPassowrdTextBox(password);
		
		registerPage.inputToConfirmPasswordTextBox(password);
		
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getSuccessRegisterMessage(), "Your registration completed");
		
	}
	
	
//	@Description("User 01 - Login")
//	@Severity(SeverityLevel.NORMAL)
	@Test
	public void User_02_Login(Method method) {

		homePage = registerPage.clickToContinueButton();
		loginPage = homePage.openLoginPage();
		
		loginPage.inputToEmailTextBox(emailAddress);
		
		loginPage.inputToPasswordTextBox(password);
		
		homePage = loginPage.clickToLoginButton();
		
		Assert.assertFalse(homePage.isMyAccountDisplayed());
		
		Assert.assertEquals(loginPage.getTitle(driver), "nopCommerce demo storee");
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
