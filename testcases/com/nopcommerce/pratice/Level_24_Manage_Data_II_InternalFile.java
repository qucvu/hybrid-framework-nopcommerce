package com.nopcommerce.pratice;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.data.UserData;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import utilities.DataHelper;

public class Level_24_Manage_Data_II_InternalFile extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName, "https://demo.nopcommerce.com/");
		dataFaker = DataHelper.getDataHelper();
		
		firstName = UserData.FIRSTNAME;
		lastName = UserData.LASTNAME;
		emailAddress = dataFaker.getEmail();
		password = UserData.Login.PASSWORD;
		day = "10";
		month = "May";
		year  = "2000";

		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		log.info("Pre-condition - Step 01: Navigate to 'Register' page");
		registerPage = homePage.openRegisterPage();
		
		log.info("Pre-condition - Step 02: Click to radio Button");
		registerPage.clickToRadioButtonByLabelName(driver, "Male");
		
		log.info("Pre-condition - Step 03: Enter to FirstName textbox with value is '" + firstName + "'");
		registerPage.inputToTextboxById(driver, "FirstName", firstName);

		log.info("Pre-condition - Step 04: Enter to Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToTextboxById(driver, "LastName", lastName);

		log.info("Pre-condition - Step 05: Enter to dropdown DOB with value");
		registerPage.selectDefaultDropDownByName(driver, "DateOfBirthDay",day);
		registerPage.selectDefaultDropDownByName(driver, "DateOfBirthMonth", month);
		registerPage.selectDefaultDropDownByName(driver, "DateOfBirthYear", year);

		log.info("Pre-condition - Step 06: Enter to Email Address textbox with value is '" + emailAddress + "'");
		registerPage.inputToTextboxById(driver, "Email", emailAddress);

		log.info("Pre-condition - Step 07: Check to checkbox NewsLetter");
		registerPage.clickToCheckboxByLabel(driver, "Newsletter");

		log.info("Pre-condition - Step 08: Enter to Password textbox with value is '" + password + "'");
		registerPage.inputToTextboxById(driver, "Password", password);

		 
		log.info("Pre-condition - Step 06: Enter to Confirm Password textbox with value is '" + password + "'");
		registerPage.inputToTextboxById(driver, "ConfirmPassword", password);

		
		log.info("Pre-condition - Step 08: Click to 'Register' button");
		registerPage.clickToRegisterButton();
		
		log.info("Pre-condition - Step 09: Verify register success message is displayed");
		verifyEquals(registerPage.getSuccessRegisterMessage(), "Your registration completed");

	}	

	@Test
	public void Login() {
		log.info("Login - Step 01: Click to Continue button");
		homePage = registerPage.clickToContinueButton();

		log.info("Login - Step 02: Navigate to Login page");
		loginPage = homePage.openLoginPage();

		log.info("Login - Step 12: Enter to Email textbox with value is '" + emailAddress + "'");
		loginPage.inputToTextboxById(driver, "Email", emailAddress);

		log.info("Login - Step 13: Enter to Password textbox with value is '" + password + "'");
		loginPage.inputToTextboxById(driver, "Password", password);

		log.info("Login - Step 14: Click to Login button");	
		loginPage.clickToButtonByText(driver, "Log in");
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		log.info("Login - Step 15: Verify is 'My account' Link displayed");	
		verifyTrue(homePage.isMyAccountDisplayed());
	}

	@Test
	public void My_Account() {
		log.info("Login - Step 01: Open to Customer Info Page");	
		customerInfoPage = homePage.openMyAccountPage(driver);
		
		log.info("Login - Step 02: Verify the correct data on Customer Info page");
		verifyEquals(customerInfoPage.getTextboxValueByID(driver, "FirstName"), firstName);
		verifyEquals(customerInfoPage.getTextboxValueByID(driver, "LastName"), lastName);
		verifyEquals(customerInfoPage.getTextboxValueByID(driver, "Email"), emailAddress);
		verifyTrue(customerInfoPage.isSelectedRadioByLabel(driver, "Male"));
		verifyEquals(customerInfoPage.getSelectItemDropdownByName(driver, "DateOfBirthDay"), day);
		verifyEquals(customerInfoPage.getSelectItemDropdownByName(driver, "DateOfBirthMonth"), month);
		verifyEquals(customerInfoPage.getSelectItemDropdownByName(driver, "DateOfBirthYear"), year);
	}


	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}

	private WebDriver driver;
	private DataHelper dataFaker;
	private UserLoginPageObject loginPage;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserCustomerInfoPageObject customerInfoPage;
	private String firstName, lastName, password, emailAddress, day, month, year;
}
