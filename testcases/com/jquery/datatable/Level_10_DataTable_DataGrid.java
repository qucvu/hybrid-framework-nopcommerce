package com.jquery.datatable;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.HomePageObject;
import pageObjects.jQuery.PageGeneratorManager;
import pageUIs.jQuery.HomePageUI;

public class Level_10_DataTable_DataGrid extends BaseTest {
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {

		driver = getBrowserDriver(browserName, url);
		homePage = PageGeneratorManager.getJQueryHomePage(driver);

	}

	 @Test
	public void Table_01() {
		homePage.openPagingByPageNumber("1");
		Assert.assertTrue(homePage.isPageNumberActive("1"));

		homePage.openPagingByPageNumber("3");
		Assert.assertTrue(homePage.isPageNumberActive("3"));

		homePage.openPagingByPageNumber("5");
		Assert.assertTrue(homePage.isPageNumberActive("5"));

		homePage.openPagingByPageNumber("10");
		Assert.assertTrue(homePage.isPageNumberActive("10"));

		homePage.openPagingByPageNumber("15");
		Assert.assertTrue(homePage.isPageNumberActive("15"));

		homePage.openPagingByPageNumber("24");
		Assert.assertTrue(homePage.isPageNumberActive("24"));
		System.out.println(homePage.getElementText(driver, HomePageUI.ALL_ROW_EACH_PAGE));

	}

//	 @Test
	public void Table_02() {
		homePage.refreshCurrentPage(driver);

		homePage.enterToHeaderByLabel("Females", "253999");
		homePage.enterToHeaderByLabel("Country", "Cameroon");
		homePage.enterToHeaderByLabel("Males", "256529");
		homePage.enterToHeaderByLabel("Total", "510528");

	}

//	@Test
	public void Table_03() {
		 homePage.refreshCurrentPage(driver);

		List<String> allCountryData = homePage.getValueEachRowAtAllPage();
		for (String value : allCountryData) {
			System.out.println(value);
		}
	}

	public int generateRandomNumber() {
		return new Random().nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private HomePageObject homePage;
}
