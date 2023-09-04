package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.CompareProductPageUI;

public class UserCompareProductPageObject extends BasePage {
	private WebDriver driver;

	public UserCompareProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isProductNameDisplayedAtComparisonTable(String productNameCompare) {
		waitForElementVisibility(driver, CompareProductPageUI.PRODUCT_NAME_AT_COMPARISION_TABLE, productNameCompare);
		return isElementDisplayed(driver, CompareProductPageUI.PRODUCT_NAME_AT_COMPARISION_TABLE, productNameCompare);
	}

	public boolean isRemoveButtonDisplayedAtComparisonTableByProductName(String productNameCompare) {
		int columnIndex = getElementsSize(driver, CompareProductPageUI.COLUMN_INDEX_BY_PRODUCT_NAME, productNameCompare) + 1;
		waitForElementVisibility(driver, CompareProductPageUI.REMOVE_BUTTON_AT_COMPARISION_TABLE_BY_COLUMN_INDEX, String.valueOf(columnIndex));
		return isElementDisplayed(driver, CompareProductPageUI.REMOVE_BUTTON_AT_COMPARISION_TABLE_BY_COLUMN_INDEX, String.valueOf(columnIndex));
	}

	public boolean isNameLabelDisplayedAtComparisonTableByProductName(String productNameCompare) {
		waitForElementVisibility(driver, CompareProductPageUI.NAME_LABEL_AT_COMPARISION_TABLE_BY_PRODUCT_NAME, productNameCompare);
		return isElementDisplayed(driver, CompareProductPageUI.NAME_LABEL_AT_COMPARISION_TABLE_BY_PRODUCT_NAME, productNameCompare);
	}

	public boolean isPriceLabelDisplayedAtComparisonTableByProductPrice(String productPriceCompare) {
		waitForElementVisibility(driver, CompareProductPageUI.PRICE_LABEL_AT_COMPARISION_TABLE_BY_PRODUCT_PRICE, productPriceCompare);
		return isElementDisplayed(driver, CompareProductPageUI.PRICE_LABEL_AT_COMPARISION_TABLE_BY_PRODUCT_PRICE, productPriceCompare);
	}

	public void clickToClearListLink() {
		waitForElementClickable(driver, CompareProductPageUI.CLEAR_LIST_LINK);
		clickToElement(driver, CompareProductPageUI.CLEAR_LIST_LINK);
	}

	public boolean isEmptyCompareProductMessDisplayed() {
		waitForElementVisibility(driver, CompareProductPageUI.EMPTY_PRODUCT_COMPARE_MESSAGE);
		return isElementDisplayed(driver, CompareProductPageUI.EMPTY_PRODUCT_COMPARE_MESSAGE);
	}

	public boolean isProductNameUnDisplayed(String productNameCompare) {
		return isElementUndisplayed(driver, CompareProductPageUI.PRODUCT_NAME_AT_COMPARISION_TABLE);
	}


}
