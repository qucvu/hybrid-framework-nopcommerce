package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.nopCommerce.admin.AdminCustomersPageObject;
import pageObjects.nopCommerce.admin.AdminDashboardPageObject;
import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.admin.AdminProductPageObject;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCategoryPageObject;
import pageObjects.nopCommerce.user.UserChangePasswordPageObject;
import pageObjects.nopCommerce.user.UserCompareProductPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserProductDetailPageObject;
import pageObjects.nopCommerce.user.UserProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;
import pageObjects.nopCommerce.user.UserSearchPageObject;
import pageObjects.nopCommerce.user.UserShoppingCartPageObject;
import pageObjects.nopCommerce.user.UserWishlistPageObject;

public class PageGeneratorManager {
	public static UserHomePageObject getUserHomePage(WebDriver driver) {
		return new UserHomePageObject(driver);
	}

	public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}

	public static UserLoginPageObject getUserLoginPage(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}

	public static UserCustomerInfoPageObject getUserCustomerInfoPage(WebDriver driver) {
		return new UserCustomerInfoPageObject(driver);
	}

	public static UserAddressPageObject getUserAddressPage(WebDriver driver) {
		return new UserAddressPageObject(driver);
	}

	public static UserMyProductReviewPageObject getUserMyProductReviewPage(WebDriver driver) {
		return new UserMyProductReviewPageObject(driver);
	}

	public static UserRewardPointPageObject getUserRewardPointPage(WebDriver driver) {
		return new UserRewardPointPageObject(driver);
	}

	public static UserChangePasswordPageObject getUserChangePasswordPage(WebDriver driver) {
		return new UserChangePasswordPageObject(driver);
	}

	public static UserCategoryPageObject getCategoryPageObject(WebDriver driver) {
		return new UserCategoryPageObject(driver);
	}

	public static UserProductDetailPageObject getProductPurchasePage(WebDriver driver) {
		return new UserProductDetailPageObject(driver);

	}

	public static UserProductReviewPageObject getProductReviewPage(WebDriver driver) {
		return new UserProductReviewPageObject(driver);
	}

	public static UserSearchPageObject getSearchPage(WebDriver driver) {
		return new UserSearchPageObject(driver);
	}
	
	public static UserWishlistPageObject getWishListPage(WebDriver driver) {
		return new UserWishlistPageObject(driver);
	}

	public static UserShoppingCartPageObject getShoppingCartPage(WebDriver driver) {
		return new UserShoppingCartPageObject(driver);
	}
	
	public static UserCompareProductPageObject getCompareProductPage(WebDriver driver) {
		return new UserCompareProductPageObject(driver);
	}


	/* Admin Page */

	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}

	public static AdminDashboardPageObject getAdminDashboardPage(WebDriver driver) {
		return new AdminDashboardPageObject(driver);
	}

	public static AdminProductPageObject getAdminProductPage(WebDriver driver) {
		return new AdminProductPageObject(driver);
	}

	public static AdminCustomersPageObject getAdminCustomerPage(WebDriver driver) {
		return new AdminCustomersPageObject(driver);
	}

}
