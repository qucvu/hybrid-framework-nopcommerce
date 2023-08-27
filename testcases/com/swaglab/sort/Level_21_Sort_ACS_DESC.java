package com.swaglab.sort;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.swaglab.LoginPO;
import pageObjects.swaglab.PageGeneratorManager;
import pageObjects.swaglab.ProductPO;

public class Level_21_Sort_ACS_DESC extends BaseTest {

	@Parameters({ "browser", "appUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		userName = "standard_user";
		password = "secret_sauce";
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.enterToUsernameTextbox(userName);
		loginPage.enterToPasswordTextbox(password);
		productPage = loginPage.clickToLoginButton();
	}

	@Test
	public void Sort_01_Name() {
		// Ascending
		productPage.selectItemInSortDropdown("Name (A to Z)");
		verifyTrue(productPage.isProductNameSortAscending());
		// Descending
		productPage.selectItemInSortDropdown("Name (Z to A)");
		verifyTrue(productPage.isProductNameSortDescending());

	}

	@Test
	public void Sort_01_Price() {
		// Ascending
		productPage.selectItemInSortDropdown("Price (low to high)");
		verifyTrue(productPage.isProductPriceSortAscending());

		// Descending
		productPage.selectItemInSortDropdown("Price (high to low)");
		verifyTrue(productPage.isProductPriceSortDescending());


	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}

	private WebDriver driver;
	private String userName, password;
	private LoginPO loginPage;
	private ProductPO productPage;
}
