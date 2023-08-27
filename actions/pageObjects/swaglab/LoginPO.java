package pageObjects.swaglab;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.swaglab.LoginPageUI;

public class LoginPO extends BasePage {
	private WebDriver driver;

	public LoginPO(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToUsernameTextbox(String userName) {
		waitForElementVisibility(driver, LoginPageUI.USERNAME_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX, userName);

	}

	public void enterToPasswordTextbox(String password) {
		waitForElementVisibility(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);

	}

	public ProductPO clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getProductPage(driver);
	}

}
