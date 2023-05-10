package pageUIs.jQuery.uploadFile;

public class HomePageUI {
	public static final String UPLOAD_FILE = "xpath=//input[@type='file']";
	public static final String LOADED_FILE_BY_FILE_NAME = "xpath=//tbody//p[@class='name' and text()='%s']";
	public static final String LOAD_IMG_BY_FILE_NAME = "xpath=//a[@download='%s']/img";
	public static final String UPLOADED_FILE_BY_FILE_NAME = "xpath=//p[@class='name']//a[@download='%s']";
	
	public static final String UNALLOWED_LOADED_FILE_BY_FILE_NAME = "xpath=//p[text()='%s']/following-sibling::strong[text()='File type not allowed']";
	public static final String UPLOAD_FILE_BUTTON = "css=tbody button.start:not([disabled])";
	
	

	
	
}
