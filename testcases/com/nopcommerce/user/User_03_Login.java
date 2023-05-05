package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class User_03_Login {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private LoginPageObject loginPage;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;

	String email, password;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		loginPage = new LoginPageObject(driver);
		homePage = new HomePageObject(driver);
		registerPage = new RegisterPageObject(driver);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		email = "geni" + generateRandomNumber() + "@gmail.com";
		password = "123456";
		driver.get("https://demo.nopcommerce.com/");

	}

	@Test
	public void TC_01_Login_Empty_Data() {
		homePage.clickToLoginLink();
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextBox(), "Please enter your email");
	}

	@Test
	public void TC_02_Login_Invalid_Email() {
		homePage.clickToLoginLink();
		loginPage.inputToEmailTextBox("abc@gds!!");
		loginPage.inputToPasswordTextBox(password);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextBox(), "Wrong email");
	}

	@Test
	public void TC_03_Login_Unregistered_Email() {
		homePage.clickToLoginLink();
		loginPage.inputToEmailTextBox(email);
		loginPage.inputToPasswordTextBox(password);

		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorLoginMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void TC_04_Login_Empty_Password() {
		homePage.clickToRegisterLink();
		registerPage.inputToFirstNameTextBox("Geni");
		registerPage.inputToLastNameTextBox("Nguyen");
		registerPage.inputToEmailTextBox(email);
		registerPage.inputToPassowrdTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getSuccessRegisterMessage(), "Your registration completed");
		homePage.clickToLoginLink();

		loginPage.inputToEmailTextBox(email);
		loginPage.inputToPasswordTextBox("");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorLoginMessage(),
			"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void TC_05_Wrong_Password() {
		loginPage.inputToEmailTextBox(email);
		loginPage.inputToPasswordTextBox("1245");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorLoginMessage(), "Login was unsuccessful. Please correct the errors and try again."
			+ "\nThe credentials provided are incorrect");
	}

	@Test
	public void TC_06_Login_Success() {
		loginPage.inputToEmailTextBox(email);
		loginPage.inputToPasswordTextBox(password);
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getTitle(driver), "nopCommerce demo store");
		Assert.assertTrue(homePage.isMyAccountDisplayed());
	}

	public int generateRandomNumber() {
		return new Random().nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
