package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.ShoppingCartPageUI;

public class UserShoppingCartPageObject extends BasePage {
	private WebDriver driver;

	public UserShoppingCartPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isShoppingCartTitleDisplayed() {
		waitForElementVisibility(driver, ShoppingCartPageUI.SHOPPING_CART_TITLE);
		return isElementDisplayed(driver, ShoppingCartPageUI.SHOPPING_CART_TITLE);
	}

	public boolean isProductNameDisplayedAtCartTable(String productName) {
		waitForElementVisibility(driver, ShoppingCartPageUI.DYNAMIC_PRODUCT_AT_CART_TABLE_BY_NAME, productName);
		return isElementDisplayed(driver, ShoppingCartPageUI.DYNAMIC_PRODUCT_AT_CART_TABLE_BY_NAME, productName);
	}



}
