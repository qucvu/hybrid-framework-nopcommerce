package pageUIs.nopCommerce.user;

public class CategoryPageUI {
	public static final String CURRENT_ITEM_AT_BREADCRUMB_MENU = "css=div.breadcrumb strong.current-item";
	public static final String DYNAMIC_PRODUCT_NAME_AT_PRODUCT_ITEM = "xpath=//div[@class='product-item']//h2[@class='product-title']/a[text()='%s']";
	public static final String CATEGORY_TITLE = "css=div.category-page>.page-title";
	public static final String SORT_DROPDOWN = "css=select#products-orderby";
	public static final String PRODUCT_TITLE_TEXT = "css=div.product-item h2.product-title>a";
	public static final String PRODUCT_PRICE_TEXT = "css=div.product-item div.prices>span.price";
	public static final String DISPLAY_PER_PAGE_DROPDOWN = "css=select#products-pagesize";
	public static final String PRODUCT_ITEM = "css=div.products-wrapper div.product-item";
	public static final String PAGING_ITEM = "css=div.products-wrapper div.pager";
	public static final String PAGING_BY_PAGE_NUMBER = "xpath=//div[@class='pager']//li[@class='individual-page']/a[text()='%s']";
	public static final String CURRENT_PAGING_BY_PAGE_NUMBER = "xpath=//div[@class='pager']//li[@class='current-page']/span[text()='%s']";
	public static final String NEXT_ICON_PAGING = "xpath=//div[@class='pager']//li[@class='next-page']/a[text()='Next']";
	public static final String PREVIOUS_ICON_PAGING = "xpath=//div[@class='pager']//li[@class='previous-page']/a[text()='Previous']";
	public static final String AJAX_PRODUCT_LOADING= "css=div.ajax-products-busy";
	public static final String DYNAMIC_PRICE_AT_PRODUCT_ITEM_BY_PRODUCT_NAME= "xpath=//a[text()='%s']/parent::h2[@class='product-title']/following-sibling::div[@class='add-info']/div[@class='prices']/span";
	public static final String ADD_TO_COMPARE_LIST_BUTTON_BY_PRODUCT_NAME= "xpath=//a[text()='%s']/parent::h2[@class='product-title']/following-sibling::div[@class='add-info']/div[@class='buttons']/button[@title='Add to compare list']";
	public static final String PRODUCT_COMPARISION_LINK_AT_BAR_NOTIFICATION= "CSS=div#bar-notification a[href='/compareproducts']";

}
