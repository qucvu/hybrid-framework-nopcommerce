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
import pageObjects.nopCommerce.user.UserProductDetailPageObject;
import pageObjects.nopCommerce.user.UserProductReviewPageObject;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserChangePasswordPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import reportConfig.ExtentTestManager;

public class MyAccount extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName, Method method) {
		driver = getBrowserDriver(browserName, GlobalConstants.PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		showBrowserConsoleLogs(driver);
		log.info("Pre-condition - Step 01: Set cookie and reload page");
		homePage.setCookies(driver, Common_01_Register_Cookie.loggedCookies);
		homePage.refreshCurrentPage(driver);

		log.info("Pre-condition - Step 02: Verify the 'My Account' Link is displayed");
		verifyTrue(homePage.isMyAccountDisplayed());
		oldPassword = Common_01_Register_Cookie.password;

		gender = "Female";
		firstName = "Automation";
		lastName = "FC";
		dobDay = "1";
		dobMonth = "January";
		dobYear = "1999";
		email = "automationfc.vn" + generateRandomNumber() + "@gmail.com";
		companyName = "Automation FC";
		country = "Viet Nam";
		provice = "Other";
		city = "Da Nang";
		address1 = "123/123 Le Lai";
		address2 = "245/14 Hai Phong";
		postalCode = "555000";
		phoneNumber = "0123123123";
		faxNumber = "09879879";
		newPassword = "123456789";

		// Review Product
		productReview = "Build your own computer";
		reviewTitle = "Highly Recommended";
		reviewRate = "Not good";
		reviewContent = "Good product to use <3";

	}

	@Test
	public void My_Account_01_Customer_Info(Method method) {
		ExtentTestManager.startTest(method.getName(), "My_Account_01_Customer_Info");
		ExtentTestManager.getTest().log(Status.INFO, "Update Customer Info - Step 01: Navigate to 'Customer info' page");
		customerInfoPage = homePage.openMyAccountPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Update Customer Info - Step 02: Update the customer info");
		customerInfoPage.inputToTextboxById(driver, "FirstName", firstName);
		customerInfoPage.inputToTextboxById(driver, "LastName", lastName);
		customerInfoPage.clickToRadioButtonByLabelName(driver, gender);
		customerInfoPage.selectDefaultDropDownByName(driver, "DateOfBirthDay", dobDay);
		customerInfoPage.selectDefaultDropDownByName(driver, "DateOfBirthMonth", dobMonth);
		customerInfoPage.selectDefaultDropDownByName(driver, "DateOfBirthYear", dobYear);
		customerInfoPage.inputToTextboxById(driver, "Email", email);
		customerInfoPage.inputToTextboxById(driver, "Company", companyName);

		ExtentTestManager.getTest().log(Status.INFO, "Update Customer Info - Step 04: Click to 'Save' button");
		customerInfoPage.clickToButtonByText(driver, "Save");

		ExtentTestManager.getTest().log(Status.INFO, "Update Customer Info - Step 05: Verify the message 'Update Customer info successfully'");
		verifyTrue(customerInfoPage.isMessageDispalyedOnBarNotificationByMessage(driver, "The customer info has been updated successfully"));

		ExtentTestManager.getTest().log(Status.INFO, "Update Customer Info - Step 06: Verify the customer info update successfully");
		verifyEquals(customerInfoPage.getTextboxValueByID(driver, "FirstName"), firstName);
		verifyEquals(customerInfoPage.getTextboxValueByID(driver, "LastName"), lastName);
		verifyEquals(customerInfoPage.getTextboxValueByID(driver, "Email"), email);
		verifyEquals(customerInfoPage.getTextboxValueByID(driver, "Company"), companyName);
		verifyTrue(customerInfoPage.isSelectedRadioByLabel(driver, gender));
		verifyEquals(customerInfoPage.getSelectItemDropdownByName(driver, "DateOfBirthDay"), dobDay);
		verifyEquals(customerInfoPage.getSelectItemDropdownByName(driver, "DateOfBirthMonth"), dobMonth);
		verifyEquals(customerInfoPage.getSelectItemDropdownByName(driver, "DateOfBirthYear"), dobYear);
	}

