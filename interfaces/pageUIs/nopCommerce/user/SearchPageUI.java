package pageUIs.nopCommerce.user;

public class SearchPageUI {
	public static final String SEARCH_INPUT_AT_SEARCH_FIELDSET= "css=input.search-text[id=q]";
	public static final String ERROR_MESSAGE_AT_SEARCH_RESULT= "css=div.search-results div.products-wrapper>div:not(.product-grid)";
	public static final String PRODUCT_ITEM_AT_SEARCH_RESULT= "css=div.item-box div.product-item";
	public static final String DYNAMIC_PRODUCT_ITEM_BY_PRODUCT_TITLE= "xpath=//a[text()='%s']/parent::h2/ancestor::div[@class='product-item']";
	public static final String DYNAMIC_CHECKBOX_AT_SEARCH_FIELDSET= "xpath=//label[contains(text(), '%s')]/preceding-sibling::input";

}
