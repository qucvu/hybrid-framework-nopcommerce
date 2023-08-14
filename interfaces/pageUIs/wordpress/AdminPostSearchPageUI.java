package pageUIs.wordpress;

public class AdminPostSearchPageUI {
	public static final String ADD_NEW_LINK = "CSS=a.page-title-action";
	public static final String SEARCH_TEXTBOX = "css=input#post-search-input";
	public static final String SEARCH_POSTS_BUTTON = "css=input#search-submit";
	public static final String HEADER_TABLE_INDEX_BY_HEADER_ID = "xpath=//table[contains(@class, 'posts')]/thead//th[@id='%s']/preceding-sibling::*";
	public static final String TABLE_ROW_VALUE_BY_HEADER_INDEX = "xpath=//table[contains(@class, 'posts')]/tbody/tr/*[%s]//a[text()='%s']";

}
