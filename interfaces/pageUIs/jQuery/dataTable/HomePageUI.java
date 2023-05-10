package pageUIs.jQuery.dataTable;

public class HomePageUI {
	public static final String PAGING_BY_PAGE_NUMBER = "xpath=//li[@class='qgrd-pagination-page']/a[text()='%s']";
	public static final String ACTIVE_PAGINATION_PAGE_BY_PAGE_NUMBER = "xpath=//a[@class='qgrd-pagination-page-link active' and text()='%s']";
	public static final String HEADER_TEXTBOX_BY_LABEL = "xpath=//div[text()='%s']/parent::div/following-sibling::input";
	public static final String ALL_ROW_EACH_PAGE = "xpath=//tbody/tr";
	public static final String ALL_ROW_COUNTRY_EACH_PAGE = "xpath=//tbody/tr/td[@data-key='country']";
	public static final String TOTAL_PAGINATION = "css=li.qgrd-pagination-page";
	public static final String INDEX_COLUMN_BY_COLUMN_NAME = "xpath=//thead//th[text()='%s']/preceding-sibling::th";
	
	public static final String TEXTBOX_AT_ROW_NUMBER_BY_COLUMN_INDEX = "XPATH=//tbody/tr[%s]/td[%s]/input";
	public static final String LOAD_DATA_BUTTON = "id=load";
	public static final String DROPDOWN_AT_ROW_NUMBER_BY_COLUMN_INDEX = "XPATH=//tbody/tr[%s]/td[%s]//select";
	public static final String CHECKBOX_BY_ROW_NUMBER_AND_COLUMN_INDEX = "XPATH=//tbody/tr[%s]/td[%s]//input[@type='checkbox']";
	public static final String ICON_ACTION_AT_ROW_NUMBER_BY_NAME= "xpath=//tbody/tr[%s]/td//p[@class='control']/button[@title='%s']";

}
