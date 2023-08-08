package com.nopcommerce.admin;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.admin.AdminCustomersPageObject;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.admin.AdminProductPageObject;
import reportConfig.ExtentTestManager;

public class Admin_All extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		adminEmailLogin = "admin@yourstore.com";
		adminPasswordLogin = "admin";
		productSearch = "Lenovo IdeaCentre 600 All-in-One PC";
		productSKU = "LE_IC_600";

		// customer
		customerEmail = "geni" + generateRandomNumber() + "@gmail.com";
		customerFirstName = "Geni" + generateRandomNumber();
		customerPassowrd = "123456";
		customerLastName = "Nguyen";
		customerGender = "Male";
		customerDOB = "10/11/2000";
		customerCompanyName = "Bosch" + generateRandomNumber();
		customerRoles = "Guests";
		adminComment = "Add new Customer by CS request";
		customerStatus = "Active";

		customerEmailEdit = customerEmail + "edit";
		customerFirstNameEdit = customerFirstName + "edit";
		customerLastNameEdit = customerLastName + "edit";
		customerDOBEdit = "2/2/2000";
		customerCompanyNameEdit = "Edit Bosch";
		adminCommentEdit = "Edit Customer (Guest)";
		customerCountry = "United States";
		customerProvince = "Alaska";
		customerCity = "Ho Chi Minh";
		customerAddress1 = "11 Ha";
		customerAddress2 = "11 Au co";
		customerPostalCode = "246324";
		customerPhone = "39514785";
		customerFax = "+1-907-555-1234";
		customerPhoneEdit = customerPhone + "edit";
		customerFaxEdit = customerFax + "edit";
		customerPostalCodeEdit = customerFax + "edit";
		customerCountryEdit = "Cuba";

		log.info("Pre-conditions - Step 01: Navigate to Admin Login Page");
		driver = getBrowserDriver(browserName, GlobalConstants.ADMIN_PAGE_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

		log.info("Pre-conditions - Step 02: Enter Email '" + adminEmailLogin + "' and Password '" + adminPasswordLogin + "'");
		adminLoginPage.inputToTextboxById(driver, "Email", adminEmailLogin);
		adminLoginPage.inputToTextboxById(driver, "Password", adminPasswordLogin);

		log.info("Pre-conditions - Step 03: Click to Enter button");
		adminLoginPage.clickToButtonByText(driver, "Log in");
		adminDashboardPage = PageGeneratorManager.getAdminDashboardPage(driver);

		log.info("Pre-conditions - Step 04: Verify the dashboard page load success");
		verifyTrue(adminDashboardPage.isAdminPageReady(driver));

		log.info("Pre-conditions - Step 05: Click to 'Catalog' header");
		adminDashboardPage.clickToDynamicHeaderOnSidebarByName(driver, "Catalog");

		log.info("Pre-conditions - Step 06: Verify the menu item is opened");
		verifyTrue(adminDashboardPage.isMenuItemOpenedByHeaderName(driver, "Catalog"));

	}

	@Test
	public void Admin_01_Search_With_Product_Name(Method method) {
		ExtentTestManager.startTest(method.getName(), "Admin_01_Search_With_Product_Name");

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 01: Navigate to Product Page");
		adminDashboardPage.openDynamicPageAtAdminPageOnSideBarByName(driver, "Products");
		adminProductPage = PageGeneratorManager.getAdminProductPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 02: Verify product page load success");
		verifyTrue(adminProductPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 03: Verify that navigate to product page success");
		verifyTrue(adminProductPage.isActiveLinkAtAdminPageOnSideBarByName(driver, "Products"));

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 04: Verify that navigate to product page success");
		adminProductPage.openSearchFieldSetAtAdminPage(driver);
		verifyTrue(adminProductPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 05: Enter to product name input with value: " + productSearch);
		adminProductPage.inputToTextboxById(driver, "SearchProductName", productSearch);

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 06: Click to Search button");
		adminProductPage.clickToButtonById(driver, "search-products");
		verifyTrue(adminProductPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 07: Verify 1 item displayed on data table");
		verifyEquals(adminProductPage.getItemQuantityOnResultTableByTableId(driver, "products-grid"), 1);

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 08: Verify Product Name is Displayed");
		verifyTrue(adminProductPage.isSearchDataDisplayedByTableIdAndHeader(driver, productSearch, "products-grid", "Product name"));
	}

	@Test
	public void Admin_02_Search_With_Product_Name_Category(Method method) {
		ExtentTestManager.startTest(method.getName(), "Admin_02_Search_With_Product_Name_Category");

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 01: Enter to product name input with value: " + productSearch);
		adminProductPage.inputToTextboxById(driver, "SearchProductName", productSearch);

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 02: Select The computers categories dropdown");
		adminProductPage.selectDefaultDropDownByName(driver, "SearchCategoryId", "Computers");

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 03: Uncheck 'Search in subcategories' checkbox");
		adminProductPage.unCheckToboxByLabelAtAdminPage(driver, "Search subcategories");

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 04: Click to Search button ");
		adminProductPage.clickToButtonById(driver, "search-products");
		verifyTrue(adminProductPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 05: Verify 'No data availabel in table' displayed");
		verifyEquals(adminProductPage.getItemQuantityOnResultTableByTableId(driver, "products-grid"), 1);
		verifyTrue(adminProductPage.isNoDataInTableDisplayedByTableId(driver, "products-grid"));

	}

	@Test
	public void Admin_03_Search_With_Product_Name_Category_Uncheck(Method method) {
		ExtentTestManager.startTest(method.getName(), "Admin_03_Search_With_Product_Name_Category_Uncheck");

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 01: Enter to product name input with value: " + productSearch);
		adminProductPage.inputToTextboxById(driver, "SearchProductName", productSearch);

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 02: Select The computers categories dropdown");
		adminProductPage.selectDefaultDropDownByName(driver, "SearchCategoryId", "Computers");

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 03: Check 'Search in subcategories' checkbox");
		adminProductPage.checkToCheckboxByLabelAtAdminPage(driver, "Search subcategories");

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 04: Click to Search button ");
		adminProductPage.clickToButtonById(driver, "search-products");
		verifyTrue(adminProductPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 05: Verify 1 item data displayed");
		verifyEquals(adminProductPage.getItemQuantityOnResultTableByTableId(driver, "products-grid"), 1);
		verifyTrue(adminProductPage.isSearchDataDisplayedByTableIdAndHeader(driver, productSearch, "products-grid", "Product name"));

	}

	@Test
	public void Admin_04_Search_With_Product_Name_Child_Category(Method method) {
		ExtentTestManager.startTest(method.getName(), "Admin_04_Search_With_Product_Name_Child_Category");

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 01: Enter to product name input with value: " + productSearch);
		adminProductPage.inputToTextboxById(driver, "SearchProductName", productSearch);

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 02: Select The 'Computers >> Desktops' categories dropdown");
		adminProductPage.selectDefaultDropDownByName(driver, "SearchCategoryId", "Computers >> Desktops");

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 03: Uncheck 'Search in subcategories' checkbox");
		adminProductPage.unCheckToboxByLabelAtAdminPage(driver, "Search subcategories");

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 04: Click to Search button ");
		adminProductPage.clickToButtonById(driver, "search-products");
		verifyTrue(adminProductPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 05: Verify 1 item data displayed");
		verifyEquals(adminProductPage.getItemQuantityOnResultTableByTableId(driver, "products-grid"), 1);
		verifyTrue(adminProductPage.isSearchDataDisplayedByTableIdAndHeader(driver, productSearch, "products-grid", "Product name"));

	}

	@Test
	public void Admin_05_Search_With_Product_Name_Manufactures(Method method) {
		ExtentTestManager.startTest(method.getName(), "Admin_05_Search_With_Product_Name_Manufactures");

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 01: Enter to product name input with value: " + productSearch);
		adminProductPage.inputToTextboxById(driver, "SearchProductName", productSearch);

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 02: Select The 'All' categories dropdown");
		adminProductPage.selectDefaultDropDownByName(driver, "SearchCategoryId", "All");

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 03: Uncheck 'Search in subcategories' checkbox");
		adminProductPage.unCheckToboxByLabelAtAdminPage(driver, "Search subcategories");

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 04: Select The 'App' Manufacturers dropdown");
		adminProductPage.selectDefaultDropDownByName(driver, "SearchManufacturerId", "Apple");

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 05: Click to Search button ");
		adminProductPage.clickToButtonById(driver, "search-products");
		verifyTrue(adminProductPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 06: Verify 'No data availabel in table' displayed");
		verifyEquals(adminProductPage.getItemQuantityOnResultTableByTableId(driver, "products-grid"), 1);
		verifyTrue(adminProductPage.isNoDataInTableDisplayedByTableId(driver, "products-grid"));

	}

	@Test
	public void Admin_06_Go_Product_SKU(Method method) {
		ExtentTestManager.startTest(method.getName(), "Admin_06_Go_Product_SKU");

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 01: Enter to product SKU input with value: " + productSKU);
		adminProductPage.inputToTextboxById(driver, "GoDirectlyToSku", productSKU);

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 02: Click to 'Go' button");
		adminProductPage.clickToButtonById(driver, "go-to-product-by-sku");

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 03: Verify the admin page load success");
		verifyTrue(adminProductPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 04: Verify the Product Name input has the value: " + productSearch);
		verifyEquals(adminProductPage.getValueTextboxByIdAtAdminPage(driver, "Name"), productSearch);

		ExtentTestManager.getTest().log(Status.INFO, "Search Product - Step 05: Verify the SKU input has the value: " + productSKU);
		verifyEquals(adminProductPage.getValueTextboxByIdAtAdminPage(driver, "Sku"), productSKU);
	}

	@Test
	public void Admin_07_Create_New_Customer(Method method) {
		ExtentTestManager.startTest(method.getName(), "Admin_07_Create_New_Customer");
		ExtentTestManager.getTest().log(Status.INFO, "Create Customer - Step 01: Click to Customers header on sidebar");
		adminProductPage.clickToDynamicHeaderOnSidebarByName(driver, "Customers");

		ExtentTestManager.getTest().log(Status.INFO, "Create Customer - Step 02: Verify the menu item Customers is opened");
		verifyTrue(adminProductPage.isMenuItemOpenedByHeaderName(driver, "Customers"));

		ExtentTestManager.getTest().log(Status.INFO, "Create Customer - Step 03: Navigate to 'Customers' page");
		adminProductPage.openDynamicPageAtAdminPageOnSideBarByName(driver, "Customers");
		adminCustomersPage = PageGeneratorManager.getAdminCustomerPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Create Customer - Step 04: Verify that navigate to Customers page success");
		verifyTrue(adminCustomersPage.isActiveLinkAtAdminPageOnSideBarByName(driver, "Customers"));
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Create Customer - Step 05: Click to 'Add new' button");
		adminCustomersPage.clickToAddNewlink();
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Create Customer - Step 06: Enter the input value for creating customer");
		adminCustomersPage.inputToTextboxById(driver, "Email", customerEmail);
		adminCustomersPage.inputToTextboxById(driver, "Password", customerPassowrd);
		adminCustomersPage.inputToTextboxById(driver, "FirstName", customerFirstName);
		adminCustomersPage.inputToTextboxById(driver, "LastName", customerLastName);
		adminCustomersPage.clickToRadioButtonByLabelName(driver, customerGender);
		adminCustomersPage.inputToTextboxById(driver, "DateOfBirth", customerDOB);
		adminCustomersPage.inputToTextboxById(driver, "Company", customerCompanyName);
		adminCustomersPage.checkToCheckboxByLabelAtAdminPage(driver, "Active");
		adminCustomersPage.inputToTextareaById(driver, "AdminComment", adminComment);

		adminCustomersPage.closeAllOptionOnDropDownAtAdminPageByLabel(driver, "Customer roles");
		adminCustomersPage.selectCustomerRoleDropdown(customerRoles);

		ExtentTestManager.getTest().log(Status.INFO, "Create Customer - Step 07: Click 'Save and Continue' button");
		adminCustomersPage.clickToButtonByText(driver, "Save and Continue Edit");
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Create Customer - Step 08: Verify the data has been added successfully");
		verifyTrue(adminCustomersPage.isSuccessMessageDisplayedOnTheTopByMsg(driver, "The new customer has been added successfully"));
		verifyEquals(adminCustomersPage.getTextboxValueByID(driver, "Email"), customerEmail);
		verifyEquals(adminCustomersPage.getTextboxValueByID(driver, "FirstName"), customerFirstName);
		verifyEquals(adminCustomersPage.getTextboxValueByID(driver, "LastName"), customerLastName);
		verifyTrue(adminCustomersPage.isSelectedRadioByLabel(driver, customerGender));
		verifyTrue(adminCustomersPage.isSelectedCheckboxByLabelAtAdminPage(driver, customerStatus));
		verifyTrue(adminCustomersPage.isOptionDisplayedAtDropdownByLabel(driver, "Customer roles", customerRoles));
		verifyEquals(adminCustomersPage.getTextboxValueByID(driver, "DateOfBirth"), customerDOB);
		verifyEquals(adminCustomersPage.getTextboxValueByID(driver, "Company"), customerCompanyName);
		verifyEquals(adminCustomersPage.getValueTextareaByIdAtAdminPage(driver, "AdminComment"), adminComment);

		ExtentTestManager.getTest().log(Status.INFO, "Create Customer - Step 09: Click 'Back to customer list' link");
		adminCustomersPage.clickToBackToCustomerListLink();
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Create Customer - Step 10: Enter the Customer Role textbox with value: " + customerRoles);
		adminCustomersPage.closeAllOptionOnDropDownAtAdminPageByLabel(driver, "Customer roles");
		adminCustomersPage.selectCustomerRoleDropdown(customerRoles);

		ExtentTestManager.getTest().log(Status.INFO, "Create Customer - Step 11: Click to 'Search' button");
		adminCustomersPage.clickToButtonById(driver, "search-customers");
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Create Customer - Step 12: Verify the data is displayed properly on the table");
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, String.format("%s %s", customerFirstName, customerLastName), "customers-grid", "Name"));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, customerRoles, "customers-grid", "Customer roles"));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, customerCompanyName, "customers-grid", "Company name"));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, "Guest", "customers-grid", "Email"));
		verifyTrue(adminCustomersPage.isActiveDisplayedAtCustomerTableByFullName(customerFirstName + " " + customerLastName));

	}

	@Test
	public void Admin_08_Search_Customer_With_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "Admin_08_Search_Customer_With_Email");
