package pageObjects.swaglab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.swaglab.ProductPageUI;

public class ProductPO extends BasePage {
	private WebDriver driver;

	public ProductPO(WebDriver driver) {
		this.driver = driver;
	}

	public void selectItemInSortDropdown(String option) {
		waitForElementClickable(driver, ProductPageUI.PRODUCT_SORT_DROPDOWN);
		selectItemInDefaultDropdown(driver, ProductPageUI.PRODUCT_SORT_DROPDOWN, option);
	}

	public boolean isProductNameSortAscending() {
		return isDataStringSortAsc(driver, ProductPageUI.PRODUCT_NAME_TEXT);
	}

	public boolean isProductNameSortDescending() {
		return isDataStringSortDesc(driver, ProductPageUI.PRODUCT_NAME_TEXT);
	}

	public boolean isProductPriceSortAscending() {
		List<WebElement> productPriceText = getListElements(driver, ProductPageUI.PRODUCT_PRICE_TEXT);

		List<Float> productPriceUI = new ArrayList<Float>();
		for (WebElement productPrice : productPriceText) {
			productPriceUI.add(Float.parseFloat(productPrice.getText().replace("$", "")));
		}
		List<Float> productPriceSortList = new ArrayList<Float>(productPriceUI);
		Collections.sort(productPriceSortList);
		return productPriceUI.equals(productPriceSortList);

	}

	public boolean isProductPriceSortDescending() {
		List<WebElement> productPriceText = getListElements(driver, ProductPageUI.PRODUCT_PRICE_TEXT);

		List<Float> productPriceUI = new ArrayList<Float>();
		for (WebElement productPrice : productPriceText) {
			productPriceUI.add(Float.parseFloat(productPrice.getText().replace("$", "")));
		}
		List<Float> productPriceSortList = new ArrayList<Float>(productPriceUI);
		Collections.sort(productPriceSortList);
		Collections.reverse(productPriceSortList);
		return productPriceUI.equals(productPriceSortList);

	}

}
