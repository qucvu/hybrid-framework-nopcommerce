package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.admin.AdminCustomerPageUI;

public class AdminCustomersPageObject extends BasePage {
	private WebDriver driver;

	public AdminCustomersPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToAddNewlink() {
		waitForElementClickable(driver, AdminCustomerPageUI.ADD_NEW_LINK);
		clickToElement(driver, AdminCustomerPageUI.ADD_NEW_LINK);
	}

	public void selectCustomerRoleDropdown(String customerRole) {
		waitForElementClickable(driver, AdminCustomerPageUI.CUSTOMER_ROLE_TEXTBOX);
		selectItemDropdown(driver, AdminCustomerPageUI.CUSTOMER_ROLE_TEXTBOX, AdminCustomerPageUI.ALL_CUSTOMER_ROLES, customerRole);
	}

	public boolean isActiveDisplayedAtCustomerTableByFullName(String customerFullName) {
		waitForElementVisibility(driver, AdminCustomerPageUI.ACTIVE_CUSTOMER_AT_TABLE_RESULT_BY_NAME, customerFullName);
		return isElementDisplayed(driver, AdminCustomerPageUI.ACTIVE_CUSTOMER_AT_TABLE_RESULT_BY_NAME, customerFullName);
	}
	

	public void clickToSaveButtonAtCustomerPage() {
		waitForElementClickable(driver, AdminCustomerPageUI.SAVE_BUTTON_AT_CUSTOMER_PAGE);
		clickToElement(driver, AdminCustomerPageUI.SAVE_BUTTON_AT_CUSTOMER_PAGE);
	}

	public void clickToBackToCustomerDetailsLink() {
		waitForElementVisibility(driver, AdminCustomerPageUI.BACK_TO_CUSTOMER_DETAILS_LINK);
		clickToElement(driver, AdminCustomerPageUI.BACK_TO_CUSTOMER_DETAILS_LINK);
		
	}

	public boolean isSearchDataDisplayedAtAddressHeaderByEmail(String customerEmail, String addressData) {
		waitForElementVisibility(driver, AdminCustomerPageUI.ADDRESS_INFORMATION_BY_EMAIL,customerEmail, addressData);
		return isElementDisplayed(driver, AdminCustomerPageUI.ADDRESS_INFORMATION_BY_EMAIL, customerEmail ,addressData);
	}

	public void clickToBackToCustomerListLink() {
		waitForElementVisibility(driver, AdminCustomerPageUI.BACK_TO_CUSTOMER_LIST_LINK);
		clickToElement(driver, AdminCustomerPageUI.BACK_TO_CUSTOMER_LIST_LINK);
	}

	public void clickToEditCustomerButtonAtCustomerTableByFullName(String customerFullName) {
		waitForElementClickable(driver, AdminCustomerPageUI.EDIT_CUSTOMER_BUTTON_AT_TABLE_RESULT_BY_NAME, customerFullName);
		clickToElement(driver, AdminCustomerPageUI.EDIT_CUSTOMER_BUTTON_AT_TABLE_RESULT_BY_NAME, customerFullName);
	}

	public void clickToEditAddressButtonAtAddressTableByEmail(String customerEmail) {
		waitForElementClickable(driver, AdminCustomerPageUI.EDIT_ADDRESS_BUTTON_AT_TABLE_RESULT_BY_EMAIL, customerEmail);
		clickToElement(driver, AdminCustomerPageUI.EDIT_ADDRESS_BUTTON_AT_TABLE_RESULT_BY_EMAIL, customerEmail);
		
	}
	
	public void clickToDeleteAddressButtonAtAddressTableByEmail(String customerEmail) {
		waitForElementClickable(driver, AdminCustomerPageUI.DELETE_ADDRESS_BUTTON_AT_TABLE_RESULT_BY_EMAIL, customerEmail);
		clickToElement(driver, AdminCustomerPageUI.DELETE_ADDRESS_BUTTON_AT_TABLE_RESULT_BY_EMAIL, customerEmail);
		
	}

	public void acceptDeleteAlert() {
		acceptAlert(driver);
		
	}


	




}
