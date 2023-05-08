package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;
import pageUIs.nopCommerce.user.RegisterPageUI;

public class RegisterPageObject extends BasePageFactory {
	private WebDriver driver;	
	
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "register-button")
	private WebElement registerButton;

	@FindBy(id = "FirstName")
	private WebElement firstNameTextbox;

	@FindBy(id = "LastName")
	private WebElement lastNameTextbox;

	@FindBy(id = "Email")
	private WebElement emailTextbox;

	@FindBy(id = "Password")
	private WebElement passwordTextbox;

	@FindBy(id = "ConfirmPassword")
	private WebElement confirmPasswordTextBox;

	@FindBy(id = "FirstName-error")
	private WebElement firstNameErrorMessage;

	@FindBy(id = "LastName-error")
	private WebElement lastNameErrorMessage;

	@FindBy(id = "Email-error")
	private WebElement emailErrorMessage;

	@FindBy(id = "Password-error")
	private WebElement passwordErrorMessage;

	@FindBy(id = "ConfirmPassword-error")
	private WebElement confirmPasswordErrorMessage;

	@FindBy(css = "div.result")
	private WebElement registerSuccessMessage;

	@FindBy(css = "div.message-error")
	private WebElement existingEmailErrorMessage;

	@FindBy(css = "div.message-error")
	private WebElement wrongEmailErrorMessage;

	@FindBy(css = "a.register-continue-button")
	private WebElement continueButton;

	@FindBy(css = "a.ico-logout")
	private WebElement logoutLink;
	
	public void clickToRegisterButton() {
		waitForElementClickable(driver, registerButton);
		clickToElement(driver, registerButton);
	}

	public String getErrorMessageAtFirstNameTextBox() {
		waitForElementVisibility(driver, firstNameErrorMessage);
		return getElementText(driver, firstNameErrorMessage);
	}

	public String getErrorMessageAtLastNameTextBox() {
		waitForElementVisibility(driver, lastNameErrorMessage);
		return getElementText(driver, lastNameErrorMessage);
	}

	public String getErrorMessageAtEmailTextBox() {
		waitForElementVisibility(driver, emailErrorMessage);
		return getElementText(driver, emailErrorMessage);
	}
	
	public String getErrorMessageAtPasswordTextBox() {
		waitForElementVisibility(driver, passwordErrorMessage);
		return getElementText(driver, passwordErrorMessage);
	}


	public String getErrorMessageAtConfirmPasswordTextBox() {
		waitForElementVisibility(driver, confirmPasswordErrorMessage);
		return getElementText(driver, confirmPasswordErrorMessage);
	}

	public void inputToFirstNameTextBox(String firstName) {
		waitForElementVisibility(driver, firstNameTextbox);
		sendkeyToElement(driver, firstNameTextbox, firstName);		
	}

	public void inputToLastNameTextBox(String lastName) {
		waitForElementVisibility(driver, lastNameTextbox);
		sendkeyToElement(driver, lastNameTextbox, lastName);		
	}

	public void inputToEmailTextBox(String emailAddress) {
		waitForElementVisibility(driver, emailTextbox);
		sendkeyToElement(driver, emailTextbox, emailAddress);		
	}
	
	public void inputToPassowrdTextBox(String password) {
		waitForElementVisibility(driver, passwordTextbox);
		sendkeyToElement(driver, passwordTextbox, password);		

		
	}

	public void inputToConfirmPasswordTextBox(String confirmPassword) {
		waitForElementVisibility(driver, confirmPasswordTextBox);
		sendkeyToElement(driver, confirmPasswordTextBox, confirmPassword);		
	}
	

	public String getErrorExistingEmailMessage() {
		waitForElementVisibility(driver, existingEmailErrorMessage);
		return getElementText(driver, existingEmailErrorMessage);
	}

	public String getSuccessRegisterMessage() {
		waitForElementVisibility(driver, registerSuccessMessage);
		return getElementText(driver, registerSuccessMessage);
	}

	public String getErrorWrongEmailMessage() {
		waitForElementVisibility(driver, wrongEmailErrorMessage);
		return getElementText(driver, wrongEmailErrorMessage);
	}
	public void clickToContinueButton() {
		waitForElementClickable(driver, continueButton);
		clickToElement(driver, continueButton);
	}

	public void clickToLogoutLink() {
		waitForElementClickable(driver, logoutLink);
		clickToElement(driver, logoutLink);
	}


}
