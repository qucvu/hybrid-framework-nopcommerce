package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.WishlistPageUI;

public class UserWishlistPageObject extends BasePage {
	private WebDriver driver;

	public UserWishlistPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isWishlistTitleDisplayed() {
		waitForElementVisibility(driver, WishlistPageUI.WISHLIST_TITLE);
		return isElementDisplayed(driver, WishlistPageUI.WISHLIST_TITLE);
	}

	public boolean isProductNameDisplayedAtWishlistTable(String productName) {
		waitForElementVisibility(driver, WishlistPageUI.DYNAMIC_PRODUCT_AT_WISHLIST_TABLE_BY_NAME, productName);
		return isElementDisplayed(driver, WishlistPageUI.DYNAMIC_PRODUCT_AT_WISHLIST_TABLE_BY_NAME, productName);
	}

	public void clickToPersionalWishlistUrl() {
		waitForElementClickable(driver, WishlistPageUI.PERSONAL_WISHLIST_LINK);
		clickToElement(driver, WishlistPageUI.PERSONAL_WISHLIST_LINK);
	}

	public boolean isFullNameDisplayedAtWishlistTitle(String fullName) {
		waitForElementVisibility(driver, WishlistPageUI.WISHLIST_TITLE_WITH_FULL_NAME, fullName);
		return isElementDisplayed(driver, WishlistPageUI.WISHLIST_TITLE_WITH_FULL_NAME, fullName);
	}


	public void selectToCheckboxAddToCartByProductName(String productName) {
		waitForElementClickable(driver, WishlistPageUI.CHECKBOX_ADD_TO_CART_BY_PRODUCT_NAME, productName);
		checkToDefaultCheckboxRadio(driver, WishlistPageUI.CHECKBOX_ADD_TO_CART_BY_PRODUCT_NAME, productName);
	}

	public UserShoppingCartPageObject clickToAddToCartButton() {
		waitForElementClickable(driver, WishlistPageUI.ADD_TO_CART_BUTTON);
		clickToElement(driver, WishlistPageUI.ADD_TO_CART_BUTTON);
		return PageGeneratorManager.getShoppingCartPage(driver);
	}

	public boolean isEmptyWishlistMessageDisplayed() {
		waitForElementVisibility(driver, WishlistPageUI.EMPTY_WISHLIST_MESSAGE);
		return isElementDisplayed(driver, WishlistPageUI.EMPTY_WISHLIST_MESSAGE);
	}

	public boolean isProductNameUnDisplayedAtWishlistTable(String productName) {
		return isElementUndisplayed(driver, WishlistPageUI.DYNAMIC_PRODUCT_AT_WISHLIST_TABLE_BY_NAME, productName);
	}

	public void clickToRemoveButtonByProductName(String productName) {
		waitForElementClickable(driver, WishlistPageUI.REMOVE_BUTTON_CELL_BY_PRODUCT_NAME, productName);
		clickToElement(driver, WishlistPageUI.REMOVE_BUTTON_CELL_BY_PRODUCT_NAME, productName);
	}

}
