package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.CategoryPageUI;

public class CategoryPageObject extends BasePage {
	private WebDriver driver;
	
	public CategoryPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getCurrentTextAtBreadcrumbMenu() {
		waitForElementVisibility(driver, CategoryPageUI.CURRENT_ITEM_AT_BREADCRUMB_MENU);
		return getElementText(driver, CategoryPageUI.CURRENT_ITEM_AT_BREADCRUMB_MENU);
	}

	/**
	 * Open dynamic purchase page by title
	 * @param driver
	 * @param title
	 * @return
	 */
	public ProductPurchasePageObject openDynamicPurchasePageByTitle(String title) {
		waitForElementClickable(driver, CategoryPageUI.DYNAMIC_PRODUCT_TITLE_BY_TITLE, title);
		clickToElement(driver, CategoryPageUI.DYNAMIC_PRODUCT_TITLE_BY_TITLE, title);
		return PageGeneratorManager.getProductPurchasePage(driver);
	}

}
