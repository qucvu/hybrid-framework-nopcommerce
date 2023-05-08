package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;
import pageUIs.nopCommerce.user.HomePageUI;

public class HomePageObject extends BasePageFactory {
	private WebDriver driver;	
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// pageUI
	@CacheLookup
	@FindBy(how= How.XPATH, using ="//a[@class='ico-register']")
	private WebElement registerLink;
	
	@FindBy(css = "a.ico-login")
	private WebElement loginLink;

	@CacheLookup
	@FindBy(xpath = "//a[@class='ico-account' and text() = 'My account']")
	private WebElement myAccountLink;
	
	
	// page Object / action
	public void clickToRegisterLink() {
		waitForElementClickable(driver, registerLink);
		clickToElement(driver, registerLink);
	}
	
	public void clickToLoginLink() {
		waitForElementClickable(driver, loginLink);
		clickToElement(driver, loginLink);

	}
	
	public boolean isMyAccountDisplayed() {
		waitForElementVisibility(driver, myAccountLink);
		return isElemenetDisplayed(driver, myAccountLink);
	}



}