	@Test
	public void My_Account_02_Customer_Address(Method method) {
		ExtentTestManager.startTest(method.getName(), "My_Account_02_Customer_Address");

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 01: Navigate to 'Address' page");
		addressPage = (UserAddressPageObject) customerInfoPage.openDynamicPageAtMyAccountByPageName(driver, "Addresses");

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 02: Click to 'Add New' button");
		addressPage.clickToButtonByText(driver, "Add new");

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 03: Add the address information");
		addressPage.inputToTextboxById(driver, "Address_FirstName", firstName);
		addressPage.inputToTextboxById(driver, "Address_LastName", lastName);
		addressPage.inputToTextboxById(driver, "Address_Email", email);
		addressPage.inputToTextboxById(driver, "Address_Company", companyName);
		addressPage.inputToTextboxById(driver, "Address_City", city);
		addressPage.inputToTextboxById(driver, "Address_Address1", address1);
		addressPage.inputToTextboxById(driver, "Address_Address2", address2);
		addressPage.inputToTextboxById(driver, "Address_ZipPostalCode", postalCode);
		addressPage.inputToTextboxById(driver, "Address_PhoneNumber", phoneNumber);
		addressPage.inputToTextboxById(driver, "Address_FaxNumber", faxNumber);
		addressPage.selectDefaultDropDownByName(driver, "Address.CountryId", country);
		addressPage.selectDefaultDropDownByName(driver, "Address.StateProvinceId", provice);

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 04: Click to 'Save' button");
		addressPage.clickToButtonByText(driver, "Save");

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 05: Verify the message 'add new address successfully' displayed");
		verifyTrue(addressPage.isMessageDispalyedOnBarNotificationByMessage(driver, "The new address has been added successfully."));

		ExtentTestManager.getTest().log(Status.INFO, "Add Address - Step 05: Verify the new address has been added successfully");
		verifyTrue(addressPage.isTitleDisplayedByFullName(firstName + " " + lastName));
		verifyTrue(addressPage.isAddressInformationDisplayedByEmail(email, firstName));
		verifyTrue(addressPage.isAddressInformationDisplayedByEmail(email, lastName));
		verifyTrue(addressPage.isAddressInformationDisplayedByEmail(email, companyName));
		verifyTrue(addressPage.isAddressInformationDisplayedByEmail(email, city));
		verifyTrue(addressPage.isAddressInformationDisplayedByEmail(email, address1));
		verifyTrue(addressPage.isAddressInformationDisplayedByEmail(email, address2));
		verifyTrue(addressPage.isAddressInformationDisplayedByEmail(email, postalCode));
		verifyTrue(addressPage.isAddressInformationDisplayedByEmail(email, phoneNumber));
		verifyTrue(addressPage.isAddressInformationDisplayedByEmail(email, faxNumber));
		verifyTrue(addressPage.isAddressInformationDisplayedByEmail(email, country));
	}

