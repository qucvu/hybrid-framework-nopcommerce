package pageUIs.wordpress;

public class UserHomePageUI {
	public static final String POST_TITLE_TEXT = "xpath=//article//h2/a[text()='%s']"; 
	public static final String POST_CURRENT_DATE_BY_POST_TITLE = POST_TITLE_TEXT +  "/ancestor::header/div/span[@class='posted-on']//time[text()='%s']"; 
	public static final String POST_BODY_TEXT_BY_POST_TITLE = POST_TITLE_TEXT +  "/ancestor::header/following-sibling::div[@class='entry-content']/p[text()='%s']"; 
	public static final String POST_AUTHOR_BY_POST_TITLE = POST_TITLE_TEXT +  "/ancestor::header//span[contains(@class, 'author')]/a[text()='%s']"; 
	public static final String SEARCH_TEXTBOX = "css=aside#secondary form.search-form input.search-field"; 
	public static final String SEARCH_BUTTON = "css=aside#secondary input.search-submit"; 
}
