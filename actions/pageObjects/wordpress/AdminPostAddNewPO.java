package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.AdminPostAddNewPageUI;

public class AdminPostAddNewPO extends BasePage {
	private WebDriver driver;

	public AdminPostAddNewPO(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToAddNewPostTitle(String postTitleValue) {
		waitForElementVisibility(driver, AdminPostAddNewPageUI.POST_TITLE_TEXTBOX);
		sendkeyToElement(driver, AdminPostAddNewPageUI.POST_TITLE_TEXTBOX, postTitleValue);
	}

	public void enterToEditAddNewPostBody(String postBodyValue) {
		waitForElementClickable(driver, AdminPostAddNewPageUI.POST_BODY_BUTTON);
		clickToElement(driver, AdminPostAddNewPageUI.POST_BODY_BUTTON);
		
		waitForElementVisibility(driver, AdminPostAddNewPageUI.POST_BODY_TEXTBOX);
		sendkeyToElement(driver, AdminPostAddNewPageUI.POST_BODY_TEXTBOX, postBodyValue);

	}

	public void clickToPublishOrUpdateButton() {
		waitForElementClickable(driver, AdminPostAddNewPageUI.PUBLISH_BUTTON);
		clickToElement(driver, AdminPostAddNewPageUI.PUBLISH_BUTTON);

	}

	public void clickToConfirmPublishButton() {
		waitForElementClickable(driver, AdminPostAddNewPageUI.CONFIRM_PUBLISH_BUTTON);
		clickToElement(driver, AdminPostAddNewPageUI.CONFIRM_PUBLISH_BUTTON);

	}

	public boolean isPostPublishedMessageDisplayed(String publishedMessage) {
		waitForElementVisibility(driver, AdminPostAddNewPageUI.POST_PUBLISHED_MESSAGE, publishedMessage);
		return isElementDisplayed(driver, AdminPostAddNewPageUI.POST_PUBLISHED_MESSAGE, publishedMessage);
	}

	public AdminPostSearchPO openSearchPostPageUrl(String searchPostURL) {
		openPageUrl(driver, searchPostURL);
		return PageGeneratorManager.getAdminSearchPage(driver);
		
	}

}
