package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.UserPostSearchPageUI;

public class UserPostSearchPO extends BasePage {
	private WebDriver driver;
	
	public UserPostSearchPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isNothingFoundMessageDisplayed() {
		waitForElementVisibility(driver, UserPostSearchPageUI.NOTHING_FOUND_MESSAGE);
		return isElementDisplayed(driver, UserPostSearchPageUI.NOTHING_FOUND_MESSAGE);
	}
}
