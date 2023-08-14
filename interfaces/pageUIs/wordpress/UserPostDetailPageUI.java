package pageUIs.wordpress;

public class UserPostDetailPageUI {
	public static final String POST_TITLE_TEXT = "xpath=//article//h1[text()='%s']";
	public static final String POST_CURRENT_DATE_BY_POST_TITLE = POST_TITLE_TEXT + "/following-sibling::div//span[@class='posted-on']//time[text()='%s']";
	public static final String POST_BODY_TEXT_BY_POST_TITLE = POST_TITLE_TEXT + "/ancestor::header/following-sibling::div[@class='entry-content']/p[text()='%s']";
	public static final String POST_AUTHOR_BY_POST_TITLE = POST_TITLE_TEXT + "/following-sibling::div//span[contains(@class, 'author')]/a[text()='%s']";

}
