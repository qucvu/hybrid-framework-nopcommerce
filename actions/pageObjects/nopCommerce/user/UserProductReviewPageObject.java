package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.ProductReviewPageUI;

public class UserProductReviewPageObject extends BasePage {
	WebDriver driver;
	
	public UserProductReviewPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getTitleProductForOnReviewPage() {
		waitForElementVisibility(driver, ProductReviewPageUI.TITLE_PRODUCT_REVIEW);
		return getElementText(driver, ProductReviewPageUI.TITLE_PRODUCT_REVIEW);
	}

	public String getProductReviewTitleByFirstName(String firstName) {
		waitForElementVisibility(driver, ProductReviewPageUI.REVIEW_TITLE_BY_FIRST_NAME, firstName);
		return getElementText(driver, ProductReviewPageUI.REVIEW_TITLE_BY_FIRST_NAME, firstName);
	}

	public String getProductReviewRateByFirstName(String firstName) {
		String widthPercentOnReview = "";
		waitForElementVisibility(driver, ProductReviewPageUI.REVIEW_RATE_BY_FIRST_NAME, firstName);
		widthPercentOnReview = getElementAttribute(driver, "style", ProductReviewPageUI.REVIEW_RATE_BY_FIRST_NAME, firstName);
		int indexPercent = widthPercentOnReview.indexOf("%");
		widthPercentOnReview = widthPercentOnReview.substring(7, indexPercent);
		switch (widthPercentOnReview) {
		case "20":
			return "Bad";
		case "40":
			return "Not good";
		case "60":
			return "Not bad but also not excellent";
		case "80":
			return "Good";
		case "100":
			return "Excellent";
		default:
			throw new RuntimeException("Invalid Width percent for Review Rate");
		}
	}

	public String getProductReviewContentByFirstName(String firstName) {
		waitForElementVisibility(driver, ProductReviewPageUI.REVIEW_CONTENT_BY_FIRST_NAME, firstName);
		return getElementText(driver, ProductReviewPageUI.REVIEW_CONTENT_BY_FIRST_NAME, firstName);
	}
	
	
	

}
