package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.LoginPageUI;

public class UserLoginPageObject extends BasePage {
	private WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserHomePageObject clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		// return new HomePageObject(driver);: cách 2
		return PageGeneratorManager.getUserHomePage(driver);
	}

	// @Step("Enter to Email textbox with value: {0}")
	public void inputToEmailTextBox(String email) {
		waitForElementVisibility(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, email);
	}

	// @Step("Enter to Password textbox with value: {0}")
	public void inputToPasswordTextBox(String password) {
		waitForElementVisibility(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}

	// @Step("Get Error Message for unsuccessful login")
	public String getErrorLoginMessage() {
		waitForElementVisibility(driver, LoginPageUI.UNSUCCESSFUL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.UNSUCCESSFUL_ERROR_MESSAGE);
	}

	// @Step("Get Error Message at Email textbox")
	public String getErrorMessageAtEmailTextBox() {
		waitForElementVisibility(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
	}

	// @Step("Login to System as User with email: {0} and password: {1}")
	public UserHomePageObject loginAsUser(String email, String password) {
		inputToEmailTextBox(email);
		inputToPasswordTextBox(password);
		return clickToLoginButton();
	}

}
