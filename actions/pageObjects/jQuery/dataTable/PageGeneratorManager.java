package pageObjects.jQuery.dataTable;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static HomePageObject getJQueryHomePage(WebDriver driver) {
		return new HomePageObject(driver);		
	}
	






}
