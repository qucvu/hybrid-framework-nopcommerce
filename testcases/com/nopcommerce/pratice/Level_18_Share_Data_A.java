package com.nopcommerce.pratice;

import java.lang.reflect.Method;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.nopcommerce.common.Common_01_Register_End_User;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import reportConfig.ExtentTestManager;

public class Level_18_Share_Data_A extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(Method method, String browserName) {


		driver = getBrowserDriver(browserName, "https://demo.nopcommerce.com/");
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		
		emailAddress = Common_01_Register_End_User.emailAddress;
		password = Common_01_Register_End_User.password;

		
//		ExtentTestManager.getTest().log(Status.INFO,"Pre-condition - Step 01: Navigate to Login page");
		loginPage = homePage.openLoginPage();

//		ExtentTestManager.getTest().log(Status.INFO,"Pre-condition - Step 02: Enter to Email textbox with value is '" + emailAddress + "'");
		loginPage.inputToEmailTextBox(emailAddress);

//		ExtentTestManager.getTest().log(Status.INFO,"Pre-condition - Step 03: Enter to Password textbox with value is '" + password + "'");
		loginPage.inputToPasswordTextBox(password);

//		ExtentTestManager.getTest().log(Status.INFO,"Pre-condition - Step 04: Click to Login button");
		homePage = loginPage.clickToLoginButton();


//		ExtentTestManager.getTest().log(Status.INFO,"Pre-condition - Step 06: Verify the 'My Account' Link is displayed");
		verifyTrue(homePage.isMyAccountDisplayed());

	}


	@Test
	public void Search_01_(Method method) {
		ExtentTestManager.startTest(method.getName(), "User_01_Register");
		ExtentTestManager.getTest().log(Status.INFO,"Pre-condition - Step 06: Verify the 'My Account' Link is displayed");
	}

	@Test
	public void Search_02_() {

	}
	@Test
	public void Search_03_() {

	}
	@Test
	public void Search_04_() {

	}
	@Test
	public void Search_05_() {

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
	private String password, emailAddress;
}