//		 ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 01: Click to Customers header on sidebar");
//		 adminProductPage.clickToDynamicHeaderOnSidebarByName(driver, "Customers");
		
		 ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 02: Verify the menu item Customers is opened");
		 verifyTrue(adminProductPage.isMenuItemOpenedByHeaderName(driver, "Customers"));

		ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 03: Navigate to 'Customers' page");
		adminProductPage.openDynamicPageAtAdminPageOnSideBarByName(driver, "Customers");
		adminCustomersPage = PageGeneratorManager.getAdminCustomerPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 04: Verify that navigate to Customers page success");
		verifyTrue(adminCustomersPage.isActiveLinkAtAdminPageOnSideBarByName(driver, "Customers"));
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 05: Enter the Email textbox with value: " + customerEmail);
		adminCustomersPage.inputToTextboxById(driver, "SearchEmail", customerEmail);

		ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 06: Enter the customer role textbox with value: " + customerRoles);
		adminCustomersPage.closeAllOptionOnDropDownAtAdminPageByLabel(driver, "Customer roles");
		adminCustomersPage.selectCustomerRoleDropdown(customerRoles);

		ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 07: Click to 'Search' button");
		adminCustomersPage.clickToButtonById(driver, "search-customers");
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 08: Verify the 1 item displayed on the table");
		verifyEquals(adminCustomersPage.getItemQuantityOnResultTableByTableId(driver, "customers-grid"), 1);
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, String.format("%s %s", customerFirstName, customerLastName), "customers-grid", "Name"));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, customerRoles, "customers-grid", "Customer roles"));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, customerCompanyName, "customers-grid", "Company name"));

		// Case fail for side admin Nopcommerce, expected: customer email, Actual: "Guest"
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, "Guest", "customers-grid", "Email"));
		verifyTrue(adminCustomersPage.isActiveDisplayedAtCustomerTableByFullName(customerFirstName + " " + customerLastName));
	}

	@Test
	public void Admin_09_Search_Customer_With_First_Name_Last_Name(Method method) {
		ExtentTestManager.startTest(method.getName(), "Admin_09_Search_Customer_With_First_Name_Last_Name");
//		 ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 01: Click to Customers header on sidebar");
//		 adminProductPage.clickToDynamicHeaderOnSidebarByName(driver, "Customers");
		
		 ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 02: Verify the menu item Customers is opened");
		 verifyTrue(adminProductPage.isMenuItemOpenedByHeaderName(driver, "Customers"));

		ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 03: Navigate to 'Customers' page");
		adminProductPage.openDynamicPageAtAdminPageOnSideBarByName(driver, "Customers");
		adminCustomersPage = PageGeneratorManager.getAdminCustomerPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 04: Verify that navigate to Customers page success");
		verifyTrue(adminCustomersPage.isActiveLinkAtAdminPageOnSideBarByName(driver, "Customers"));
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO,
			"Search Customer - Step 05: Enter the First name textbox with value: " + customerFirstName + " and Last Name textbox with value: " + customerLastName);
		adminCustomersPage.inputToTextboxById(driver, "SearchFirstName", customerFirstName);
		adminCustomersPage.inputToTextboxById(driver, "SearchLastName", customerLastName);

		ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 06: Enter the customer role textbox with value: " + customerRoles);
		adminCustomersPage.closeAllOptionOnDropDownAtAdminPageByLabel(driver, "Customer roles");
		adminCustomersPage.selectCustomerRoleDropdown(customerRoles);

		ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 07: Click to 'Search' button");
		adminCustomersPage.clickToButtonById(driver, "search-customers");
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 08: Verify the 1 item displayed on the table");
		verifyEquals(adminCustomersPage.getItemQuantityOnResultTableByTableId(driver, "customers-grid"), 1);
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, String.format("%s %s", customerFirstName, customerLastName), "customers-grid", "Name"));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, customerRoles, "customers-grid", "Customer roles"));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, customerCompanyName, "customers-grid", "Company name"));
		// Case fail for side admin Nopcommerce, expected: customer email, Actual: customer role
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, "Guest", "customers-grid", "Email"));
		verifyTrue(adminCustomersPage.isActiveDisplayedAtCustomerTableByFullName(customerFirstName + " " + customerLastName));
	}

	@Test
	public void Admin_10_Search_Customer_With_Company(Method method) {
		ExtentTestManager.startTest(method.getName(), "Admin_10_Search_Customer_With_Company");
//		 ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 01: Click to Customers header on sidebar");
//		 adminProductPage.clickToDynamicHeaderOnSidebarByName(driver, "Customers");
		
		 ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 02: Verify the menu item Customers is opened");
		 verifyTrue(adminProductPage.isMenuItemOpenedByHeaderName(driver, "Customers"));

		ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 03: Navigate to 'Customers' page");
		adminProductPage.openDynamicPageAtAdminPageOnSideBarByName(driver, "Customers");
		adminCustomersPage = PageGeneratorManager.getAdminCustomerPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 04: Verify that navigate to Customers page success");
		verifyTrue(adminCustomersPage.isActiveLinkAtAdminPageOnSideBarByName(driver, "Customers"));
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 05: Enter the Company textbox with value: " + customerCompanyName);
		adminCustomersPage.inputToTextboxById(driver, "SearchCompany", customerCompanyName);

		ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 06: Enter the customer role textbox with value: " + customerRoles);
		adminCustomersPage.closeAllOptionOnDropDownAtAdminPageByLabel(driver, "Customer roles");
		adminCustomersPage.selectCustomerRoleDropdown(customerRoles);

		ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 07: Click to 'Search' button");
		adminCustomersPage.clickToButtonById(driver, "search-customers");
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 08: Verify the 1 item displayed on the table");
		verifyEquals(adminCustomersPage.getItemQuantityOnResultTableByTableId(driver, "customers-grid"), 1);
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, String.format("%s %s", customerFirstName, customerLastName), "customers-grid", "Name"));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, customerRoles, "customers-grid", "Customer roles"));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, customerCompanyName, "customers-grid", "Company name"));
		// Case fail for side admin Nopcommerce, expected: customer email, Actual: customer role
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, "Guest", "customers-grid", "Email"));
		verifyTrue(adminCustomersPage.isActiveDisplayedAtCustomerTableByFullName(customerFirstName + " " + customerLastName));
	}

	@Test
	public void Admin_11_Search_Customer_With_Full_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "Admin_11_Search_Customer_With_Full_Data");
