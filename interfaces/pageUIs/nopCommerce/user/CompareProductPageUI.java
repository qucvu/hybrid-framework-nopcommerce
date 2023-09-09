package pageUIs.nopCommerce.user;

public class CompareProductPageUI {
	public static final String PRODUCT_NAME_AT_COMPARISION_TABLE = "xpath=//tr[@class='product-name']//a[text()='%s']";
	public static final String COLUMN_INDEX_BY_PRODUCT_NAME = "xpath=//a[text()='%s']/parent::td/preceding-sibling::td";
	public static final String REMOVE_BUTTON_AT_COMPARISION_TABLE_BY_COLUMN_INDEX = "css=table tr.remove-product>td:nth-child(%s)";
	public static final String NAME_LABEL_AT_COMPARISION_TABLE_BY_PRODUCT_NAME = PRODUCT_NAME_AT_COMPARISION_TABLE + "/parent::td/preceding-sibling::td/label[text()='Name']";
	public static final String PRICE_LABEL_AT_COMPARISION_TABLE_BY_PRODUCT_PRICE = "XPATH=//td[text()='%s']/preceding-sibling::td/label[text()='Price']";
	public static final String CLEAR_LIST_LINK = "css=a.clear-list";
	public static final String EMPTY_PRODUCT_COMPARE_MESSAGE = "xpath=//div[@class='no-data' and text()='You have no items to compare.']";
}
