package pageUIs.nopCommerce.user;

public class BasePageNopCommerceUI {
	public static final String CURRENT_PAGE = "xpath=//html";
	public static final String CUSTOMER_INFO_LINK = "xpath=//li[contains(@class, 'customer-info')]/a[text()='Customer info']";

	public static final String ADDRESS_LINK = "xpath=//li[contains(@class, 'customer-addresses')]/a[text()='Addresses']";

	public static final String REWARD_POINT_LINK = "xpath=//li[contains(@class, 'reward-points')]/a[text()='Reward points']";

	public static final String MY_PRODUCT_REVIEW_LINK = "xpath=//li[contains(@class, 'customer-reviews')]/a[text()='My product reviews']";
	public static final String LOGOUT_LINK_USER_PAGE = "xpath=//a[contains(@class, 'ico-logout')]";
	public static final String LOGOUT_LINK_ADMIN_PAGE = "XPATH=//a[text()='Logout']";
	public static final String MY_ACCOUNT_LINK_USER_PAGE = "xpath=//a[@class='ico-account' and text() = 'My account']";
	public static final String WISHLIST_LINK_USER_PAGE = "css=a.ico-wishlist";
	public static final String SHOPPING_CART_LINK_USER_PAGE = "css=a.ico-cart";
	public static final String CLOSE_ICON_BAR_NOTIFICATION = "css=span.close";

	// Pattern Object
	public static final String DYNAMIC_LOCATOR_AT_MY_ACCOUNT_PAGE = "xpath=//a[text()='%s']";
	public static final String DYNAMIC_TEXTBOX_BY_ID = "css=input#%s";
	public static final String DYNAMIC_TEXTAREA_BY_ID = "css=textarea#%s";
	public static final String DYNAMIC_BUTTON_BY_TEXT = "xpath=//button[contains(.,'%s')]";
	public static final String DYNAMIC_BUTTON_BY_ID = "css=button#%s";
	public static final String DYNAMIC_DROPDOWN_BY_NAME = "css=select[name='%s']";
	public static final String DYNAMIC_RADIO_BY_LABEL = "xpath=//label[contains(text(),'%s')]/preceding-sibling::input";
	public static final String DYNAMIC_RADIO_BY_ARIA_LABEL = "css=input[type=radio][aria-label='%s']";
	public static final String DYNAMIC_CHECKBOX_BY_LABEL = "xpath=//label[contains(text(), '%s')]/following-sibling::input";
	
	
	// dynamic locator
	public static final String DYNAMIC_MESSAGE_AT_BAR_NOTIFICATION_BY_MESSAGE = "xpath=//div[@id='bar-notification']//p[@class='content' and contains(.,'%s')]";
	public static final String DYNAMIC_PRODUCT_CATEGORY_LINK_BY_NAME= "xpath=//ul[@class='top-menu notmobile']//a[contains(text(), '%s')]";
	public static final String DYNAMIC_SUB_PRODUCT_CATEGORY_LINK_BY_NAME= "xpath=//ul[@class='top-menu notmobile']//ul[contains(@class,'sublist')]//a[contains(text(), '%s')]";
	public static final String DYNAMIC_LINK_ON_FOOTER_BY_PAGE_NAME= "xpath=//div[contains(@class, 'footer-block')]//ul[@class='list']//a[text()='%s']";
	
	// ADMIN SITE
	public static final String DYNAMIC_LINK_ON_SIDEBAR_AT_ADMIN_PAGE_BY_NAME= "xpath=//li[contains(@class,'menu-open')]//p[contains(text(), '%s')]/parent::a/i[contains(@class,'fa-dot-circle')]";
	public static final String ACTIVE_LINK_ON_SIDEBAR_AT_ADMIN_PAGE_BY_NAME= "xpath=//li[contains(@class,'menu-open')]//p[contains(text(), '%s')]/parent::a[contains(@class,'active')]";
	public static final String AJAX_LOADING_AT_ADMIN_PAGE= "css=div#ajaxBusy";
	public static final String DYNAMIC_CHECKBOX_BY_LABEL_AT_ADMIN_PAGE= "xpath=//label[text()='%s']/ancestor::div[@class='form-group row']//input";
	public static final String SEARCH_TITLE_ROW = "css=div.search-row";
	public static final String DYNAMIC_CARD_TITLE_BY_ID= "css=nop-card div#%s";
	public static final String DYNAMIC_HEADER_LINK_BY_NAME = "xpath=//p[contains(text(), '%s')]/parent::a[@class='nav-link']";
	public static final String OPENED_MENU_ITEM_BY_HEADER_NAME = "xpath=//p[contains(text(),'%s')]/ancestor::li[contains(@class,'menu-open')]";
	public static final String ALL_CLOSE_OPTION_ON_DROP_DOWN_AT_ADMIN_PAGE_BY_LABEL = "xpath=//label[text()='%s']/parent::div/parent::div/following-sibling::div//ul//span[@title='delete']";
	public static final String ALL_OPTION_ON_DROP_DOWN_AT_ADMIN_PAGE_BY_LABEL = ALL_CLOSE_OPTION_ON_DROP_DOWN_AT_ADMIN_PAGE_BY_LABEL + "/preceding-sibling::span[text()='%s']";
	public static final String SUCCESS_MESSAGE_ON_THE_TOP_AT_ADMIN_PAGE = "xpath=//div[contains(@class, 'alert-success') and contains(., '%s')]";

	
	public static final String PRODUCT_ITEM_QUANTITY_ON_RESULT_TABLE_BY_TABLE_ID = "css=table#%s tbody tr";
	public static final String DYNAMIC_CELL_AT_ROW_NUMBER_TABLE_ID_AND_BY_COLUMN_INDEX = "xpath=//table[@id='%s']//tbody/tr[%s]/td[%s]";
	public static final String ALL_ROW_DATA_BY_TABLE_ID_AND_COLUMN_INDEX = "xpath=//table[@id='%s']//tbody/tr/td[%s]";
	public static final String INDEX_COLUMN_BY_HEADER = "xpath=//div[@class='dataTables_scroll']//th[text()='%s']/preceding-sibling::th";
	public static final String NO_DATA_MESSAGE_ON_TABLE_RESULT_BY_TABLE_ID = "xpath=//table[@id='%s']//td[@class='dataTables_empty' and text()='No data available in table']";



}
