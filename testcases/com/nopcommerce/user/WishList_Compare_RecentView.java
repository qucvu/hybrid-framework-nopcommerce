package com.nopcommerce.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.nopcommerce.common.Common_01_Register_Cookie;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserCategoryPageObject;
import pageObjects.nopCommerce.user.UserCompareProductPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserProductDetailPageObject;
import pageObjects.nopCommerce.user.UserShoppingCartPageObject;
import pageObjects.nopCommerce.user.UserWishlistPageObject;
import reportConfig.ExtentTestManager;

public class WishList_Compare_RecentView extends BaseTest {
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		productName = "Apple MacBook Pro 13-inch";
		productNameCompare1 = "Adobe Photoshop CS4";
		productNameCompare2 = "Sound Forge Pro 11 (recurring)";

		productNameView1 = "Apple MacBook Pro 13-inch";
		productNameView2 = "HP Envy 6-1180ca 15.6-Inch Sleekbook";
		productNameView3 = "Asus N551JK-XO076H Laptop";
		productNameView4 = "Lenovo Thinkpad X1 Carbon Laptop";
		productNameView5 = "HP Spectre XT Pro UltraBook";

		fullName = String.format("%s %s", Common_01_Register_Cookie.firstName, Common_01_Register_Cookie.lastName);
		log.info("Pre-condition - Step 01: Open End User Page URL");
		driver = getBrowserDriver(browserName, GlobalConstants.PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getUserHomePage(driver);

		log.info("Pre-condition - Step 02: Set cookie and reload page");
		homePage.setCookies(driver, Common_01_Register_Cookie.loggedCookies);
		homePage.refreshCurrentPage(driver);

		log.info("Pre-condition - Step 03: Verify 'My Account' Link is displayed");
		verifyTrue(homePage.isMyAccountDisplayed());

		log.info("Pre-condition - Step 04: Click to 'Desktops' category");
		homePage.hoverDynamicProductCategoryOnTopMenuByName(driver, "Computers");
		categoryPage = homePage.openCategoryPageOnTopMenuByCategoryName("Notebooks");

		log.info("Pre-condition - Step 05: Open details product: " + productName);
		productDetailPage = categoryPage.openDynamicProductDetailPageByName(productName);

		log.info("Pre-condition - Step 06: Verify " + productName + " is displayed at Product details page");
		verifyEquals(productDetailPage.getCurrentProductName(), productName);

	}

	@Test
	public void WishList_01_Add_To_WishList(Method method) {
		ExtentTestManager.startTest(method.getName(), "WishList_01_Add_To_WishList");
		ExtentTestManager.getTest().log(Status.INFO, "Add to Wishlist - Step 01: Click to 'Add To Wishlist' button");
		productDetailPage.clickToAddToWishlistButton();

		ExtentTestManager.getTest().log(Status.INFO, "Add to Wishlist - Step 02: Verify the Add To Wishlist success message is displayed");
		verifyTrue(productDetailPage.isMessageDispalyedOnBarNotificationByMessage(driver, "The product has been added to your wishlist"));

		ExtentTestManager.getTest().log(Status.INFO, "Add to Wishlist - Step 03: Click to 'Wishlist' link at Bar Botification");
		wishlistPage = productDetailPage.clickToWishlistLinkAtTheBarNotification();

		ExtentTestManager.getTest().log(Status.INFO, "Add to Wishlist - Step 04: Verify the 'Wishlist' title is displayed");
		verifyTrue(wishlistPage.isWishlistTitleDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "Add to Wishlist - Step 05: Verify the '" + productName + "' has been added successfully to Wishlist");
		verifyTrue(wishlistPage.isProductNameDisplayedAtWishlistTable(productName));

		ExtentTestManager.getTest().log(Status.INFO, "Add to Wishlist - Step 06: Open personal Wishlist link when click 'Your wishlist URL for sharing'");
		wishlistPage.clickToPersionalWishlistUrl();

		ExtentTestManager.getTest().log(Status.INFO, "Add to Wishlist - Step 07: Verify the '" + fullName + "' has been shown successfully on Title");
		verifyTrue(wishlistPage.isFullNameDisplayedAtWishlistTitle(fullName));

		ExtentTestManager.getTest().log(Status.INFO, "Add to Wishlist - Step 08: Verify the '" + productName + "' has been shown successfully on Wishlist");
		verifyTrue(wishlistPage.isProductNameDisplayedAtWishlistTable(productName));
	}

