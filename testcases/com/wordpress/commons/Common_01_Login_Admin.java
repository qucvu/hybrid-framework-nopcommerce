package com.wordpress.commons;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import pageObjects.wordpress.AdminDashboardPO;
import pageObjects.wordpress.AdminLoginPO;
import pageObjects.wordpress.PageGeneratorManager;

public class Common_01_Login_Admin extends BaseTest {
	@Parameters({"browser", "urlAdmin"})
	@BeforeTest(description = "Login to admin site to all Classes Test")
	public void Login(String browserName, String url) {
		adminUserName = "vunguyen";
		adminPassword = "Nguyenquocvu.111";
		
		log.info("Pre-conditions - Step 01: Open admin Page URL");
		driver = getBrowserDriver(browserName, url);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

		log.info("Pre-conditions - Step 02: Enter Username textbox with value: " + adminUserName);
		adminLoginPage.enterToUserNameTextbox(adminUserName);
		
		log.info("Pre-conditions - Step 03: Enter Password textbox with value: " + adminPassword);
		adminLoginPage.enterToPasswordTextbox(adminPassword);

		log.info("Pre-conditions - Step 03: Click to 'Log in' button");
		adminLoginPage.clickToLoginButton();
		adminDashboardPage = PageGeneratorManager.getAdminDashbpardPage(driver);
		verifyTrue(adminDashboardPage.isDashboardTitleDisplayed());

		loggedCookies = adminDashboardPage.getAllCookies(driver);
//		driver.quit();
	}


	private WebDriver driver;
	private String adminUserName, adminPassword;
	public static Set<Cookie> loggedCookies;
	private AdminLoginPO adminLoginPage;
	private AdminDashboardPO adminDashboardPage;

}
