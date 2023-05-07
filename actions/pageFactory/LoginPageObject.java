package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class LoginPageObject extends BasePageFactory {
	private WebDriver driver;	
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// pageUI
	@FindBy(xpath = "//button[contains(@class, 'login-button')]")
	private WebElement loginButton;

	@FindBy(id = "Email")
	private WebElement emailTextbox;

	@FindBy(id = "Password")
	private WebElement passwordTextbox;

	@FindBy(id = "Email-error")
	private WebElement emailErrorMessage;

	@FindBy(xpath = "//div[contains(@class, 'message-error')]")
	private WebElement unsuccessfulErrorMessage;

	public void clickToLoginButton() {
		waitForElementClickable(driver, loginButton);
		clickToElement(driver, loginButton);
	}

	public void inputToEmailTextBox(String email) {
		waitForElementVisibility(driver, emailTextbox);
		sendkeyToElement(driver, emailTextbox, email);
	}

	public void inputToPasswordTextBox(String password) {
		waitForElementVisibility(driver, passwordTextbox);
		sendkeyToElement(driver, passwordTextbox, password);
	}

	public String getErrorLoginMessage() {
		waitForElementVisibility(driver, unsuccessfulErrorMessage);
		return getElementText(driver, unsuccessfulErrorMessage);
	}
	
	public String getErrorMessageAtEmailTextBox() {
		waitForElementVisibility(driver, emailErrorMessage);
		return getElementText(driver, emailErrorMessage);
	}
	
	public void loginToSystem(String email, String password) {
		inputToEmailTextBox(email);
        inputToPasswordTextBox(password);
        clickToLoginButton();
	}


}
