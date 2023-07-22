package pageObjects.jQuery.uploadFiles;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.jQuery.uploadFile.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isFileLoadedByFileName(String fileName) {
		waitForElementVisibility(driver, HomePageUI.LOADED_FILE_BY_FILE_NAME, fileName);
		return isElementDisplayed(driver, HomePageUI.LOADED_FILE_BY_FILE_NAME, fileName);

	}

	public boolean isFileLinkUpoadedByFileName(String fileName) {
		waitForElementVisibility(driver, HomePageUI.UPLOADED_FILE_BY_FILE_NAME, fileName);
		return isElementDisplayed(driver, HomePageUI.UPLOADED_FILE_BY_FILE_NAME, fileName);

	}

	public boolean isImgFileUploadedByFileName(String fileName) {
		waitForElementVisibility(driver, HomePageUI.LOAD_IMG_BY_FILE_NAME, fileName);
		return isImageLoaded(driver, HomePageUI.LOAD_IMG_BY_FILE_NAME, fileName);
	}

	public boolean isUnallowedLinkByFileName(String fileName) {
		waitForElementVisibility(driver, HomePageUI.UNALLOWED_LOADED_FILE_BY_FILE_NAME, fileName);
		return isElementDisplayed(driver, HomePageUI.UNALLOWED_LOADED_FILE_BY_FILE_NAME, fileName);
	}

	public void clickToUploadFile() {
		waitForAllElementVisibility(driver, HomePageUI.UPLOAD_FILE_BUTTON);
		List<WebElement> startButtons = getListElements(driver, HomePageUI.UPLOAD_FILE_BUTTON);
		for (WebElement startButton : startButtons) {
			startButton.click();
			sleepInSecond(1);
		}
	}

}
