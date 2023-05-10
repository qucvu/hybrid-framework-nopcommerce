package com.jquery;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.uploadFiles.HomePageObject;
import pageObjects.jQuery.uploadFiles.PageGeneratorManager;

public class Level_11_Upload_Files extends BaseTest {
	String computerFileName = "computer.jpg";
	String mountainFileName = "mountains.jpg";
	String beachFileName = "beach.avif";
	String[] multipleFileName = {computerFileName, mountainFileName, beachFileName}; 
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		
		driver = getBrowserDriver(browserName, url);
		homePage = PageGeneratorManager.getJQueryUpLoadHomePage(driver);

	}

	 @Test
	public void Upload_01_One_File() {
		homePage.uploadFilesByFileName(driver,computerFileName);
		homePage.sleepInSecond(2);
		
		homePage.uploadFilesByFileName(driver, mountainFileName, beachFileName);
		homePage.sleepInSecond(2);

		Assert.assertTrue(homePage.isFileLoadedByFileName(computerFileName));
		Assert.assertTrue(homePage.isFileLoadedByFileName(mountainFileName));
		Assert.assertTrue(homePage.isUnallowedLinkByFileName(beachFileName));
		
		
		homePage.clickToUploadFile();
		Assert.assertTrue(homePage.isImgFileUploadedByFileName(computerFileName));
		Assert.assertTrue(homePage.isFileLinkUpoadedByFileName(mountainFileName));


		
	}	
	@Test
	public void Upload_02_Multiple_File() {
		homePage.refreshCurrentPage(driver);
		homePage.uploadFilesByFileName(driver,multipleFileName);

		Assert.assertTrue(homePage.isFileLoadedByFileName(computerFileName));
		Assert.assertTrue(homePage.isUnallowedLinkByFileName(beachFileName));
		
		homePage.clickToUploadFile();
		Assert.assertTrue(homePage.isImgFileUploadedByFileName(computerFileName));
		Assert.assertTrue(homePage.isFileLinkUpoadedByFileName(mountainFileName));
	}

	public int generateRandomNumber() {
		return new Random().nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
		 driver.quit();
	}

	private WebDriver driver;
	private HomePageObject homePage;
}
