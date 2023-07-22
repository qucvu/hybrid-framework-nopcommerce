package pageUIs.nopCommerce.user;

public class MyProducReviewPageUI {
	public final static String DYNAMIC_REVIEW_TITLE_BY_TITLE = "xpath=//div[@class='review-title']//strong[text()='%s']";
	public final static String DYNAMIC_REVIEW_CONTENT_BY_TEXT = "xpath=//div[@class='review-content']//div[@class='review-text' and text()='%s']";
	public final static String DYNAMIC_REVIEW_RATE_BY_TITLE_AND_TEXT = "xpath=//strong[text()='%s']/parent::div/following-sibling::div/div/div[@style='width:%s%%']";

	
}
