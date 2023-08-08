package com.nopcommerce.pratice;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_08_Switch_Role extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		firstName = "Geni";
		lastName = "Nguyen";
		userEmailAddress = "afc" + generateRandomNumber() + "@acb.com";
		userPassword = "123456";
		adminEmailAddress = "admin@yourstore.com";
		adminPassword = "admin";

		driver.get(getDomainUrl("production"));
		// driver.get(GlobalConstants.PORTAL_PAGE_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

	}

	@Test
	public void Role_01_Register_Success() {
		userRegisterPage = userHomePage.openRegisterPage();

		userRegisterPage.inputToFirstNameTextBox(firstName);
		userRegisterPage.inputToLastNameTextBox(lastName);
		userRegisterPage.inputToEmailTextBox(userEmailAddress);
		userRegisterPage.inputToPassowrdTextBox(userPassword);
		userRegisterPage.inputToConfirmPasswordTextBox(userPassword);

		userRegisterPage.clickToRegisterButton();

		Assert.assertEquals(userRegisterPage.getSuccessRegisterMessage(), "Your registration completed");
		userHomePage = userRegisterPage.clickToContinueButton();
	}

	@Test
	public void Role_02_User_To_Admin() {
		userLoginPage = userHomePage.openLoginPage();

		// login as User Role
		userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);
		// Assert.assertEquals(loginPage.getTitle(driver), "nopCommerce demo store");
		Assert.assertTrue(userHomePage.isMyAccountDisplayed());

		// vào trang customer infor
		userCustomerInfoPage = userHomePage.openMyAccountPage(driver);

		// click logout
		userHomePage = userCustomerInfoPage.clickToLogoutLinkAtUserPage(driver);

		// qua trang login của admin
		userHomePage.openPageUrl(driver, GlobalConstants.ADMIN_PAGE_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword);
		Assert.assertTrue(adminDashboardPage.isDashboardTitleDisplayed());

		// logout bên trang admin
		adminLoginPage = adminDashboardPage.clickToLogoutLinkAtAdminPage(driver);

	}

	// @Test
	public void Role_03_Admin_To_User() {
		adminLoginPage.openPageUrl(driver, GlobalConstants.PORTAL_PAGE_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		userLoginPage = userHomePage.openLoginPage();

	}

	public int generateRandomNumber() {
		return new Random().nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private UserLoginPageObject userLoginPage;
	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
	private UserCustomerInfoPageObject userCustomerInfoPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	private String firstName, lastName, userPassword, userEmailAddress, adminEmailAddress, adminPassword;

}
