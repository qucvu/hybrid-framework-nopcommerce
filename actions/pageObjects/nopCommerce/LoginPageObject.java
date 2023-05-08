package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.LoginPageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	


	public HomePageObject clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
//		return new HomePageObject(driver);: cách 2
		return PageGeneratorManager.getHomePage(driver);
	}

	public void inputToEmailTextBox(String email) {
		waitForElementVisibility(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, email);
	}

	public void inputToPasswordTextBox(String password) {
		waitForElementVisibility(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}

	public String getErrorLoginMessage() {
		waitForElementVisibility(driver, LoginPageUI.UNSUCCESSFUL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.UNSUCCESSFUL_ERROR_MESSAGE);
	}
	
	public String getErrorMessageAtEmailTextBox() {
		waitForElementVisibility(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
	}
	
	public void loginToSystem(String email, String password) {
		inputToEmailTextBox(email);
        inputToPasswordTextBox(password);
        clickToLoginButton();
	}


	
	

}
