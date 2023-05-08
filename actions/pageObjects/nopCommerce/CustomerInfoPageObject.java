package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.CustomerInfoPageUI;

public class CustomerInfoPageObject extends BasePage {
	private WebDriver driver;
	
	public CustomerInfoPageObject(WebDriver driver) {
		this.driver = driver;
    }

	public String getValueAtFirstNameTextbox() {
		waitForAllElementVisibility(driver, CustomerInfoPageUI.FIRST_NAME_TEXTBOX);
		return getElementAttribute(driver, "value", CustomerInfoPageUI.FIRST_NAME_TEXTBOX);
	}

	public String getValueAtLastNameTextbox() {
		waitForAllElementVisibility(driver, CustomerInfoPageUI.LAST_NAME_TEXTBOX);
		return getElementAttribute(driver, "value", CustomerInfoPageUI.LAST_NAME_TEXTBOX);
	}

	public String getValueAtEmailTextbox() {
		waitForAllElementVisibility(driver, CustomerInfoPageUI.EMAIL_TEXTBOX);
		return getElementAttribute(driver, "value", CustomerInfoPageUI.EMAIL_TEXTBOX);
	}

	public boolean isCustomerInfoDisplayed() {
		waitForElementVisibility(driver, CustomerInfoPageUI.CUSTOMER_INFO_HEADER);
		return isElemenetDisplayed(driver, CustomerInfoPageUI.CUSTOMER_INFO_HEADER);
	}
}
