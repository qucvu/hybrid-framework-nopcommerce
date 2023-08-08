package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.admin.AdminLoignPageUI;

public class AdminLoginPageObject extends BasePage {
	private WebDriver driver;
	
	public AdminLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputToEmailTextBox(String email) {
		waitForElementVisibility(driver, AdminLoignPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, AdminLoignPageUI.EMAIL_TEXTBOX, email);
	}

	public void inputToPasswordTextBox(String password) {
		waitForElementVisibility(driver, AdminLoignPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, AdminLoignPageUI.PASSWORD_TEXTBOX, password);
	}
	
	public AdminDashboardPageObject clickToLoginButton() {
		waitForElementClickable(driver, AdminLoignPageUI.LOGIN_BUTTON);
		clickToElement(driver, AdminLoignPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getAdminDashboardPage(driver);
	}

	public AdminDashboardPageObject loginAsAdmin(String adminEmailAddress, String adminPassword) {
		inputToEmailTextBox(adminEmailAddress);
        inputToPasswordTextBox(adminPassword);
        return clickToLoginButton();
		
	}

}
