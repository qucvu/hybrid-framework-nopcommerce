package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.MyAccountPageUI;

public class MyAccountPageObject extends BasePage {
	private WebDriver driver;
	
	public MyAccountPageObject(WebDriver driver) {
		this.driver = driver;
    }

	public String getValueAtFirstNameTextbox() {
		waitForAllElementVisibility(driver, MyAccountPageUI.FIRST_NAME_TEXTBOX);
		return getElementAttribute(driver, "value", MyAccountPageUI.FIRST_NAME_TEXTBOX);
	}

	public String getValueAtLastNameTextbox() {
		waitForAllElementVisibility(driver, MyAccountPageUI.LAST_NAME_TEXTBOX);
		return getElementAttribute(driver, "value", MyAccountPageUI.LAST_NAME_TEXTBOX);
	}

	public String getValueAtEmailTextbox() {
		waitForAllElementVisibility(driver, MyAccountPageUI.EMAIL_TEXTBOX);
		return getElementAttribute(driver, "value", MyAccountPageUI.EMAIL_TEXTBOX);
	}
}
