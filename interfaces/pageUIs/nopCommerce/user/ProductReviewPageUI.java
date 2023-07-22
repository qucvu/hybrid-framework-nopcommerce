package pageUIs.nopCommerce.user;

public class ProductReviewPageUI {
	
	public static final String TITLE_PRODUCT_REVIEW = "css=div.page-title a";
	private static final String DYNAMIC_PRODUCT_REVIEW_ITEM_BY_FIRST_NAME = "XPATH=//span[text()='%s']/parent::span/ancestor::div[@class='product-review-item']";
	public static final String REVIEW_TITLE_BY_FIRST_NAME = DYNAMIC_PRODUCT_REVIEW_ITEM_BY_FIRST_NAME + "//div[@class='review-title']//strong";
	public static final String REVIEW_RATE_BY_FIRST_NAME = DYNAMIC_PRODUCT_REVIEW_ITEM_BY_FIRST_NAME + "//div[@class='rating']/div";
	public static final String REVIEW_CONTENT_BY_FIRST_NAME = DYNAMIC_PRODUCT_REVIEW_ITEM_BY_FIRST_NAME + "//div[@class='review-content']//div[@class='text-body']";
	

}
