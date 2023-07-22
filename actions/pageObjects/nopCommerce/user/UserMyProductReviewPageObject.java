package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.MyProducReviewPageUI;

public class UserMyProductReviewPageObject extends BasePage {
	private WebDriver driver;

	public UserMyProductReviewPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isProductReviewTitleDisplayed(String reviewTitle) {
		waitForElementVisibility(driver, MyProducReviewPageUI.DYNAMIC_REVIEW_TITLE_BY_TITLE, reviewTitle);
		return isElementDisplayed(driver, MyProducReviewPageUI.DYNAMIC_REVIEW_TITLE_BY_TITLE, reviewTitle);
	}

	public boolean isContentProductReviewDisplayed(String reviewContent) {
		waitForElementVisibility(driver, MyProducReviewPageUI.DYNAMIC_REVIEW_CONTENT_BY_TEXT, reviewContent);
		return isElementDisplayed(driver, MyProducReviewPageUI.DYNAMIC_REVIEW_CONTENT_BY_TEXT, reviewContent);
	}

	public boolean isProductReviewRateDisplayedByTitle(String reviewRate, String reviewTitle) {
		String reviewRateWidth = "";
		switch (reviewRate) {
		case "Bad":
			reviewRateWidth = "20";
			break;
		case "Not good":
			reviewRateWidth = "40";
			break;
		case "Not bad but also not excellent":
			reviewRateWidth = "60";
			break;
		case "Good":
			reviewRateWidth = "80";
			break;
		case "Excellent":
			reviewRateWidth = "100";
			break;
		}
		waitForElementVisibility(driver, MyProducReviewPageUI.DYNAMIC_REVIEW_RATE_BY_TITLE_AND_TEXT, reviewTitle, reviewRateWidth);
		return isElementDisplayed(driver, MyProducReviewPageUI.DYNAMIC_REVIEW_RATE_BY_TITLE_AND_TEXT, reviewTitle, reviewRateWidth);
	}

}
