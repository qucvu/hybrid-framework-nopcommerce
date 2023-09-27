package com.wordpress.admin;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.wordpress.AdminDashboardPO;
import pageObjects.wordpress.AdminLoginPO;
import pageObjects.wordpress.AdminUserPO;
import pageObjects.wordpress.PageGeneratorManager;

public class User_01_View_User extends BaseTest {

	@Parameters({ "browser", "urlAdmin", "urlUser" })
	@BeforeClass
	public void beforeClass(String browserName, String urlAdmin, String urlEndUser) {
		adminUserName = "vunguyen";
		adminPassword = "Nguyenquocvu.111";
		this.adminUrl = urlAdmin;

		log.info("Pre-condition - Step 01: Open browser and admin site");
		driver = getBrowserDriverUrl(browserName, this.adminUrl);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

		log.info("Pre-conditions - Step 02: Enter Username textbox with value: " + adminUserName);
		adminLoginPage.enterToUserNameTextbox(adminUserName);

		log.info("Pre-conditions - Step 03: Enter Password textbox with value: " + adminPassword);
		adminLoginPage.enterToPasswordTextbox(adminPassword);

		log.info("Pre-conditions - Step 03: Click to 'Log in' button");
		adminDashboardPage = adminLoginPage.clickToLoginButton();

		log.info("Pre-condition - Step 03: Verify that Navigate to Admin Dashboard page successfully");
		verifyTrue(adminDashboardPage.isDashboardTitleDisplayed());

	}

	@Test
	public void TC_01_View_User() {
		log.info("View User - Step 01: Click to 'User' Menu link");
		adminUserPage=  adminDashboardPage.clickToUserLink();
		
		log.info("View User - Step 02: Get all user from UI");
		int totalNumberAtUI = adminUserPage.getUserNumberAtUI();
			
		log.info("View User - Step 03: Get all user from DB");
		int totalNumberAtDB = adminUserPage.getUserNumberAtDB();

		log.info("View User - Step 04: Verify the users are matching");
		verifyEquals(totalNumberAtUI, totalNumberAtDB);

	}


	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}

	private WebDriver driver;
	private String adminUserName, adminPassword;
	private AdminUserPO adminUserPage;
	private String adminUrl;
	private AdminLoginPO adminLoginPage;
	private AdminDashboardPO adminDashboardPage;

}
