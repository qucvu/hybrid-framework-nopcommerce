package pageObjects.jQuery;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.jQuery.HomePageUI;

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
		return isElemenetDisplayed(driver, HomePageUI.ACTIVE_PAGINATION_PAGE_BY_PAGE_NUMBER, pageNumber);
	}

	public void enterToHeaderByLabel(String label, String value) {
		waitForElementVisibility(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, label);
		sendkeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL,value, label);
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


}
