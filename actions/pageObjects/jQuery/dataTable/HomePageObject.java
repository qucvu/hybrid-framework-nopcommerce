package pageObjects.jQuery.dataTable;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.jQuery.dataTable.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void openPagingByPageNumber(String pageNumber) {
		waitForElementVisibility(driver, HomePageUI.PAGING_BY_PAGE_NUMBER, pageNumber);
		clickToElement(driver, HomePageUI.PAGING_BY_PAGE_NUMBER, pageNumber);
	}

	public boolean isPageNumberActive(String pageNumber) {
		waitForElementVisibility(driver, HomePageUI.ACTIVE_PAGINATION_PAGE_BY_PAGE_NUMBER, pageNumber);
		return isElementDisplayed(driver, HomePageUI.ACTIVE_PAGINATION_PAGE_BY_PAGE_NUMBER, pageNumber);
	}

	public void enterToHeaderByLabel(String label, String value) {
		waitForElementVisibility(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, label);
		sendkeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, value, label);
		pressKeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, Keys.ENTER, label);
	}

	public List<String> getValueEachRowAtAllPage() {
		int totalPage = getElementsSize(driver, HomePageUI.TOTAL_PAGINATION);
		List<String> allRowDataValues = new ArrayList<String>();
		for (int pageNumber = 1; pageNumber <= totalPage; pageNumber++) {
			openPagingByPageNumber(String.valueOf(pageNumber));
			List<WebElement> allRowEachPage = getListElements(driver, HomePageUI.ALL_ROW_EACH_PAGE); // ALL_ROW_EACH_PAGE
			for (WebElement element : allRowEachPage) {
				allRowDataValues.add(element.getText());
			}
		}

		return allRowDataValues;
	}
	
	public void enterToTextboxAtRowNumberByColumnName(String columnName, String rowNumber, String textValue) {
		int indexColumn = getElementsSize(driver, HomePageUI.INDEX_COLUMN_BY_COLUMN_NAME, columnName) + 1;
		waitForElementVisibility(driver, HomePageUI.TEXTBOX_AT_ROW_NUMBER_BY_COLUMN_INDEX, rowNumber, String.valueOf(indexColumn));
		sendkeyToElement(driver, HomePageUI.TEXTBOX_AT_ROW_NUMBER_BY_COLUMN_INDEX, textValue, rowNumber, String.valueOf(indexColumn));
	}

	public void clickToLoadData() {
		waitForElementClickable(driver, HomePageUI.LOAD_DATA_BUTTON);
		clickToElement(driver, HomePageUI.LOAD_DATA_BUTTON);
	}

	public void selectToDropdownAtRowNumberByColumnName(String columnName, String rowNumber, String textValue) {
		int indexColumn = getElementsSize(driver, HomePageUI.INDEX_COLUMN_BY_COLUMN_NAME, columnName) + 1;
		waitForElementClickable(driver, HomePageUI.DROPDOWN_AT_ROW_NUMBER_BY_COLUMN_INDEX, rowNumber, String.valueOf(indexColumn));
		selectItemInDefaultDropdown(driver, HomePageUI.DROPDOWN_AT_ROW_NUMBER_BY_COLUMN_INDEX, textValue, rowNumber, String.valueOf(indexColumn));
	}

	public void checkToCheckboxAtRowNumberByColumnName(String columnName, String rowNumber) {
		int indexColumn = getElementsSize(driver, HomePageUI.INDEX_COLUMN_BY_COLUMN_NAME, columnName) + 1;
		waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_ROW_NUMBER_AND_COLUMN_INDEX, rowNumber, String.valueOf(indexColumn));
		checkToDefaultCheckboxRadio(driver, HomePageUI.CHECKBOX_BY_ROW_NUMBER_AND_COLUMN_INDEX, rowNumber, String.valueOf(indexColumn));
		
	}

	public void unCheckToCheckboxAtRowNumberByColumnName(String columnName, String rowNumber) {
		int indexColumn = getElementsSize(driver, HomePageUI.INDEX_COLUMN_BY_COLUMN_NAME, columnName) + 1;
		waitForElementClickable(driver, HomePageUI.CHECKBOX_BY_ROW_NUMBER_AND_COLUMN_INDEX, rowNumber, String.valueOf(indexColumn));
		unCheckToDefaultCheckbox(driver, HomePageUI.CHECKBOX_BY_ROW_NUMBER_AND_COLUMN_INDEX, rowNumber, String.valueOf(indexColumn));

		
	}

	public void clickToIconAction(String rowNumber, String actionName) {
		waitForElementClickable(driver, HomePageUI.ICON_ACTION_AT_ROW_NUMBER_BY_NAME, rowNumber, actionName);
		clickToElement(driver, HomePageUI.ICON_ACTION_AT_ROW_NUMBER_BY_NAME, rowNumber, actionName);
		
	}

	public void clickToIconActionByContactName(String contactPerson, String actionName) {
		// TODO Auto-generated method stub
		
	}

}
