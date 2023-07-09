package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;
import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import pageUIs.nopCommerce.user.RegisterPageUI;

public class UserRegisterPageObject extends BasePage {
	private WebDriver driver;	
	
	public UserRegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Click to Register button")
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

	@Step("Enter to Firstname textbox with value: {0}")
	public void inputToFirstNameTextBox(String firstName) {
		waitForElementVisibility(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);		
	}

	@Step("Enter to Lastname textbox with value: {0}")
	public void inputToLastNameTextBox(String lastName) {
		waitForElementVisibility(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, lastName);		
	}
	
	@Step("Enter to Email textbox with value: {0}")
	public void inputToEmailTextBox(String emailAddress) {
		waitForElementVisibility(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailAddress);		
	}
	
	@Step("Enter to Password textbox with value: {0}")
	public void inputToPassowrdTextBox(String password) {
		waitForElementVisibility(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);		

		
	}

	
	@Step("Enter to Confirm Password textbox with value: {0}")
	public void inputToConfirmPasswordTextBox(String confirmPassword) {
		waitForElementVisibility(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);		
	}
	

	public String getErrorExistingEmailMessage() {
		waitForElementVisibility(driver, RegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
	}

	@Step("Get Success Register Message at 'Register' Page")
	public String getSuccessRegisterMessage() {
		waitForElementVisibility(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public String getErrorWrongEmailMessage() {
		waitForElementVisibility(driver, RegisterPageUI.WRONG_EMAIL_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.WRONG_EMAIL_ERROR_MESSAGE);
	}
	public UserHomePageObject clickToContinueButton() {
		waitForElementClickable(driver, RegisterPageUI.CONTINUE_BUTTON);
		clickToElement(driver, RegisterPageUI.CONTINUE_BUTTON);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public void clickToLogoutLink() {
		waitForElementClickable(driver, RegisterPageUI.LOGOUT_lINK);
		clickToElement(driver, RegisterPageUI.LOGOUT_lINK);
	}






}
