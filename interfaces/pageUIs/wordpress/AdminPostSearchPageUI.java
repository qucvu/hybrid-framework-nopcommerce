package pageUIs.wordpress;

public class AdminPostSearchPageUI {
	public static final String ADD_NEW_LINK = "CSS=a.page-title-action";
	public static final String SEARCH_TEXTBOX = "css=input#post-search-input";
	public static final String SEARCH_POSTS_BUTTON = "css=input#search-submit";
	public static final String HEADER_TABLE_INDEX_BY_HEADER_ID = "xpath=//table[contains(@class, 'posts')]/thead//th[@id='%s']/preceding-sibling::*";
	public static final String TABLE_ROW_VALUE_BY_HEADER_INDEX = "xpath=//table[contains(@class, 'posts')]/tbody/tr/*[%s]//a[text()='%s']";
	public static final String ROW_TITLE_DETAIL_BY_TITLE_NAME = "xpath=//table[contains(@class, 'posts')]/tbody//a[@class='row-title' and text()='%s']";
	public static final String ROW_CHECKBOX_BY_TITLE_NAME = "xpath=//a[@class='row-title' and text()='%s']/ancestor::td/preceding-sibling::th//input";
	public static final String BULK_ACTION_DROPDOWN = "CSS=select#bulk-action-selector-top";
	public static final String APPLY_ACTION = "CSS=input#doaction";
	public static final String MOVE_TO_TRASH_MESSAGE = "xpath=//div[@id='message']/p[contains(text(), '%s')]";
	public static final String NO_POST_FOUND_MESSAGE = "xpath=//table//tr[@class='no-items']//td[text()='No posts found.']";

}
