package com.nopcommerce.pratice;

import java.lang.reflect.Method;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.nopcommerce.common.Common_01_Register_Cookie;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import reportConfig.ExtentTestManager;

public class Level_18_Share_Data_C extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName, Method method) {

		driver = getBrowserDriver(browserName, "https://demo.nopcommerce.com/");
		homePage = PageGeneratorManager.getUserHomePage(driver);

		
//		log.info("Pre-condition - Step 01: Navigate to Login page");
//		loginPage = homePage.openLoginPage();

//		log.info("Pre-condition - Step 02: Set cookie and reload page");
		homePage.setCookies(driver, Common_01_Register_Cookie.loggedCookies);
		homePage.refreshCurrentPage(driver);
		
//		log.info("Pre-condition - Step 03: Verify the 'My Account' Link is displayed");
		verifyTrue(homePage.isMyAccountDisplayed());

	}

	@Test
	public void Search_01_(Method method) {
		ExtentTestManager.startTest(method.getName(), "User_01_Register");
		
		ExtentTestManager.getTest().log(Status.INFO, "Register - Step 01: Navigate to 'Register' page");


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
	private UserHomePageObject homePage;
}
