package pageObjects.wordpress;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.wordpress.AdminDashboardPageUI;

public class AdminDashboardPO extends BasePage {
	private WebDriver driver;

	public AdminDashboardPO(WebDriver driver) {
		this.driver = driver;
	}
	

	public boolean isDashboardTitleDisplayed() {
		waitForElementVisibility(driver, AdminDashboardPageUI.DASHBOARD_TITLE);
		return isElementDisplayed(driver, AdminDashboardPageUI.DASHBOARD_TITLE);
	}


	public AdminPostSearchPO clickToPostMenuLink() {
		waitForElementClickable(driver, AdminDashboardPageUI.POST_MENU_LINK);
		clickToElement(driver, AdminDashboardPageUI.POST_MENU_LINK);
		return PageGeneratorManager.getAdminSearchPage(driver);
	}
	public AdminUserPO clickToUserLink() {
		waitForElementClickable(driver, AdminDashboardPageUI.USER_LINK);
		clickToElement(driver, AdminDashboardPageUI.USER_LINK);
		return PageGeneratorManager.getAadminUserPage(driver);
		
	}

}