	@Test
	public void WishList_02_Add_Prouct_To_Card_In_WishList(Method method) {
		ExtentTestManager.startTest(method.getName(), "WishList_02_Add_Prouct_To_Card");

		ExtentTestManager.getTest().log(Status.INFO, "Add Product To Card - Step 01: Open End User Page");
		wishlistPage.openPageUrl(driver, GlobalConstants.PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getUserHomePage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Add Product To Card - Step 02: Click to 'Wishist' link at the header");
		wishlistPage = homePage.clickToWishlistLinkAtUserPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Add Product To Card - Step 03: Select to checkbox of'" + productName + "' at wishlist table");
		wishlistPage.selectToCheckboxAddToCartByProductName(productName);

		ExtentTestManager.getTest().log(Status.INFO, "Add Product To Card - Step 04: Click to 'Add to Cart' button");
		shoppingCartPage = wishlistPage.clickToAddToCartButton();

		ExtentTestManager.getTest().log(Status.INFO, "Add Product To Card - Step 05: Verify the 'Shopping cart' title is displayed");
		verifyTrue(shoppingCartPage.isShoppingCartTitleDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "Add Product To Card - Step 06: Verify the + " + productName + "' is displayed at Shopping cart table");
		verifyTrue(shoppingCartPage.isProductNameDisplayedAtCartTable(productName));

		ExtentTestManager.getTest().log(Status.INFO, "Add Product To Card - Step 07: Click to 'Wishist' link at the header");
		wishlistPage = shoppingCartPage.clickToWishlistLinkAtUserPage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Add Product To Card - Step 08: Verify the '" + productName + "' has been not shown on Wishlist");
		verifyTrue(wishlistPage.isProductNameUnDisplayedAtWishlistTable(productName));

		ExtentTestManager.getTest().log(Status.INFO, "Add Product To Card - Step 09: Verify the 'The wishlist is empty!' message is displayed");
		verifyTrue(wishlistPage.isEmptyWishlistMessageDisplayed());
	}

	@Test
	public void WishList_03_Remove_Product_In_WishList(Method method) {
		ExtentTestManager.startTest(method.getName(), "WishList_03_Remove_Product_In_WishList");

		ExtentTestManager.getTest().log(Status.INFO, "Remove Product - Step 01: Open End User Page");
		wishlistPage.openPageUrl(driver, GlobalConstants.PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getUserHomePage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Remove Product - Step 02: Click to 'Desktops' category");
		homePage.hoverDynamicProductCategoryOnTopMenuByName(driver, "Computers");
		categoryPage = homePage.openCategoryPageOnTopMenuByCategoryName("Notebooks");

		ExtentTestManager.getTest().log(Status.INFO, "Remove Product - Step 03: Open details product: " + productName);
		productDetailPage = categoryPage.openDynamicProductDetailPageByName(productName);

		ExtentTestManager.getTest().log(Status.INFO, "Remove Product - Step 04: Verify " + productName + " is displayed at Product details page");
		verifyEquals(productDetailPage.getCurrentProductName(), productName);

		ExtentTestManager.getTest().log(Status.INFO, "Remove Product - Step 05: Click to 'Add To Wishlist' button");
		productDetailPage.clickToAddToWishlistButton();

		ExtentTestManager.getTest().log(Status.INFO, "Remove Product - Step 06: Verify the Add To Wishlist success message is displayed");
		verifyTrue(productDetailPage.isMessageDispalyedOnBarNotificationByMessage(driver, "The product has been added to your wishlist"));

		ExtentTestManager.getTest().log(Status.INFO, "Remove Product - Step 07: Click to 'Wishlist' link at Bar Botification");
		wishlistPage = productDetailPage.clickToWishlistLinkAtTheBarNotification();

		ExtentTestManager.getTest().log(Status.INFO, "Remove Product - Step 08: Verify the 'Wishlist' title is displayed");
		verifyTrue(wishlistPage.isWishlistTitleDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "Remove Product - Step 09: Verify the '" + productName + "' has been added successfully to Wishlist");
		verifyTrue(wishlistPage.isProductNameDisplayedAtWishlistTable(productName));

		ExtentTestManager.getTest().log(Status.INFO, "Remove Product - Step 10: Click to remove button at product: " + productName);
		wishlistPage.clickToRemoveButtonByProductName(productName);

		ExtentTestManager.getTest().log(Status.INFO, "Remove Product - Step 11: Verify 'Empty Wishlist' message is displayed");
		verifyTrue(wishlistPage.isEmptyWishlistMessageDisplayed());
	}

	@Test
	public void WishList_04_Add_Product_To_Compare(Method method) {
		ExtentTestManager.startTest(method.getName(), "WishList_04_Add_Product_To_Compare");
		ExtentTestManager.getTest().log(Status.INFO, "Compare product - Step 01: Open End User Page");
		wishlistPage.openPageUrl(driver, GlobalConstants.PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getUserHomePage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Compare product - Step 02: Click to 'Software' category");
		homePage.hoverDynamicProductCategoryOnTopMenuByName(driver, "Computers");
		categoryPage = homePage.openCategoryPageOnTopMenuByCategoryName("Software");
		productPriceCompare1 = categoryPage.getPriceByProductName(productNameCompare1);
		productPriceCompare2 = categoryPage.getPriceByProductName(productNameCompare2);

		ExtentTestManager.getTest().log(Status.INFO, "Compare product - Step 03: Click to 'Add to Compare list' link of product: " + productNameCompare1);
		categoryPage.clickToAddToCompareButtonByProductName(productNameCompare1);

		ExtentTestManager.getTest().log(Status.INFO, "Compare product - Step 04: Verify the Add To Compare list success message is displayed");
		verifyTrue(categoryPage.isMessageDispalyedOnBarNotificationByMessage(driver, "The product has been added to your product comparison"));
		categoryPage.closeTheBarNotification(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Compare product - Step 05: Click to 'Add to Compare list' link of product: " + productNameCompare2);
		categoryPage.clickToAddToCompareButtonByProductName(productNameCompare2);

		ExtentTestManager.getTest().log(Status.INFO, "Compare product - Step 06: Verify the Add To Compare list success message is displayed");
		verifyTrue(categoryPage.isMessageDispalyedOnBarNotificationByMessage(driver, "The product has been added to your product comparison"));

		ExtentTestManager.getTest().log(Status.INFO, "Compare product - Step 07: Click to 'product comparison' link at Bar notification message");
		compareProductPage = categoryPage.clickToProductComparisonLink();

		ExtentTestManager.getTest().log(Status.INFO, "Compare product - Step 08: Verify the compare information is displayed of product: " + productNameCompare1);
		verifyTrue(compareProductPage.isProductNameDisplayedAtComparisonTable(productNameCompare1));

		ExtentTestManager.getTest().log(Status.INFO, "Compare product - Step 08: Verify the compare information is displayed of product: " + productNameCompare1);
		verifyTrue(compareProductPage.isRemoveButtonDisplayedAtComparisonTableByProductName(productNameCompare1));

		ExtentTestManager.getTest().log(Status.INFO, "Compare product - Step 08: Verify the compare information is displayed of product: " + productNameCompare1);
		verifyTrue(compareProductPage.isNameLabelDisplayedAtComparisonTableByProductName(productNameCompare1));

		ExtentTestManager.getTest().log(Status.INFO, "Compare product - Step 08: Verify the compare information is displayed of product: " + productNameCompare1);
		verifyTrue(compareProductPage.isPriceLabelDisplayedAtComparisonTableByProductPrice(productPriceCompare1));

		ExtentTestManager.getTest().log(Status.INFO, "Compare product - Step 09: Verify the compare information is displayed of product: " + productNameCompare2);
		verifyTrue(compareProductPage.isProductNameDisplayedAtComparisonTable(productNameCompare2));
		verifyTrue(compareProductPage.isRemoveButtonDisplayedAtComparisonTableByProductName(productNameCompare2));
		verifyTrue(compareProductPage.isNameLabelDisplayedAtComparisonTableByProductName(productNameCompare2));
		verifyTrue(compareProductPage.isPriceLabelDisplayedAtComparisonTableByProductPrice(productPriceCompare2));

		ExtentTestManager.getTest().log(Status.INFO, "Compare product - Step 10: Click to 'Clear List' link");
		compareProductPage.clickToClearListLink();

		ExtentTestManager.getTest().log(Status.INFO, "Compare product - Step 11: Verify the 'empty compare products' message is displayed");
		verifyTrue(compareProductPage.isEmptyCompareProductMessDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "Compare product - Step 11: Verify the '" + productNameCompare1 + "' has been not shown on Compare products");
		verifyTrue(compareProductPage.isProductNameUnDisplayed(productNameCompare1));
		ExtentTestManager.getTest().log(Status.INFO, "Compare product - Step 11: Verify the '" + productNameCompare2 + "' has been not shown on Compare products");
		verifyTrue(compareProductPage.isProductNameUnDisplayed(productNameCompare2));

	}

	@Test
	public void WishList_05_Recently_Viewed_Product(Method method) {
		ExtentTestManager.startTest(method.getName(), "WishList_05_Recently_Viewed_Product");
		ExtentTestManager.getTest().log(Status.INFO, "Recently Viewed Product - Step 01: Open End User Page");
		homePage.openPageUrl(driver, GlobalConstants.PORTAL_PAGE_URL);
		homePage = PageGeneratorManager.getUserHomePage(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Recently Viewed Product - Step 02: Open 'Notebooks' categories");
		homePage.hoverDynamicProductCategoryOnTopMenuByName(driver, "Computers");
		categoryPage = homePage.openCategoryPageOnTopMenuByCategoryName("Notebooks");

		ExtentTestManager.getTest().log(Status.INFO, "Recently Viewed Product - Step 03: Open detail page and back to category page of product: " + productNameView1);
		productDetailPage = categoryPage.openDynamicProductDetailPageByName(productNameView1);
		productDetailPage.backToPage(driver);
		categoryPage = PageGeneratorManager.getCategoryPageObject(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Recently Viewed Product - Step 04: Open detail page of product: " + productNameView2);
		productDetailPage = categoryPage.openDynamicProductDetailPageByName(productNameView2);
		productDetailPage.backToPage(driver);
		categoryPage = PageGeneratorManager.getCategoryPageObject(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Recently Viewed Product - Step 05: Open detail page of product: " + productNameView3);
		productDetailPage = categoryPage.openDynamicProductDetailPageByName(productNameView3);
		productDetailPage.backToPage(driver);
		categoryPage = PageGeneratorManager.getCategoryPageObject(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Recently Viewed Product - Step 06: Open detail page of product: " + productNameView4);
		productDetailPage = categoryPage.openDynamicProductDetailPageByName(productNameView4);
		productDetailPage.backToPage(driver);
		categoryPage = PageGeneratorManager.getCategoryPageObject(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Recently Viewed Product - Step 07: Open detail page of product: " + productNameView5);
		productDetailPage = categoryPage.openDynamicProductDetailPageByName(productNameView5);
		productDetailPage.backToPage(driver);
		categoryPage = PageGeneratorManager.getCategoryPageObject(driver);

		ExtentTestManager.getTest().log(Status.INFO, "Recently Viewed Product - Step 08: Click to 'Recently viewed products' link at the footer");
		categoryPage.openDynamicLinkOnFooterBlockByPageName(driver, "Recently viewed products");

		ExtentTestManager.getTest().log(Status.INFO, "Recently Viewed Product - Step 09: Verify only 3 last product are displayed at the page");
		verifyTrue(categoryPage.isProductNameDisplayed(productNameView3));
		verifyTrue(categoryPage.isProductNameDisplayed(productNameView4));
		verifyTrue(categoryPage.isProductNameDisplayed(productNameView5));
		verifyTrue(categoryPage.isProductNameUnDisplayed(productNameView2));

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}

	private WebDriver driver;
	private String productName;
	private String productNameCompare1, productNameCompare2;
	private String productPriceCompare1, productPriceCompare2;
	private String productNameView1, productNameView2, productNameView3, productNameView4, productNameView5;
	private UserHomePageObject homePage;
	private UserCategoryPageObject categoryPage;
	private UserProductDetailPageObject productDetailPage;
	private UserWishlistPageObject wishlistPage;
	private UserShoppingCartPageObject shoppingCartPage;
	private UserCompareProductPageObject compareProductPage;
	private String fullName;

}
