package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.ProductDetailPageUI;

public class UserProductDetailPageObject extends BasePage {
	private WebDriver driver;
	
	public UserProductDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserProductReviewPageObject openProductReviewPage() {
		waitForElementClickable(driver, ProductDetailPageUI.ADD_REVIEW_LINK);
		clickToElement(driver, ProductDetailPageUI.ADD_REVIEW_LINK);
		return PageGeneratorManager.getProductReviewPage(driver);
	}

	public String getCurrentProductName() {
		waitForElementVisibility(driver, ProductDetailPageUI.CURRENT_PRODUCT_NAME);
		return getElementText(driver, ProductDetailPageUI.CURRENT_PRODUCT_NAME);
	}

	public void clickToAddToWishlistButton() {
		waitForElementClickable(driver, ProductDetailPageUI.ADD_TO_WISHLIST_BUTTON);
		clickToElement(driver, ProductDetailPageUI.ADD_TO_WISHLIST_BUTTON);
	}


	public UserWishlistPageObject clickToWishlistLinkAtTheBarNotification() {
		waitForElementClickable(driver, ProductDetailPageUI.WISHLIST_LINK_AT_BAR_NOTIFICATION);
		clickToElement(driver, ProductDetailPageUI.WISHLIST_LINK_AT_BAR_NOTIFICATION);
		return PageGeneratorManager.getWishListPage(driver);
	}

}
