package pageUIs.nopCommerce.user;

public class BasePageNopCommerceUI {
	public static final String CUSTOMER_INFO_LINK = "xpath=//li[contains(@class, 'customer-info')]/a[text()='Customer info']";
	
	public static final String ADDRESS_LINK = "xpath=//li[contains(@class, 'customer-addresses')]/a[text()='Addresses']";
	
	public static final String REWARD_POINT_LINK = "xpath=//li[contains(@class, 'reward-points')]/a[text()='Reward points']";
	
	public static final String MY_PRODUCT_REVIEW_LINK = "xpath=//li[contains(@class, 'customer-reviews')]/a[text()='My product reviews']";
	public static final String LOGOUT_LINK_USER_PAGE = "xpath=//a[contains(@class, 'ico-logout')]";
	public static final String LOGOUT_LINK_ADMIN_PAGE = "XPATH=//a[text()='Logout']";
	
	// Pattern Object
	public static final String DYNAMIC_LOCATOR_AT_MY_ACCOUNT_PAGE = "xpath=//a[text()='%s']";
	public static final String DYNAMIC_TEXTBOX_BY_ID = "css=input#%s";
	public static final String DYNAMIC_BUTTON_BY_TEXT = "xpath=//button[text()='%s']";
	public static final String DYNAMIC_DROPDOWN_BY_NAME = "css=select[name=%s]";
	public static final String DYNAMIC_RADIO_BY_LABEL = "xpath=//label[text()='%s']/preceding-sibling::input";
	public static final String DYNAMIC_CHECKBOX_BY_LABEL = "xpath=//label[contains(text(), '%s')]/following-sibling::input";
	

}
