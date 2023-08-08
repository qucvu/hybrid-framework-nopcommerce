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
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserSearchPageObject;
import reportConfig.ExtentTestManager;

public class Search_Advanced_Search extends BaseTest {

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
		
		log.info("Pre-condition - Step 03: Scroll to bottom page");
		homePage.scrollToBottomPage(driver);
		
		log.info("Pre-condition - Step 04: Navigate to 'Search' Page");
		homePage.scrollToBottomPage(driver);
		homePage.openDynamicLinkOnFooterBlockByPageName(driver, "Search");
		searchPage = PageGeneratorManager.getSearchPage(driver);
		
		emptyData = "";
		notFoundData = "Macbook Pro 2050";
		relativeProduct = "Lenovo";
		absoluteProduct = "Thinkpad X1 Carbon";
		productAdvancedSearch = "Apple Macbook Pro";
		

	}

	@Test
	public void Search_01_Empty_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search_01_Empty_Data");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 01: Enter empty data to search input");
		searchPage.inputToTextboxById(driver, "q", emptyData);
		
		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 02: Press 'Enter' to search Textbox");
		searchPage.pressEnterToSearchInputOnSearchFieldset();
		
		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 03: Verify error message displayed");
		verifyEquals(searchPage.getErrorMessageAtSearchResult(), "Search term minimum length is 3 characters");
	}

	@Test
	public void Search_02_Not_Found_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search_02_Not_Found_Data");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 01: Enter '"+ notFoundData  + "' to search input");
		searchPage.inputToTextboxById(driver, "q", notFoundData);
		
		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 02: Press 'Enter' to search Textbox");
		searchPage.pressEnterToSearchInputOnSearchFieldset();
		
		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 03: Verify error message displayed");
		verifyEquals(searchPage.getErrorMessageAtSearchResult(), "No products were found that matched your criteria.");

	
	}

	@Test
	public void Search_03_Relative_Product(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search_03_Relative_Product");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 01: Enter '"+ relativeProduct  + "' to search input");
		searchPage.inputToTextboxById(driver, "q", relativeProduct);
		
		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 02: Press 'Enter' to search Textbox");
		searchPage.pressEnterToSearchInputOnSearchFieldset();
		
		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 03: Verify 2 product displayed");
		verifyEquals(searchPage.getProductQuantityAtSearchResult(), 2);
		verifyTrue(searchPage.isDynamicProductItemDisplayedByProductTitle("Lenovo IdeaCentre 600 All-in-One PC"));
		verifyTrue(searchPage.isDynamicProductItemDisplayedByProductTitle("Lenovo Thinkpad X1 Carbon Laptop"));
	}

	@Test
	public void Search_04_Absolute_Product(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search_04_Absolute_Product");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 01: Enter '"+ absoluteProduct  + "' to search input");
		searchPage.inputToTextboxById(driver, "q", absoluteProduct);
		
		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 02: Press 'Enter' to search Textbox");
		searchPage.pressEnterToSearchInputOnSearchFieldset();
		
		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 03: Verify 1 product displayed");
		verifyEquals(searchPage.getProductQuantityAtSearchResult(), 1);
		verifyTrue(searchPage.isDynamicProductItemDisplayedByProductTitle("Lenovo Thinkpad X1 Carbon Laptop"));

	}
	
	@Test
	public void Search_05_Advanced_Search_With_Parent_Categories(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search_05_Advanced_Search_With_Parent_Categories");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 01: Enter '"+ productAdvancedSearch  + "' to search input");
		searchPage.inputToTextboxById(driver, "q", productAdvancedSearch);
		
		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 02: Click to 'Advanced search' checkbox");
		searchPage.checkToCheckboxByLabelAtSearchFieldset("Advanced search");

		
		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 03: Select 'Computer' categories at Category input");
		searchPage.selectDefaultDropDownByName(driver, "cid", "Computers");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 04: Uncheck the 'Automatically search sub categories' checkbox");
		searchPage.unCheckToCheckboxByLabelAtSearchFieldset("Automatically search sub categories");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 05: Press 'Enter' to search Textbox");
		searchPage.pressEnterToSearchInputOnSearchFieldset();
		
		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 06: Verify error message displayed");
		verifyEquals(searchPage.getErrorMessageAtSearchResult(), "No products were found that matched your criteria.");

	}
	
	@Test
	public void Search_06_Advanced_Search_With_Sub_Categories(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search_06_Advanced_Search_With_Sub_Categories");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 01: Enter '"+ productAdvancedSearch  + "' to search input");
		searchPage.inputToTextboxById(driver, "q", productAdvancedSearch);
		
		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 02: Click to 'Advanced search' checkbox");
		searchPage.checkToCheckboxByLabelAtSearchFieldset("Advanced search");

		
		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 03: Select 'Computer' categories at Category input");
		searchPage.selectDefaultDropDownByName(driver, "cid", "Computers");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 04: Check the 'Automatically search sub categories' checkbox");
		searchPage.checkToCheckboxByLabelAtSearchFieldset("Automatically search sub categories");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 05: Press 'Enter' to search Textbox");
		searchPage.pressEnterToSearchInputOnSearchFieldset();
		
		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 06: Verify 1 product displayed ");
		verifyEquals(searchPage.getProductQuantityAtSearchResult(), 1);
		verifyTrue(searchPage.isDynamicProductItemDisplayedByProductTitle("Apple MacBook Pro 13-inch"));
	}
	
	@Test
	public void Search_07_Advanced_Search_With_Incorrect_Manufacturer(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search_07_Advanced_Search_With_Incorrect_Manufacturer");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 01: Enter '"+ productAdvancedSearch  + "' to search input");
		searchPage.inputToTextboxById(driver, "q", productAdvancedSearch);
		
		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 02: Click to 'Advanced search' checkbox");
		searchPage.checkToCheckboxByLabelAtSearchFieldset("Advanced search");

		
		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 03: Select 'Computer' categories at Category input");
		searchPage.selectDefaultDropDownByName(driver, "cid", "Computers");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 04: Check the 'Automatically search sub categories' checkbox");
		searchPage.checkToCheckboxByLabelAtSearchFieldset("Automatically search sub categories");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 05: Select 'HP' Manufacture  at Manufacture input");
		searchPage.selectDefaultDropDownByName(driver, "mid", "HP");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 06: Press 'Enter' to search Textbox");
		searchPage.pressEnterToSearchInputOnSearchFieldset();
		
		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 07: Verify error message displayed");
		verifyEquals(searchPage.getErrorMessageAtSearchResult(), "No products were found that matched your criteria.");
	}

	@Test
	public void Search_08_Advanced_Search_With_Correct_Manufacturer(Method method) {
		ExtentTestManager.startTest(method.getName(), "Search_08_Advanced_Search_With_Correct_Manufacturer");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 01: Enter '"+ productAdvancedSearch  + "' to search input");
		searchPage.inputToTextboxById(driver, "q", productAdvancedSearch);
		
		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 02: Click to 'Advanced search' checkbox");
		searchPage.checkToCheckboxByLabelAtSearchFieldset("Advanced search");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 03: Select 'Computer' categories at Category input");
		searchPage.selectDefaultDropDownByName(driver, "cid", "Computers");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 04: Check the 'Automatically search sub categories' checkbox");
		searchPage.checkToCheckboxByLabelAtSearchFieldset("Automatically search sub categories");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 05: Select 'Apple' Manufacture  at Manufacture input");
		searchPage.selectDefaultDropDownByName(driver, "mid", "Apple");

		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 06: Press 'Enter' to search Textbox");
		searchPage.pressEnterToSearchInputOnSearchFieldset();
		
		ExtentTestManager.getTest().log(Status.INFO, "Search - Step 06: Verify 1 product displayed ");
		verifyEquals(searchPage.getProductQuantityAtSearchResult(), 1);
		verifyTrue(searchPage.isDynamicProductItemDisplayedByProductTitle("Apple MacBook Pro 13-inch"));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		 closeBrowserDriver();
	}

	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserSearchPageObject searchPage;
	private String emptyData, notFoundData, relativeProduct, absoluteProduct, productAdvancedSearch;  
}
