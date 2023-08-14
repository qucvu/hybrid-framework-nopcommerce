package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.AdminPostSearchPageUI;

public class AdminPostSearchPO extends BasePage {
	private WebDriver driver;

	public AdminPostSearchPO(WebDriver driver) {
		this.driver = driver;
	}

	public AdminPostAddNewPO clickToAddNewButton() {
		waitForElementClickable(driver, AdminPostSearchPageUI.ADD_NEW_LINK);
		clickToElement(driver, AdminPostSearchPageUI.ADD_NEW_LINK);
		return PageGeneratorManager.getAdminPostAddNewPage(driver);
	}


	public void enterToSearchTextbox(String postTitle) {
		waitForElementVisibility(driver, AdminPostSearchPageUI.SEARCH_TEXTBOX);
		sendkeyToElement(driver, AdminPostSearchPageUI.SEARCH_TEXTBOX, postTitle);
	}

	public void clickToSearchPostButton() {
		waitForElementClickable(driver, AdminPostSearchPageUI.SEARCH_POSTS_BUTTON);
		clickToElement(driver, AdminPostSearchPageUI.SEARCH_POSTS_BUTTON);

		
	}

	public boolean isPostSearchTableDisplayed(String headerId, String postCellData) {
		int headerIndex = getElementsSize(driver, AdminPostSearchPageUI.HEADER_TABLE_INDEX_BY_HEADER_ID, headerId) + 1;
		waitForElementVisibility(driver, AdminPostSearchPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(headerIndex), postCellData);
		return isElementDisplayed(driver, AdminPostSearchPageUI.TABLE_ROW_VALUE_BY_HEADER_INDEX, String.valueOf(headerIndex), postCellData);
	}


}
