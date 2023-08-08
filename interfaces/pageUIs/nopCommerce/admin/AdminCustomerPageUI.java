package pageUIs.nopCommerce.admin;

public class AdminCustomerPageUI {
	public static final String ADD_NEW_LINK = "css=a.btn-primary>i.fa-plus-square";
	public static final String CUSTOMER_ROLE_TEXTBOX = "css=ul#SelectedCustomerRoleIds_taglist+input";
	public static final String ALL_CUSTOMER_ROLES = "css=ul#SelectedCustomerRoleIds_listbox>li";
	public static final String ACTIVE_CUSTOMER_AT_TABLE_RESULT_BY_NAME = "xpath=//table[@id='customers-grid']//tbody//td[text()='%s']/following-sibling::td/i[contains(@class, 'true-icon')]";
	public static final String EDIT_CUSTOMER_BUTTON_AT_TABLE_RESULT_BY_NAME = "xpath=//table[@id='customers-grid']//tbody//td[text()='%s']/following-sibling::td/a[text()='Edit']";
	public static final String EDIT_ADDRESS_BUTTON_AT_TABLE_RESULT_BY_EMAIL = "xpath=//table[@id='customer-addresses-grid']//tbody//td[text()='%s']/following-sibling::td/a[text()='Edit']";
	public static final String DELETE_ADDRESS_BUTTON_AT_TABLE_RESULT_BY_EMAIL = "xpath=//table[@id='customer-addresses-grid']//tbody//td[text()='%s']/following-sibling::td/a[text()='Delete']";
	public static final String SAVE_BUTTON_AT_CUSTOMER_PAGE = "css=button[name='save']>i.fa-save";
	public static final String BACK_TO_CUSTOMER_DETAILS_LINK = "xpath=//a[text()='back to customer details']";
	public static final String BACK_TO_CUSTOMER_LIST_LINK = "css=i.fa-arrow-circle-left+a";
	public static final String ADDRESS_INFORMATION_BY_EMAIL= "xpath=//table[@id='customer-addresses-grid']/tbody//td[text()='%s']/following-sibling::td/div[contains(., '%s')]";
	

}
