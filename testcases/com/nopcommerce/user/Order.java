package com.nopcommerce.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Register_Cookie;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import reportConfig.ExtentTestManager;

public class Order extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName, Method method) {
		driver = getBrowserDriver(browserName, GlobalConstants.PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		log.info("Pre-condition - Step 01: Set cookie and reload page");
		homePage.setCookies(driver, Common_01_Register_Cookie.loggedCookies);
		homePage.refreshCurrentPage(driver);

		log.info("Pre-condition - Step 02: Verify the 'My Account' Link is displayed");
		verifyTrue(homePage.isMyAccountDisplayed());

	}

	@Test
	public void My_Account_01_Customer_Info(Method method) {
		ExtentTestManager.startTest(method.getName(), "My_Account_01_Customer_Info");
	}

	@Test
	public void My_Account_02_Customer_Address(Method method) {
		ExtentTestManager.startTest(method.getName(), "My_Account_02_Customer_Address");

	}

	@Test
	public void My_Account_03_Change_Password(Method method) {
		ExtentTestManager.startTest(method.getName(), "My_Account_03_Change_Password");

	}

	@Test
	public void My_Account_04_Product_Review(Method method) {
		ExtentTestManager.startTest(method.getName(), "My_Account_04_Product_Review");

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		 closeBrowserDriver();
	}

	private WebDriver driver;
	private UserHomePageObject homePage;
}
