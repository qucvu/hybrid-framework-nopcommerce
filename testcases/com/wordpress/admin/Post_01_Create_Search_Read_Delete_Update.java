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
import pageObjects.wordpress.UserPostSearchPO;

public class Post_01_Create_Search_Read_Delete_Update extends BaseTest {

	@Parameters({ "browser", "urlAdmin", "urlUser" })
	@BeforeClass
	public void beforeClass(String browserName, String urlAdmin, String urlEndUser) {
		adminUserName = "vunguyen";
		adminPassword = "Nguyenquocvu.111";
		randomNumber = generateRandomNumber();
		postTitle = "Live coding Title " + randomNumber;
		postBody = "Live coding Body " + randomNumber;
		authorName = "Vu Admin";
		editPostTitle = postTitle + "edit";
		editPostBody = postBody + "edit";
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

		log.info("Create Post - Step 04: Enter to Post body with value: " + postBody);
		adminPostAddNewPage.enterToEditAddNewPostBody(postBody);

		log.info("Create Post - Step 05: Click to 'Publish' button");
		adminPostAddNewPage.clickToPublishOrUpdateButton();

		log.info("Create Post - Step 05: Click to Confirm 'Publish' button");
		adminPostAddNewPage.clickToConfirmPublishButton();

		log.info("Create Post - Step 05: Verify 'Posted Published' message is displayed");
		verifyTrue(adminPostAddNewPage.isPostPublishedMessageDisplayed("Post published."));

	}

	@Test
	public void Post_02_Search_And_View_Post(Method method) {
		log.info("Search Post - Step 01: Open 'Search Posts' Page");
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
	public void Post_03_Edit_Post(Method method) {
		log.info("Edit Post - Step 01: Open browser and admin site");
		adminDashboardPage = userPostDetailPage.openAdminSite(driver, this.adminUrl);

		log.info("Edit Post - Step 03: Click to 'Post' Menu link");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();

		log.info("Edit Post - Step 03: Enter to Search textbox with value: " + postTitle);
		adminPostSearchPage.enterToSearchTextbox(postTitle);

		log.info("Edit Post - Step 04: Click to Search Posts button");
		adminPostSearchPage.clickToSearchPostButton();

		log.info("Edit Post - Step 05: Click Post title detail link and navigate to Edit Post page");
		adminPostAddNewPage = adminPostSearchPage.clickToPostTitleLink(postTitle);

		log.info("Edit Post - Step 06: Enter to post title");
		adminPostAddNewPage.enterToAddNewPostTitle(editPostTitle);

		log.info("Edit Post - Step 07: Enter to Post body with value: " + editPostBody);
		adminPostAddNewPage.enterToEditAddNewPostBody(editPostBody);

		log.info("Edit Post - Step 08: Click to Update button");
		adminPostAddNewPage.clickToPublishOrUpdateButton();

		log.info("Edit Post - Step 09: Verify the 'Post updated' message is displayed");
		verifyTrue(adminPostAddNewPage.isPostPublishedMessageDisplayed("Post updated."));

		log.info("Edit Post - Step 10: Open 'Search Posts' Page");
		adminPostSearchPage = adminPostAddNewPage.openSearchPostPageUrl(searchPostURL);

		log.info("Edit Post - Step 11: Enter to Search textbox with value: " + editPostTitle);
		adminPostSearchPage.enterToSearchTextbox(editPostTitle);

		log.info("Edit Post - Step 12: Click to Search Posts button");
		adminPostSearchPage.clickToSearchPostButton();

		log.info("Edit Post - Step 13: Verify Search table contains: '" + editPostTitle + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("title", editPostTitle));

		log.info("Edit Post - Step 14: Verify Search table contains: '" + authorName + "'");
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("author", authorName));

		log.info("Edit Post - Step 15: Open end User Site");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);

		log.info("Edit Post - Step 16: Verify post infor displayed at HomePage");
		verifyTrue(userHomePage.isPostInforDisplayedWithPostTitle(editPostTitle));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostAuthor(editPostTitle, authorName));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostBody(editPostTitle, editPostBody));
		verifyTrue(userHomePage.isPostInforDisplayedWithPostCurrentDay(editPostTitle, currentDay));

		log.info("Edit Post - Step 17: Click to Post title");
		userPostDetailPage = userHomePage.clickToPostTitle(editPostTitle);

		log.info("Edit Post - Step 18: Verify Post infor displayed at Post details page");
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostTitle(editPostTitle));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostAuthor(editPostTitle, authorName));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostBody(editPostTitle, editPostBody));
		verifyTrue(userPostDetailPage.isPostInforDisplayedWithPostCurrentDay(editPostTitle, currentDay));

	}

	@Test
	public void Post_04_Delete_Post(Method method) {
		log.info("Delete post - Step 01: Open browser and admin site");
		adminDashboardPage = userPostDetailPage.openAdminSite(driver, this.adminUrl);

		log.info("Delete post - Step 02: Click to 'Post' Menu link");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();

		log.info("Delete post - Step 03: Enter to Search textbox with value: " + editPostTitle);
		adminPostSearchPage.enterToSearchTextbox(editPostTitle);

		log.info("Delete post - Step 04: Click to Search Posts button");
		adminPostSearchPage.clickToSearchPostButton();

		log.info("Delete post - Step 05: Select Post details checkbox");
		adminPostSearchPage.selectToPostCheckboxByTittle(editPostTitle);

		log.info("Delete post - Step 06: Select 'Move to Trash' option in dropdown");
		adminPostSearchPage.selectTextItemActionDropdown("Move to Trash");

		log.info("Delete Post - Step 07: Click to Appy button");
		adminPostSearchPage.clickToApplyAction();

		log.info("Delete Post - Step 08: Verify '1 post move to trash' message is displayed");
		verifyTrue(adminPostSearchPage.isMoveToTrashMessageDisplayed("1 post moved to the Trash."));

		log.info("Delete Post - Step 09: Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextbox(editPostTitle);

		log.info("Delete post - Step 10: Click to Search Posts button");
		adminPostSearchPage.clickToSearchPostButton();

		log.info("Delete post - Step 11: Verify 'No Post found' message is displayed");
		verifyTrue(adminPostSearchPage.isNoPostFoundMessageDisplayed());

		log.info("Delete post - Step 12: Open end User Site");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);

		log.info("Delete post - Step 13: Verify post title is undisplayed at HomePage");
		verifyTrue(userHomePage.isPostInfoUndisplayedWithPostTittle(editPostTitle));

		log.info("Delete post - Step 14: Enter to Search textbox");
		userHomePage.enterToSearchTextbox(editPostTitle);

		log.info("Delete post - Step 15: Click to Search button");
		userPostSearchPage = userHomePage.clickToSearchPostButton();

		log.info("Delete post - Step 16: Verify 'Nothing found' message is displayed");
		verifyTrue(userPostSearchPage.isNothingFoundMessageDisplayed());

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}

	private WebDriver driver;
	private String adminUserName, adminPassword, searchPostURL, postTitle, postBody, authorName, currentDay, editPostTitle, editPostBody;
	private String adminUrl, endUserUrl;
	private int randomNumber;
	private AdminLoginPO adminLoginPage;
	private AdminDashboardPO adminDashboardPage;
	private AdminPostAddNewPO adminPostAddNewPage;
	private AdminPostSearchPO adminPostSearchPage;
	private UserHomePO userHomePage;
	private UserPostDetailPO userPostDetailPage;
	private UserPostSearchPO userPostSearchPage;

}
