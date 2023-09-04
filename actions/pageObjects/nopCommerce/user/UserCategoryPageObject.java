package pageObjects.nopCommerce.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.CategoryPageUI;

public class UserCategoryPageObject extends BasePage {
	private WebDriver driver;

	public UserCategoryPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getCurrentTextAtBreadcrumbMenu() {
		waitForElementVisibility(driver, CategoryPageUI.CURRENT_ITEM_AT_BREADCRUMB_MENU);
		return getElementText(driver, CategoryPageUI.CURRENT_ITEM_AT_BREADCRUMB_MENU);
	}

	/**
	 * Open dynamic purchase page by product name
	 * 
	 * @param driver
	 * @param name
	 * @return
	 */
	public UserProductDetailPageObject openDynamicProductDetailPageByName(String productName) {
		waitForElementClickable(driver, CategoryPageUI.DYNAMIC_PRODUCT_NAME_AT_PRODUCT_ITEM, productName);
		clickToElement(driver, CategoryPageUI.DYNAMIC_PRODUCT_NAME_AT_PRODUCT_ITEM, productName);
		return PageGeneratorManager.getProductPurchasePage(driver);
	}

	public String getCategoryPageTitle() {
		waitForElementVisibility(driver, CategoryPageUI.CATEGORY_TITLE);
		return getElementText(driver, CategoryPageUI.CATEGORY_TITLE);
	}

	public void selectItemToSortDropdown(String option) {
		waitForElementClickable(driver, CategoryPageUI.SORT_DROPDOWN);
		selectItemInDefaultDropdown(driver, CategoryPageUI.SORT_DROPDOWN, option);
	}

	public boolean isProductNameSortAscending() {
		waitForElementInvisibility(driver, CategoryPageUI.AJAX_PRODUCT_LOADING);
		return isDataStringSortAsc(driver, CategoryPageUI.PRODUCT_TITLE_TEXT);
	}

	public boolean isProductNameSortDescending() {
		waitForElementInvisibility(driver, CategoryPageUI.AJAX_PRODUCT_LOADING);
		return isDataStringSortDescLamDa(driver, CategoryPageUI.PRODUCT_TITLE_TEXT);
	}

	public boolean isProductPriceSortAscending() {
		waitForElementInvisibility(driver, CategoryPageUI.AJAX_PRODUCT_LOADING);
		List<Float> productPriceUIList = new ArrayList<Float>();
		List<WebElement> productPriceElementList = getListElements(driver, CategoryPageUI.PRODUCT_PRICE_TEXT);
		for (WebElement element : productPriceElementList) {
			productPriceUIList.add(Float.parseFloat(element.getText().replaceAll("[\\$,]", "")));
		}
		List<Float> productPriceSortList = new ArrayList<Float>(productPriceUIList);

		Collections.sort(productPriceSortList);
		return productPriceUIList.equals(productPriceSortList);
	}

	public boolean isProductPriceSortDescending() {
		waitForElementInvisibility(driver, CategoryPageUI.AJAX_PRODUCT_LOADING);
		List<Float> productPriceUIList = new ArrayList<Float>();
		List<WebElement> productPriceElementList = getListElements(driver, CategoryPageUI.PRODUCT_PRICE_TEXT);
		for (WebElement element : productPriceElementList) {
			productPriceUIList.add(Float.parseFloat(element.getText().replaceAll("[\\$,]", "")));
		}
		List<Float> productPriceSortList = new ArrayList<Float>(productPriceUIList);
		Collections.sort(productPriceSortList);
		Collections.reverse(productPriceSortList);
		return productPriceUIList.equals(productPriceSortList);
	}

	public void selectItemInDisplayDropdown(String option) {
		waitForElementClickable(driver, CategoryPageUI.DISPLAY_PER_PAGE_DROPDOWN);
		selectItemInDefaultDropdown(driver, CategoryPageUI.DISPLAY_PER_PAGE_DROPDOWN, option);
	}

	public void openPageNumberAtProductWrapper(String pagingNumber) {
		waitForElementClickable(driver, CategoryPageUI.PAGING_BY_PAGE_NUMBER, pagingNumber);
		clickToElement(driver, CategoryPageUI.PAGING_BY_PAGE_NUMBER, pagingNumber);
	}

	public boolean isPageNumberActive(String pagingNumber) {
		waitForElementVisibility(driver, CategoryPageUI.CURRENT_PAGING_BY_PAGE_NUMBER, pagingNumber);
		return isElementDisplayed(driver, CategoryPageUI.CURRENT_PAGING_BY_PAGE_NUMBER, pagingNumber);
	}

	public boolean isLessOrEquaProductItemlItemsDisplayedByQuantity(String quantityProduct) {
		waitForElementUnDisplayed(driver, CategoryPageUI.AJAX_PRODUCT_LOADING);
		return getElementsSize(driver, CategoryPageUI.PRODUCT_ITEM) <= Float.parseFloat(quantityProduct);
	}

	public boolean isNextIconDisplayedAtProductWrapper() {
		waitForElementVisibility(driver, CategoryPageUI.NEXT_ICON_PAGING);
		return isElementDisplayed(driver, CategoryPageUI.NEXT_ICON_PAGING);
	}

	public boolean isPreviousIconDisplayedAtProductWrapper() {
		waitForElementVisibility(driver, CategoryPageUI.PREVIOUS_ICON_PAGING);
		return isElementDisplayed(driver, CategoryPageUI.PREVIOUS_ICON_PAGING);
	}

	public boolean isPagingUnDisplayed() {
		return isElementUndisplayed(driver, CategoryPageUI.PAGING_ITEM);
	}

	public String getPriceByProductName(String productName) {
		waitForElementVisibility(driver, CategoryPageUI.DYNAMIC_PRICE_AT_PRODUCT_ITEM_BY_PRODUCT_NAME, productName);
		return getElementText(driver, CategoryPageUI.DYNAMIC_PRICE_AT_PRODUCT_ITEM_BY_PRODUCT_NAME, productName);
	}

	public void clickToAddToCompareButtonByProductName(String productName) {
		waitForElementClickable(driver, CategoryPageUI.ADD_TO_COMPARE_LIST_BUTTON_BY_PRODUCT_NAME, productName);
		clickToElement(driver, CategoryPageUI.ADD_TO_COMPARE_LIST_BUTTON_BY_PRODUCT_NAME, productName);
	}

	public UserCompareProductPageObject clickToProductComparisonLink() {
		waitForElementClickable(driver, CategoryPageUI.PRODUCT_COMPARISION_LINK_AT_BAR_NOTIFICATION);
		clickToElement(driver, CategoryPageUI.PRODUCT_COMPARISION_LINK_AT_BAR_NOTIFICATION);
		return PageGeneratorManager.getCompareProductPage(driver);
	}

	public boolean isProductNameDisplayed(String productName) {
		waitForElementVisibility(driver, CategoryPageUI.DYNAMIC_PRODUCT_NAME_AT_PRODUCT_ITEM, productName);
		return isElementDisplayed(driver, CategoryPageUI.DYNAMIC_PRODUCT_NAME_AT_PRODUCT_ITEM, productName);
	}

	public boolean isProductNameUnDisplayed(String productName) {
		return isElementUndisplayed(driver, CategoryPageUI.DYNAMIC_PRODUCT_NAME_AT_PRODUCT_ITEM, productName);
	}

}
