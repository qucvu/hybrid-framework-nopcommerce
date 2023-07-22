package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.ProductPurchasePageUI;

public class ProductPurchasePageObject extends BasePage {
	private WebDriver driver;
	
	public ProductPurchasePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public ProductReviewPageObject openProductReviewPage() {
		waitForElementClickable(driver, ProductPurchasePageUI.ADD_REVIEW_LINK);
		clickToElement(driver, ProductPurchasePageUI.ADD_REVIEW_LINK);
		return PageGeneratorManager.getProductReviewPage(driver);
	}

}
