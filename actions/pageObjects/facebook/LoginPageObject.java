package pageObjects.facebook;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.facebook.LoginPageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToCreateNewAccountLink() {
		waitForElementClickable(driver, LoginPageUI.ACCOUNT_LINK);
		clickToElement(driver, LoginPageUI.ACCOUNT_LINK);
	}

	public boolean isEmailAdddressTextboxDisplayed() {
		waitForElementVisibility(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
		return isElemenetDisplayed(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
	}

	public boolean isConfirmEmailAdddressTextboxUnDisplayed() {
//		waitForElementInvisibility(driver, LoginPageUI.COFIRM_EMAIL_ADDRESS_TEXTBOX);
		return isElementUndisplayed(driver, LoginPageUI.COFIRM_EMAIL_ADDRESS_TEXTBOX);
	}

	public void enterToEmailTextbox(String email) {
		waitForElementVisibility(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX, email);
	}

	public void clickToCloseImg() {
		waitForElementClickable(driver, LoginPageUI.CLOSE_IMG);
		clickToElement(driver, LoginPageUI.CLOSE_IMG);
		
	}

	
}