//		 ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 01: Click to Customers header on sidebar");
//		 adminProductPage.clickToDynamicHeaderOnSidebarByName(driver, "Customers");
		
		 ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 02: Verify the menu item Customers is opened");
		 verifyTrue(adminProductPage.isMenuItemOpenedByHeaderName(driver, "Customers"));

		ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 03: Navigate to 'Customers' page");
		adminProductPage.openDynamicPageAtAdminPageOnSideBarByName(driver, "Customers");
		adminCustomersPage = PageGeneratorManager.getAdminCustomerPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 04: Verify that navigate to Customers page success");
		verifyTrue(adminCustomersPage.isActiveLinkAtAdminPageOnSideBarByName(driver, "Customers"));
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 05: Enter the Fulldata textbox");
		adminCustomersPage.inputToTextboxById(driver, "SearchEmail", customerEmail);
		adminCustomersPage.inputToTextboxById(driver, "SearchFirstName", customerFirstName);
		adminCustomersPage.inputToTextboxById(driver, "SearchLastName", customerLastName);
		adminCustomersPage.inputToTextboxById(driver, "SearchCompany", customerCompanyName);

		ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 06: Enter the DOB dropdown with value: " + customerDOB);
		adminCustomersPage.selectDefaultDropDownByName(driver, "SearchMonthOfBirth", customerDOB.split("/")[0]);
		adminCustomersPage.selectDefaultDropDownByName(driver, "SearchDayOfBirth", customerDOB.split("/")[1]);

		ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 07: Enter the customer role textbox with value: " + customerRoles);
		adminCustomersPage.closeAllOptionOnDropDownAtAdminPageByLabel(driver, "Customer roles");
		adminCustomersPage.selectCustomerRoleDropdown(customerRoles);

		ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 08: Click to 'Search' button");
		adminCustomersPage.clickToButtonById(driver, "search-customers");
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Search Customer - Step 09: Verify the 1 item displayed on the table");
		verifyEquals(adminCustomersPage.getItemQuantityOnResultTableByTableId(driver, "customers-grid"), 1);
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, String.format("%s %s", customerFirstName, customerLastName), "customers-grid", "Name"));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, customerRoles, "customers-grid", "Customer roles"));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, customerCompanyName, "customers-grid", "Company name"));
		// Case fail for side admin Nopcommerce, expected: customer email, Actual: customer role
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, "Guest", "customers-grid", "Email"));
		verifyTrue(adminCustomersPage.isActiveDisplayedAtCustomerTableByFullName(customerFirstName + " " + customerLastName));
	}

	@Test
	public void Admin_12_Edit_Customer(Method method) {
		ExtentTestManager.startTest(method.getName(), "Admin_12_Edit_Customer");
//		 ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 01: Click to Customers header on sidebar");
//		 adminProductPage.clickToDynamicHeaderOnSidebarByName(driver, "Customers");
		
		 ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 02: Verify the menu item Customers is opened");
		 verifyTrue(adminProductPage.isMenuItemOpenedByHeaderName(driver, "Customers"));

		ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 03: Navigate to 'Customers' page");
		adminProductPage.openDynamicPageAtAdminPageOnSideBarByName(driver, "Customers");
		adminCustomersPage = PageGeneratorManager.getAdminCustomerPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 04: Verify that navigate to Customers page success");
		verifyTrue(adminCustomersPage.isActiveLinkAtAdminPageOnSideBarByName(driver, "Customers"));
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 05: Enter the Fulldata textbox");
		adminCustomersPage.inputToTextboxById(driver, "SearchEmail", customerEmail);
		adminCustomersPage.inputToTextboxById(driver, "SearchFirstName", customerFirstName);
		adminCustomersPage.inputToTextboxById(driver, "SearchLastName", customerLastName);
		adminCustomersPage.inputToTextboxById(driver, "SearchCompany", customerCompanyName);

		ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 06: Enter the customer role textbox with value: " + customerRoles);
		adminCustomersPage.selectDefaultDropDownByName(driver, "SearchMonthOfBirth", customerDOB.split("/")[0]);
		adminCustomersPage.selectDefaultDropDownByName(driver, "SearchDayOfBirth", customerDOB.split("/")[1]);

		ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 07: Enter the customer role textbox with value: " + customerRoles);
		adminCustomersPage.closeAllOptionOnDropDownAtAdminPageByLabel(driver, "Customer roles");
		adminCustomersPage.selectCustomerRoleDropdown(customerRoles);

		ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 08: Click to 'Search' button");
		adminCustomersPage.clickToButtonById(driver, "search-customers");
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 09: Click to 'Edit button'");
		adminCustomersPage.clickToEditCustomerButtonAtCustomerTableByFullName(customerFirstName + " " + customerLastName);
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 10: Edit Customer with data'");
		adminCustomersPage.inputToTextboxById(driver, "Email", customerEmailEdit);
		adminCustomersPage.inputToTextboxById(driver, "FirstName", customerFirstNameEdit);
		adminCustomersPage.inputToTextboxById(driver, "LastName", customerLastNameEdit);
		adminCustomersPage.inputToTextboxById(driver, "Company", customerCompanyNameEdit);
		adminCustomersPage.inputToTextboxById(driver, "DateOfBirth", customerDOBEdit);
		adminCustomersPage.inputToTextareaById(driver, "AdminComment", adminCommentEdit);

		ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 11: Click to 'Save' button");
		adminCustomersPage.clickToSaveButtonAtCustomerPage();
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 12: Verify the message for data has been updated successfully");
		verifyTrue(adminCustomersPage.isSuccessMessageDisplayedOnTheTopByMsg(driver, "The customer has been updated successfully"));

		ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 13: Search customer with edited data");
		adminCustomersPage.inputToTextboxById(driver, "SearchEmail", customerEmailEdit);
		adminCustomersPage.inputToTextboxById(driver, "SearchFirstName", customerFirstNameEdit);
		adminCustomersPage.inputToTextboxById(driver, "SearchLastName", customerLastNameEdit);
		adminCustomersPage.inputToTextboxById(driver, "SearchCompany", customerCompanyNameEdit);
		adminCustomersPage.selectDefaultDropDownByName(driver, "SearchMonthOfBirth", customerDOBEdit.split("/")[0]);
		adminCustomersPage.selectDefaultDropDownByName(driver, "SearchDayOfBirth", customerDOBEdit.split("/")[1]);
		adminCustomersPage.closeAllOptionOnDropDownAtAdminPageByLabel(driver, "Customer roles");
		adminCustomersPage.selectCustomerRoleDropdown(customerRoles);
		adminCustomersPage.clickToButtonById(driver, "search-customers");
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Edit Customer - Step 14: Verify the 1 item displayed on the table");
		verifyEquals(adminCustomersPage.getItemQuantityOnResultTableByTableId(driver, "customers-grid"), 1);
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, String.format("%s %s", customerFirstNameEdit, customerLastNameEdit), "customers-grid", "Name"));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, customerRoles, "customers-grid", "Customer roles"));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, customerCompanyNameEdit, "customers-grid", "Company name"));
		// Case fail for side admin Nopcommerce, expected: customer email, Actual: customer role
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, "Guest", "customers-grid", "Email"));
		verifyTrue(adminCustomersPage.isActiveDisplayedAtCustomerTableByFullName(customerFirstNameEdit + " " + customerLastNameEdit));
	}

	@Test
	public void Admin_13_Add_New_Address_Customer(Method method) {
		ExtentTestManager.startTest(method.getName(), "Admin_13_Add_New_Adress_Customer");
		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 01: Click to 'Edit' button with value fullname: " + customerFirstNameEdit + " " + customerLastNameEdit);
		adminCustomersPage.clickToEditCustomerButtonAtCustomerTableByFullName(customerFirstNameEdit + " " + customerLastNameEdit);

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 02: Open 'Address' card at Customer Page");
		adminCustomersPage.openDynamicCardByIdAtAdminPage(driver, "customer-address");
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 03: Click to 'Add address' button");
		adminCustomersPage.clickToButtonByText(driver, "Add new address");
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 04: Enter the all textbox for adding address");
		adminCustomersPage.inputToTextboxById(driver, "Address_FirstName", customerFirstName);
		adminCustomersPage.inputToTextboxById(driver, "Address_LastName", customerLastName);
		adminCustomersPage.inputToTextboxById(driver, "Address_Email", customerEmail);
		adminCustomersPage.inputToTextboxById(driver, "Address_Company", customerCompanyName);
		adminCustomersPage.selectDefaultDropDownByName(driver, "Address.CountryId", customerCountry);
		adminCustomersPage.selectDefaultDropDownByName(driver, "Address.StateProvinceId", customerProvince);
		adminCustomersPage.inputToTextboxById(driver, "Address_City", customerCity);
		adminCustomersPage.inputToTextboxById(driver, "Address_Address1", customerAddress1);
		adminCustomersPage.inputToTextboxById(driver, "Address_Address2", customerAddress2);
		adminCustomersPage.inputToTextboxById(driver, "Address_ZipPostalCode", customerPostalCode);
		adminCustomersPage.inputToTextboxById(driver, "Address_PhoneNumber", customerPhone);
		adminCustomersPage.inputToTextboxById(driver, "Address_FaxNumber", customerFax);

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 05: Click to 'Save' button");
		adminCustomersPage.clickToButtonByText(driver, "Save");
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 06: Verify the data has been added successfully messsage");
		verifyTrue(adminCustomersPage.isSuccessMessageDisplayedOnTheTopByMsg(driver, "The new address has been added successfully"));

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 07: Click 'Back to customer details' link");
		adminCustomersPage.clickToBackToCustomerDetailsLink();
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));
		
		adminCustomersPage.openDynamicCardByIdAtAdminPage(driver, "customer-address");
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 08: Verify the data displayed on the table properly");
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, customerFirstName, "customer-addresses-grid", "First name"));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, customerLastName, "customer-addresses-grid", "Last name"));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, customerEmail, "customer-addresses-grid", "Email"));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, customerPhone, "customer-addresses-grid", "Phone number"));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, customerFax, "customer-addresses-grid", "Fax number"));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedAtAddressHeaderByEmail(customerEmail, customerAddress1));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedAtAddressHeaderByEmail(customerEmail, customerAddress2));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedAtAddressHeaderByEmail(customerEmail, customerCountry));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedAtAddressHeaderByEmail(customerEmail, customerCity));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedAtAddressHeaderByEmail(customerEmail, customerPostalCode));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedAtAddressHeaderByEmail(customerEmail, customerProvince));
	}

	@Test
	public void Admin_14_Edit_Address(Method method) {
		ExtentTestManager.startTest(method.getName(), "Admin_14_Edit_Address");

//		 ExtentTestManager.getTest().log(Status.INFO, "Edit Address - Step 01: Click to Customers header on sidebar");
//		 adminProductPage.clickToDynamicHeaderOnSidebarByName(driver, "Customers");
		
		 ExtentTestManager.getTest().log(Status.INFO, "Edit Address - Step 02: Verify the menu item Customers is opened");
		 verifyTrue(adminProductPage.isMenuItemOpenedByHeaderName(driver, "Customers"));

		
		ExtentTestManager.getTest().log(Status.INFO, "Edit Address - Step 03: Navigate to 'Customers' page");
		adminProductPage.openDynamicPageAtAdminPageOnSideBarByName(driver, "Customers");
		adminCustomersPage = PageGeneratorManager.getAdminCustomerPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Edit Address - Step 04: Verify that navigate to Customers page success");
		verifyTrue(adminCustomersPage.isActiveLinkAtAdminPageOnSideBarByName(driver, "Customers"));
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Edit Address - Step 05: Enter the Fulldata textbox");
		adminCustomersPage.inputToTextboxById(driver, "SearchEmail", customerEmailEdit);
		adminCustomersPage.inputToTextboxById(driver, "SearchFirstName", customerFirstNameEdit);
		adminCustomersPage.inputToTextboxById(driver, "SearchLastName", customerLastNameEdit);
		adminCustomersPage.inputToTextboxById(driver, "SearchCompany", customerCompanyNameEdit);

		ExtentTestManager.getTest().log(Status.INFO, "Edit Address - Step 06: Enter the customer role textbox with value: " + customerRoles);
		adminCustomersPage.selectDefaultDropDownByName(driver, "SearchMonthOfBirth", customerDOBEdit.split("/")[0]);
		adminCustomersPage.selectDefaultDropDownByName(driver, "SearchDayOfBirth", customerDOBEdit.split("/")[1]);
		adminCustomersPage.closeAllOptionOnDropDownAtAdminPageByLabel(driver, "Customer roles");

		ExtentTestManager.getTest().log(Status.INFO, "Edit Address - Step 07: Click to 'Search' button");
		adminCustomersPage.clickToButtonById(driver, "search-customers");
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Edit Address - Step 09: Click to 'Edit' customer button");
		adminCustomersPage.clickToEditCustomerButtonAtCustomerTableByFullName(customerFirstNameEdit + " " + customerLastNameEdit);
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Edit Address - Step 10: Click to 'Edit' address button");
		adminCustomersPage.clickToEditAddressButtonAtAddressTableByEmail(customerEmail);
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Edit Address - Step 12: Enter the textbox with edit data");
		adminCustomersPage.inputToTextboxById(driver, "Address_Email", customerEmailEdit);
		adminCustomersPage.inputToTextboxById(driver, "Address_FirstName", customerFirstNameEdit);
		adminCustomersPage.inputToTextboxById(driver, "Address_LastName", customerLastNameEdit);
		adminCustomersPage.inputToTextboxById(driver, "Address_Company", customerCompanyNameEdit);
		adminCustomersPage.inputToTextboxById(driver, "Address_PhoneNumber", customerPhoneEdit);
		adminCustomersPage.inputToTextboxById(driver, "Address_FaxNumber", customerFaxEdit);
		adminCustomersPage.inputToTextboxById(driver, "Address_ZipPostalCode", customerPostalCodeEdit);
		adminCustomersPage.selectDefaultDropDownByName(driver, "Address.CountryId", customerCountryEdit);

		ExtentTestManager.getTest().log(Status.INFO, "Edit Address - Step 13: Click to 'Save' button");
		adminCustomersPage.clickToButtonByText(driver, "Save");
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Edit Address - Step 14: Verify the messaged displayed");
		verifyTrue(adminCustomersPage.isSuccessMessageDisplayedOnTheTopByMsg(driver, "The address has been updated successfully"));

		ExtentTestManager.getTest().log(Status.INFO, "Edit Address - Step 15: Click 'Back to customer details' link");
		adminCustomersPage.clickToBackToCustomerDetailsLink();
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Edit Address - Step 16: Verify the editted address data display properly");
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, customerFirstNameEdit, "customer-addresses-grid", "First name"));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, customerLastNameEdit, "customer-addresses-grid", "Last name"));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, customerEmailEdit, "customer-addresses-grid", "Email"));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, customerPhoneEdit, "customer-addresses-grid", "Phone number"));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedByTableIdAndHeader(driver, customerFaxEdit, "customer-addresses-grid", "Fax number"));
		verifyTrue(adminCustomersPage.isSearchDataDisplayedAtAddressHeaderByEmail(customerEmailEdit, customerCountryEdit));
	}

	@Test
	public void Admin_15_Delete_Address(Method method) {
		ExtentTestManager.startTest(method.getName(), "Admin_15_Delete_Address");
		
//		 ExtentTestManager.getTest().log(Status.INFO, "Delete Address - Step 01: Click to Customers header on sidebar");
//		 adminProductPage.clickToDynamicHeaderOnSidebarByName(driver, "Customers");
		
		 ExtentTestManager.getTest().log(Status.INFO, "Delete Address - Step 02: Verify the menu item Customers is opened");
		 verifyTrue(adminProductPage.isMenuItemOpenedByHeaderName(driver, "Customers"));

		ExtentTestManager.getTest().log(Status.INFO, "Delete Address - Step 03: Navigate to 'Customers' page");
		adminProductPage.openDynamicPageAtAdminPageOnSideBarByName(driver, "Customers");
		adminCustomersPage = PageGeneratorManager.getAdminCustomerPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Delete Address - Step 04: Verify that navigate to Customers page success");
		verifyTrue(adminCustomersPage.isActiveLinkAtAdminPageOnSideBarByName(driver, "Customers"));
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Delete Address - Step 05: Enter the Fulldata textbox");
		adminCustomersPage.inputToTextboxById(driver, "SearchEmail", customerEmailEdit);
		adminCustomersPage.inputToTextboxById(driver, "SearchFirstName", customerFirstNameEdit);
		adminCustomersPage.inputToTextboxById(driver, "SearchLastName", customerLastNameEdit);
		adminCustomersPage.inputToTextboxById(driver, "SearchCompany", customerCompanyNameEdit);

		ExtentTestManager.getTest().log(Status.INFO, "Delete Address - Step 06: Enter the customer role textbox with value: " + customerRoles);
		adminCustomersPage.selectDefaultDropDownByName(driver, "SearchMonthOfBirth", customerDOBEdit.split("/")[0]);
		adminCustomersPage.selectDefaultDropDownByName(driver, "SearchDayOfBirth", customerDOBEdit.split("/")[1]);
		adminCustomersPage.closeAllOptionOnDropDownAtAdminPageByLabel(driver, "Customer roles");

		ExtentTestManager.getTest().log(Status.INFO, "Delete Address - Step 07: Click to 'Search' button");
		adminCustomersPage.clickToButtonById(driver, "search-customers");
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));
		
		ExtentTestManager.getTest().log(Status.INFO, "Delete Address - Step 07: Click to 'Edit' customer button");
		adminCustomersPage.clickToEditCustomerButtonAtCustomerTableByFullName(customerFirstNameEdit + " " + customerLastNameEdit);
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));


		ExtentTestManager.getTest().log(Status.INFO, "Delete Address - Step 09: Click to 'Delete'address button");
		adminCustomersPage.clickToDeleteAddressButtonAtAddressTableByEmail(customerEmailEdit);

		ExtentTestManager.getTest().log(Status.INFO, "Delete Address - Step 10: Accept alert delete");
		adminCustomersPage.acceptDeleteAlert();
		verifyTrue(adminCustomersPage.isAdminPageReady(driver));

		ExtentTestManager.getTest().log(Status.INFO, "Delete Address - Step 11: Verify 'No data available' displayed on table");
		verifyEquals(adminCustomersPage.getItemQuantityOnResultTableByTableId(driver, "activitylog-grid"), 1);
		verifyTrue(adminCustomersPage.isNoDataInTableDisplayedByTableId(driver, "activitylog-grid"));
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		 closeBrowserDriver();
	}

	private WebDriver driver;
	private String adminEmailLogin, adminPasswordLogin, productSearch, productSKU;
	private String customerEmail, customerPassowrd, customerFirstName, customerLastName, customerGender, customerDOB, customerCompanyName, customerRoles, adminComment, customerStatus;
	private String customerEmailEdit, customerFirstNameEdit, customerLastNameEdit, customerDOBEdit, customerCompanyNameEdit, adminCommentEdit;
	private String customerCountry, customerProvince, customerCity, customerAddress1, customerAddress2, customerPostalCode, customerPhone, customerFax, customerPhoneEdit, customerFaxEdit,
		customerPostalCodeEdit, customerCountryEdit;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	private AdminProductPageObject adminProductPage;
	private AdminCustomersPageObject adminCustomersPage;
}
