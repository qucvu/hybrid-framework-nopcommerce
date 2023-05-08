package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;	
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public RegisterPageObject openRegisterPage() {
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
//		return new RegisterPageObject(driver); : cách 2
		return PageGeneratorManager.getRegisterPage(driver);
		
	}

	public LoginPageObject openLoginPage() {
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
//		return new LoginPageObject(driver); : cách 2
		return PageGeneratorManager.getLoginPage(driver);
	}
	
	public boolean isMyAccountDisplayed() {
		waitForElementVisibility(driver, HomePageUI.MY_ACCOUNT_LINK);
		return isElemenetDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
	}
	
	public CustomerInfoPageObject openCustomerInfoPage() {
		waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getCustomerInfoPage(driver);
	}


}
