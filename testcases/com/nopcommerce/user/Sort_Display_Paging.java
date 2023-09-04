package com.nopcommerce.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.nopcommerce.common.Common_01_Register_Cookie;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserCategoryPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import reportConfig.ExtentTestManager;

public class Sort_Display_Paging extends BaseTest {
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

		log.info("Pre-condition - Step 04: Open submenu 'Notebooks' at top menu 'Computer'");
		homePage.hoverDynamicProductCategoryOnTopMenuByName(driver, "Computers");
		categoryPage = homePage.openCategoryPageOnTopMenuByCategoryName("Notebooks ");

		log.info("Pre-condition - Step 05: Verify 'Notebooks' is displayed at Title and Breadcrumb");
		verifyEquals(categoryPage.getCurrentTextAtBreadcrumbMenu(), "Notebooks");
		verifyEquals(categoryPage.getCategoryPageTitle(), "Notebooks");
	}

	@Test
	public void Sort_01_Name_A_To_Z(Method method) {
		ExtentTestManager.startTest(method.getName(), "Sort_01_Name_A_To_Z");
		ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 01: Select dropdown Sort item with value 'Name: A to Z'");
		categoryPage.selectItemToSortDropdown("Name: A to Z");

		ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 02: Verify product name has been sorted properly");
		verifyTrue(categoryPage.isProductNameSortAscending());
	}

	@Test
	public void Sort_02_Name_Z_To_A(Method method) {
		ExtentTestManager.startTest(method.getName(), "Sort_02_Name_Z_To_A");
		ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 01: Select dropdown Sort item with value 'Name: Z to A'");
		categoryPage.selectItemToSortDropdown("Name: Z to A");

		ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 02: Verify product name has been sorted properly");
		verifyTrue(categoryPage.isProductNameSortDescending());
	}

	@Test
	public void Sort_03_Price_Low_To_High(Method method) {
		ExtentTestManager.startTest(method.getName(), "Sort_03_Price_Low_To_High");
		ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 01: Select dropdown Sort item with value 'Price: Low to High'");
		categoryPage.selectItemToSortDropdown("Price: Low to High");

		ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 02: Verify product price has been sorted properly");
		verifyTrue(categoryPage.isProductPriceSortAscending());
	}

	@Test
	public void Sort_04_Price_High_To_Low(Method method) {
		ExtentTestManager.startTest(method.getName(), "Sort_04_Price_High_To_Low");
		ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 01: Select dropdown Sort item with value 'Price: High to Low'");
		categoryPage.selectItemToSortDropdown("Price: High to Low");

		ExtentTestManager.getTest().log(Status.INFO, "Sort - Step 02: Verify product price has been sorted properly");
		verifyTrue(categoryPage.isProductPriceSortDescending());
	}

	@Test
	public void Sort_05_Paging_Display_3_Product(Method method) {
		ExtentTestManager.startTest(method.getName(), "Sort_05_Paging_Display_3_Product");
		ExtentTestManager.getTest().log(Status.INFO, "Display-Paging - Step 01: Select dropdown Sort item with value '3'");
		categoryPage.selectItemInDisplayDropdown("3");

		ExtentTestManager.getTest().log(Status.INFO, "Display-Paging - Step 02: Verify less or equal 3 items displayed'");
		verifyTrue(categoryPage.isLessOrEquaProductItemlItemsDisplayedByQuantity("3"));

		ExtentTestManager.getTest().log(Status.INFO, "Display-Paging - Step 03: Verify the next icon is displayed");
		verifyTrue(categoryPage.isNextIconDisplayedAtProductWrapper());

		ExtentTestManager.getTest().log(Status.INFO, "Display-Paging - Step 04: Open Page number with value: 2'");
		categoryPage.openPageNumberAtProductWrapper("2");

		ExtentTestManager.getTest().log(Status.INFO, "Display-Paging - Step 05: Verify the the active Page number 2");
		verifyTrue(categoryPage.isPageNumberActive("2"));

		ExtentTestManager.getTest().log(Status.INFO, "Display-Paging - Step 06: Verify the previous icon is displayed");
		verifyTrue(categoryPage.isPreviousIconDisplayedAtProductWrapper());
	}

	@Test
	public void Sort_06_Paging_Display_6_Product(Method method) {
		ExtentTestManager.startTest(method.getName(), "Sort_06_Paging_Display_6_Product");
		ExtentTestManager.getTest().log(Status.INFO, "Display-Paging - Step 01: Select dropdown Sort item with value '6'");
		categoryPage.selectItemInDisplayDropdown("6");

		ExtentTestManager.getTest().log(Status.INFO, "Display-Paging - Step 02: Verify less or equal 6 items displayed'");
		verifyTrue(categoryPage.isLessOrEquaProductItemlItemsDisplayedByQuantity("6"));

		ExtentTestManager.getTest().log(Status.INFO, "Display-Paging - Step 03: Verify less or equal 6 items displayed'");
		verifyTrue(categoryPage.isLessOrEquaProductItemlItemsDisplayedByQuantity("6"));

		ExtentTestManager.getTest().log(Status.INFO, "Display-Paging - Step 04: Verify Paging item is not displayed");
		verifyTrue(categoryPage.isPagingUnDisplayed());

	}

	@Test
	public void Sort_07_Paging_Display_9_Product(Method method) {
		ExtentTestManager.startTest(method.getName(), "Sort_07_Paging_Display_9_Product");
		ExtentTestManager.getTest().log(Status.INFO, "Display-Paging - Step 01: Select dropdown Sort item with value '9'");
		categoryPage.selectItemInDisplayDropdown("9");

		ExtentTestManager.getTest().log(Status.INFO, "Display-Paging - Step 02: Verify less or equal 9 items displayed'");
		verifyTrue(categoryPage.isLessOrEquaProductItemlItemsDisplayedByQuantity("9"));

		ExtentTestManager.getTest().log(Status.INFO, "Display-Paging - Step 03: Verify Paging item is not displayed");
		verifyTrue(categoryPage.isPagingUnDisplayed());
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}

	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserCategoryPageObject categoryPage;
}
