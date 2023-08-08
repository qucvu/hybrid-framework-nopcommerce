package com.wordpress.admin;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.wordpress.admin.AdminLoginPO;
import pageObjects.wordpress.admin.PageGeneratorManager;

public class Post_01_Create_Search_Read_Delete_Update extends BaseTest {

	@Parameters({"browser", "urlAdmin"})
	@BeforeClass
	public void beforeClass(String browserName, String urlAdmin) {
		adminUserName = "vunguyen";
		adminPassword = "Nguyenquocvu.111";
		log.info("Step 01: ");
		driver = getBrowserDriver(browserName, urlAdmin);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		
		adminLoginPage.inputToTextboxById(driver, "user_login", adminUserName);
		adminLoginPage.inputToTextboxById(driver, "user_pass", adminPassword);
		adminLoginPage.pressEnterToTextboxById(driver, "user_pass");
		adminLoginPage.sleepInSecond(5);
		
	}

	@Test
	public void Post_01_Create_New_Post(Method method) {
		
	}

	@Test
	public void Post_02_Search_Post(Method method) {
		

	}

	@Test
	public void Post_03_View_Post(Method method) {
		

	}
	@Test
	public void Post_04_Edit_Post(Method method) {
		

	}
	@Test
	public void Post_05_Delete_Post(Method method) {
		

	}
	

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		 closeBrowserDriver();
	}

	private WebDriver driver;
	private String adminUserName, adminPassword;
	private AdminLoginPO adminLoginPage;

}
