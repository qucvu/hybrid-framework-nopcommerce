package pageObjects.nopCommerce.user;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.SearchPageUI;

public class UserSearchPageObject extends BasePage {
	private WebDriver driver;

	public UserSearchPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getErrorMessageAtSearchResult() {
		waitForElementVisibility(driver, SearchPageUI.ERROR_MESSAGE_AT_SEARCH_RESULT);
		return getElementText(driver, SearchPageUI.ERROR_MESSAGE_AT_SEARCH_RESULT);
	}

	public int getProductQuantityAtSearchResult() {
		waitForAllElementVisibility(driver, SearchPageUI.PRODUCT_ITEM_AT_SEARCH_RESULT);
		return getElementsSize(driver, SearchPageUI.PRODUCT_ITEM_AT_SEARCH_RESULT);
	}

	public void pressEnterToSearchInputOnSearchFieldset(){
		pressKeyToElement(driver, SearchPageUI.SEARCH_INPUT_AT_SEARCH_FIELDSET, Keys.ENTER);
//		waitForCurrentPageStaleness(driver);
		sleepInSecond(1);
	}
	
	public boolean isDynamicProductItemDisplayedByProductTitle(String productTitle) {
		waitForElementVisibility(driver, SearchPageUI.DYNAMIC_PRODUCT_ITEM_BY_PRODUCT_TITLE, productTitle);
		return isElementDisplayed(driver, SearchPageUI.DYNAMIC_PRODUCT_ITEM_BY_PRODUCT_TITLE, productTitle);
	}

	public void unCheckToCheckboxByLabelAtSearchFieldset(String labelName) {
		waitForElementClickable(driver, SearchPageUI.DYNAMIC_CHECKBOX_AT_SEARCH_FIELDSET, labelName);
		unCheckToDefaultCheckbox(driver, SearchPageUI.DYNAMIC_CHECKBOX_AT_SEARCH_FIELDSET, labelName);
	}
	
	public void checkToCheckboxByLabelAtSearchFieldset(String labelName) {
		waitForElementClickable(driver, SearchPageUI.DYNAMIC_CHECKBOX_AT_SEARCH_FIELDSET, labelName);
		checkToDefaultCheckboxRadio(driver, SearchPageUI.DYNAMIC_CHECKBOX_AT_SEARCH_FIELDSET, labelName);
	}


	

	
	
}
