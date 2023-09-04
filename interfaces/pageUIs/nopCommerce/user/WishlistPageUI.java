package pageUIs.nopCommerce.user;

public class WishlistPageUI {
	public static final String WISHLIST_TITLE = "XPATH=//div[@class='page wishlist-page']/div[@class='page-title']/h1[text()='Wishlist']";
	public static final String DYNAMIC_PRODUCT_AT_WISHLIST_TABLE_BY_NAME = "xpath=//div[@class='wishlist-content']//table//td[@class='product']/a[@class='product-name' and text()='%s']";
	public static final String PERSONAL_WISHLIST_LINK = "css=div.share-info>a.share-link";
	public static final String WISHLIST_TITLE_WITH_FULL_NAME = "xpath=//div[@class='page wishlist-page']/div[@class='page-title']/h1[text()='Wishlist of %s']";
	public static final String CHECKBOX_ADD_TO_CART_BY_PRODUCT_NAME = "xpath=//a[@class='product-name' and text()='%s']/parent::td/preceding-sibling::td[@class='add-to-cart']/input";
	public static final String ADD_TO_CART_BUTTON= "css=button[name=addtocartbutton]";
	public static final String EMPTY_WISHLIST_MESSAGE= "xpath=//div[@class='page-body']/div[@class='no-data' and contains(text(), 'The wishlist is empty!')]";
	public static final String REMOVE_BUTTON_CELL_BY_PRODUCT_NAME= "xpath=//a[@class='product-name' and text()='%s']/parent::td/following-sibling::td[@class='remove-from-cart']/button";

}
