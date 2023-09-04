package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.BasePageNopCommerceUI;
import pageUIs.nopCommerce.user.HomePageUI;

public class UserHomePageObject extends BasePage {
	private WebDriver driver;

	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	// @Step("Navigate to 'Register' Page")
	public UserRegisterPageObject openRegisterPage() {
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		return PageGeneratorManager.getUserRegisterPage(driver);

	}

	// @Step("Navigate to 'Login' Page")
	public UserLoginPageObject openLoginPage() {
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getUserLoginPage(driver);
	}

	// @Step("Verify the 'My Account' Link is displayed")
	public boolean isMyAccountDisplayed() {
		waitForElementVisibility(driver, BasePageNopCommerceUI.MY_ACCOUNT_LINK_USER_PAGE);
		return isElementDisplayed(driver, BasePageNopCommerceUI.MY_ACCOUNT_LINK_USER_PAGE);
	}



	/**
	 * Open Category page on Top Menu by Product Name
	 * @param productName
	 * @return
	 */
	public UserCategoryPageObject openCategoryPageOnTopMenuByCategoryName( String productName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_SUB_PRODUCT_CATEGORY_LINK_BY_NAME, productName);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_SUB_PRODUCT_CATEGORY_LINK_BY_NAME, productName);
		return PageGeneratorManager.getCategoryPageObject(driver);
	}

}
