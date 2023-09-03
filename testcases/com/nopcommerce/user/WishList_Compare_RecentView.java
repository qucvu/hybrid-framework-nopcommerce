package com.nopcommerce.user;

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

public class WishList_Compare_RecentView extends BaseTest {
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		log.info("Pre-condition - Step 01: Open End User Page URL");
		driver = getBrowserDriver(browserName, GlobalConstants.PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getUserHomePage(driver);

		log.info("Pre-condition - Step 02: Set cookie and reload page");
		homePage.setCookies(driver, Common_01_Register_Cookie.loggedCookies);
		homePage.refreshCurrentPage(driver);

		log.info("Pre-condition - Step 03: Verify 'My Account' Link is displayed");
		verifyTrue(homePage.isMyAccountDisplayed());
		
		log.info("Pre-condition - Step 03: Verify 'My Account' Link is displayed");


	}

	@Test
	public void WishList_01() {
	}

	@Test
	public void WishList_02() {
	}

	@Test
	public void WishList_03() {
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}

	private WebDriver driver;
	private String productName;
	private UserHomePageObject homePage;

}
