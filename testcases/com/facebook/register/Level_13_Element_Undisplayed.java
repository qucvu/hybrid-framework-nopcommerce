package com.facebook.register;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.facebook.LoginPageObject;
import pageObjects.facebook.PageGeneratorManager;

//@Listeners(commons.MethodListener.class)
public class Level_13_Element_Undisplayed extends BaseTest {

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		loginPage = PageGeneratorManager.getLoginPage(driver);
	}

	@Test
	public void TC_01_Verify_Element_Displayed() {
		loginPage.clickToCreateNewAccountLink();

		verifyTrue(loginPage.isEmailAdddressTextboxDisplayed());

		loginPage.enterToEmailTextbox("geni@gmail.com");
		verifyTrue(loginPage.isConfirmEmailAdddressTextboxDisplayed());

	}

	@Test
	public void TC_02_Verify_Element_Undisplayed_In_Dom() {
		loginPage.enterToEmailTextbox("");
		verifyTrue(loginPage.isConfirmEmailAdddressTextboxUnDisplayed());
	}

	@Test
	public void TC_03_Verify_Element_Undisplayed_Not_In_Dom() {
		loginPage.clickToCloseImg();
		verifyTrue(loginPage.isConfirmEmailAdddressTextboxUnDisplayed());
	}

	public int generateRandomNumber() {
		return new Random().nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private LoginPageObject loginPage;
}
