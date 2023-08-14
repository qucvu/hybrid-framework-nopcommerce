package com.wordpress.admin;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.wordpress.AdminDashboardPO;
import pageObjects.wordpress.AdminLoginPO;
import pageObjects.wordpress.AdminPostAddNewPO;
import pageObjects.wordpress.AdminPostSearchPO;
import pageObjects.wordpress.PageGeneratorManager;
import pageObjects.wordpress.UserHomePO;
import pageObjects.wordpress.UserPostDetailPO;

public class Post_01_Create_Search_Read_Delete_Update extends BaseTest {

	@Parameters({ "browser", "urlAdmin", "urlUser" })
	@BeforeClass
	public void beforeClass(String browserName, String urlAdmin, String urlEndUser) {
		adminUserName = "vunguyen";
		adminPassword = "Nguyenquocvu.111";
		postTitle = "Live coding Title " + generateRandomNumber();
		postBody = "Live coding Body " + generateRandomNumber();
		authorName = "Vu Admin";
		this.adminUrl = urlAdmin;
		this.endUserUrl = urlEndUser;
		currentDay = getCurrentDay();

		log.info("Pre-condition - Step 01: Open browser and admin site");
		driver = getBrowserDriver(browserName, this.adminUrl);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

		log.info("Pre-conditions - Step 02: Enter Username textbox with value: " + adminUserName);
		adminLoginPage.enterToUserNameTextbox(adminUserName);

		log.info("Pre-conditions - Step 03: Enter Password textbox with value: " + adminPassword);
		adminLoginPage.enterToPasswordTextbox(adminPassword);

		log.info("Pre-conditions - Step 03: Click to 'Log in' button");
		adminDashboardPage = adminLoginPage.clickToLoginButton();

		log.info("Pre-condition - Step 03: Verify that Navigate to Admin Dashboard page successfully");
		verifyTrue(adminDashboardPage.isDashboardTitleDisplayed());

	}

	@Test
	public void Post_01_Create_New_Post(Method method) {
		log.info("Create Post - Step 01: Click to 'Post' Menu link");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();

		log.info("Create Post - Step 02: Get 'Search Posts' page Url");
		searchPostURL = adminPostSearchPage.getCurrentUrl(driver);

		log.info("Create Post - Step 02: Click to 'Add New' button");
		adminPostAddNewPage = adminPostSearchPage.clickToAddNewButton();

		log.info("Create Post - Step 03: Enter to post title");
		adminPostAddNewPage.enterToAddNewPostTitle(postTitle);

		log.info("Create Post - Step 04: Enter to body");
		adminPostAddNewPage.enterToAddNewPostBody(postBody);

		log.info("Create Post - Step 05: Click to 'Publish' button");
		adminPostAddNewPage.clickToPublishButton();

		log.info("Create Post - Step 05: Click to Confirm 'Publish' button");
		adminPostAddNewPage.clickToConfirmPublishButton();

		log.info("Create Post - Step 05: Verify 'Posted Published' message is displayed");
		verifyTrue(adminPostAddNewPage.isPostPublishedMessageDisplayed("Post published."));

	}

	@Test
	public void Post_02_Search_And_View_Post(Method method) {
		log.info("Search Post - Step 01: Click to 'Publish' button");
		adminPostSearchPage = adminPostAddNewPage.openSearchPostPageUrl(searchPostURL);

		log.info("Search Post - Step 02: Enter to Search textbox with value: " + postTitle);
		adminPostSearchPage.enterToSearchTextbox(postTitle);

		log.info("Search Post - Step 03: Click to Search Posts button");
		adminPostSearchPage.clickToSearchPostButton();

		log.info("Search Post - Step 04: Verify Search table contains: '" + postTitle + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("title", postTitle));

		log.info("Search Post - Step 05: Verify Search table contains: '" + authorName + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("author", authorName));

		log.info("Search Post - Step 05: Open end User Site");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);

		log.info("Search Post - Step 06: Verify post infor displayed at HomePage");
		verifyTrue(userHomePage.isPostInforDisplayedWithPostTitle(postTitle));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostAuthor(postTitle, authorName));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostBody(postTitle, postBody));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostCurrentDay(postTitle, currentDay));

		log.info("Search Post - Step 06: Click to Post title");
		userPostDetailPage = userHomePage.clickToPostTitle(postTitle);

		log.info("Search Post - Step 06: Verify Post infor displayed at Post details page");
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostTitle(postTitle));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostAuthor(postTitle, authorName));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostBody(postTitle, postBody));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostCurrentDay(postTitle, currentDay));
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
	private String adminUserName, adminPassword, searchPostURL, postTitle, postBody, authorName, currentDay;
	private String adminUrl, endUserUrl;
	private AdminLoginPO adminLoginPage;
	private AdminDashboardPO adminDashboardPage;
	private AdminPostAddNewPO adminPostAddNewPage;
	private AdminPostSearchPO adminPostSearchPage;
	private UserHomePO userHomePage;
	private UserPostDetailPO userPostDetailPage;

}
