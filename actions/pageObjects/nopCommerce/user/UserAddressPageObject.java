package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.AddressPageUI;

public class UserAddressPageObject extends BasePage {
	private WebDriver driver;

	public UserAddressPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isAddressInformationDisplayedByEmail(String email, String value) {
		waitForElementVisibility(driver, AddressPageUI.DYNAMIC_ADDRESS_ITEM_INFORMATION_BY_EMAIL, email, value);
		return isElementDisplayed(driver, AddressPageUI.DYNAMIC_ADDRESS_ITEM_INFORMATION_BY_EMAIL, email, value);
	}

	public boolean isTitleDisplayedByFullName(String fullName) {
		waitForElementVisibility(driver, AddressPageUI.DYNAMIC_TITLE_ADDRESS_INFORMATION_BY_FULLNAME, fullName);
		return isElementDisplayed(driver, AddressPageUI.DYNAMIC_TITLE_ADDRESS_INFORMATION_BY_FULLNAME, fullName);
	}

}
