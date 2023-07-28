package com.wordpress.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;

public class Post_01_Create_Search_Read_Delete_Update extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName, Method method) {
		driver = getBrowserDriver(browserName, GlobalConstants.PORTAL_PAGE_URL);

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

}
