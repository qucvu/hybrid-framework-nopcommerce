package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;
import commons.BasePage;
import pageUIs.nopCommerce.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	private WebDriver driver;	
	
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickToRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}

	public String getErrorMessageAtFirstNameTextBox() {
		waitForElementVisibility(driver, RegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
	}

	public String getErrorMessageAtLastNameTextBox() {
		waitForElementVisibility(driver, RegisterPageUI.LAST_NAME_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.LAST_NAME_ERROR_MESSAGE);
	}

	public String getErrorMessageAtEmailTextBox() {
		waitForElementVisibility(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
	}
	
	public String getErrorMessageAtPasswordTextBox() {
		waitForElementVisibility(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
	}


	public String getErrorMessageAtConfirmPasswordTextBox() {
		waitForElementVisibility(driver, RegisterPageUI.CONFIRM_PASSOWORD_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.CONFIRM_PASSOWORD_ERROR_MESSAGE);
	}

	public void inputToFirstNameTextBox(String firstName) {
		waitForElementVisibility(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);		
	}

	public void inputToLastNameTextBox(String lastName) {
		waitForElementVisibility(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, lastName);		
	}

	public void inputToEmailTextBox(String emailAddress) {
		waitForElementVisibility(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailAddress);		
	}
	
	public void inputToPassowrdTextBox(String password) {
		waitForElementVisibility(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);		

		
	}

	public void inputToConfirmPasswordTextBox(String confirmPassword) {
		waitForElementVisibility(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);		
	}
	

	public String getErrorExistingEmailMessage() {
		waitForElementVisibility(driver, RegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
	}

	public String getSuccessRegisterMessage() {
		waitForElementVisibility(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public String getErrorWrongEmailMessage() {
		waitForElementVisibility(driver, RegisterPageUI.WRONG_EMAIL_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.WRONG_EMAIL_ERROR_MESSAGE);
	}
	public void clickToContinueButton() {
		waitForElementClickable(driver, RegisterPageUI.CONTINUE_BUTTON);
		clickToElement(driver, RegisterPageUI.CONTINUE_BUTTON);
	}

	public void clickToLogoutLink() {
		waitForElementClickable(driver, RegisterPageUI.LOGOUT_lINK);
		clickToElement(driver, RegisterPageUI.LOGOUT_lINK);
	}


}
