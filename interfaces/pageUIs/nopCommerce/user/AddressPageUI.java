package pageUIs.nopCommerce.user;

public class AddressPageUI {
	public static final String DYNAMIC_TITLE_ADDRESS_INFORMATION_BY_FULLNAME = "xpath=//div[@class='title']/strong[text()='%s']";
	public static final String DYNAMIC_ADDRESS_ITEM_INFORMATION_BY_EMAIL = "xpath=//li[@class='email' and contains(., '%s')]/ancestor::div[@class='section address-item' and contains(., '%s')]";

}