	@Test
	public void My_Account_03_Change_Password(Method method) {
		ExtentTestManager.startTest(method.getName(), "My_Account_03_Change_Password");
		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 01: Navigate to 'Change Password' page");
		changePasswordPage = (UserChangePasswordPageObject) customerInfoPage.openDynamicPageAtMyAccountByPageName(driver, "Change password");

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 02: Enter the old password with value: " + oldPassword);
		changePasswordPage.inputToTextboxById(driver, "OldPassword", oldPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 03: Enter the new password with value: " + newPassword);
		changePasswordPage.inputToTextboxById(driver, "NewPassword", newPassword);
		changePasswordPage.inputToTextboxById(driver, "ConfirmNewPassword", newPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 04: Click to 'Change Password' button");
		changePasswordPage.clickToButtonByText(driver, "Change password");

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 05: Verify the message 'Change password success' displayed");
		verifyTrue(changePasswordPage.isMessageDispalyedOnBarNotificationByMessage(driver, "Password was changed"));

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 06: Close the bar notification");
		changePasswordPage.closeTheBarNotification(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 07: Click to 'logout' link");
		homePage = changePasswordPage.clickToLogoutLinkAtUserPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 08: Click to 'login' link");
		loginPage = homePage.openLoginPage();

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 09: Enter the 'Email' textbox with value: " + email);
		loginPage.inputToEmailTextBox(email);

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 10: Enter the 'Password' textbox with value: " + oldPassword);
		loginPage.inputToPasswordTextBox(oldPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 11: Click to 'Log in' button");
		loginPage.clickToLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 12: Verify the login failed messagewith old password");
		verifyEquals(loginPage.getErrorLoginMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 13: Enter the the 'Password' textbox with value: " + newPassword);
		loginPage.inputToPasswordTextBox(newPassword);

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 13: Click to 'Log in' button");
		homePage = loginPage.clickToLoginButton();

		ExtentTestManager.getTest().log(Status.INFO, "Change password - Step 14: Verify the 'My Account' Link is displayed");
		verifyTrue(homePage.isMyAccountDisplayed());

	}

	@Test
	public void My_Account_04_Product_Review(Method method) {
		ExtentTestManager.startTest(method.getName(), "My_Account_04_Product_Review");
		ExtentTestManager.getTest().log(Status.INFO, "Product review - Step 01: Hover to 'Computers' category");
		homePage.hoverDynamicProductCategoryOnTopMenuByName(driver, "Computers");

		ExtentTestManager.getTest().log(Status.INFO, "Product review - Step 02: Navigate to 'Desktop' product page");
		productPage = homePage.openCategoryPageOnTopMenuByCategoryName("Desktops ");

		ExtentTestManager.getTest().log(Status.INFO, "Product review - Step 03: Verify the 'Desktops' word show on breadcrumb menu ");
		verifyEquals(productPage.getCurrentTextAtBreadcrumbMenu(), "Desktops");

		ExtentTestManager.getTest().log(Status.INFO, "Product review - Step 04: Navigate to Product Purchase Page");
		productPurchasePage = productPage.openDynamicProductDetailPageByName(productReview);

		ExtentTestManager.getTest().log(Status.INFO, "Product review - Step 05: Click to 'add your view' on Purchase Page");
		productReviewPage = productPurchasePage.openProductReviewPage();

		ExtentTestManager.getTest().log(Status.INFO, "Product review - Step 06: Verify Title product 'Build your own computer' on product review page");
		verifyEquals(productReviewPage.getTitleProductForOnReviewPage(), productReview);

		ExtentTestManager.getTest().log(Status.INFO, "Product review - Step 07: Enter review for product");
		productReviewPage.inputToTextboxById(driver, "AddProductReview_Title", reviewTitle);
		productReviewPage.inputToTextareaById(driver, "AddProductReview_ReviewText", reviewContent);
		productReviewPage.clickToRadioButtonByAriaLabel(driver, reviewRate);

		ExtentTestManager.getTest().log(Status.INFO, "Product review - Step 08: Click to submit review button");
		productReviewPage.clickToButtonByText(driver, "Submit review");

		ExtentTestManager.getTest().log(Status.INFO, "Product review - Step 09: Verify review on Review Product Page");
		verifyEquals(productReviewPage.getProductReviewTitleByFirstName(firstName), reviewTitle);
		verifyEquals(productReviewPage.getProductReviewRateByFirstName(firstName), reviewRate);
		verifyEquals(productReviewPage.getProductReviewContentByFirstName(firstName), reviewContent);

		ExtentTestManager.getTest().log(Status.INFO, "Product review - Step 10: Navigate to 'My Product review' page");
		customerInfoPage = productReviewPage.openMyAccountPage(driver);
		myProductReviewPage = (UserMyProductReviewPageObject) productReviewPage.openDynamicPageAtMyAccountByPageName(driver, "My product reviews");

		ExtentTestManager.getTest().log(Status.INFO, "Product review - Step 11: Verify review on My Account Page");
		verifyTrue(myProductReviewPage.isProductReviewTitleDisplayed(reviewTitle));
		verifyTrue(myProductReviewPage.isContentProductReviewDisplayed(reviewContent));
		verifyTrue(myProductReviewPage.isProductReviewRateDisplayedByTitle(reviewRate, reviewTitle));

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		 closeBrowserDriver();
	}

	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserCustomerInfoPageObject customerInfoPage;
	private UserAddressPageObject addressPage;
	private UserChangePasswordPageObject changePasswordPage;
	private UserCategoryPageObject productPage;
	private UserLoginPageObject loginPage;
	private UserProductDetailPageObject productPurchasePage;
	private UserProductReviewPageObject productReviewPage;
	private UserMyProductReviewPageObject myProductReviewPage;
	private String firstName, lastName, dobDay, dobMonth, dobYear, email, companyName, gender, country, provice, city, address1, address2, postalCode, phoneNumber, faxNumber;
	private String oldPassword, newPassword;
	private String productReview, reviewTitle, reviewContent, reviewRate;
}
