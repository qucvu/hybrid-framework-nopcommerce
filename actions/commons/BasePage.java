package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;
import pageUIs.jQuery.uploadFile.HomePageUI;
import pageUIs.nopCommerce.user.BasePageUI;

public class BasePage {
	private final long longTimeout = GlobalConstants.LONG_TIMEOUT;

	public static BasePage getBasePageObject() {
		return new BasePage();
	}

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	protected String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	protected String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	protected Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	protected void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	protected void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	protected String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	protected void sendKeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}

	protected String getWindowHandle(WebDriver driver) {
		return driver.getWindowHandle();
	}

	// 2 windows
	protected void switchToWindowById(WebDriver driver, String windowId) {
		Set<String> allWindowIds = driver.getWindowHandles();
		for (String id : allWindowIds) {
			if (!id.equals(windowId)) {
				driver.switchTo().window(id);
			}
		}
	}

	// more than 2
	protected void switchToWindowByTitle(WebDriver driver, String expectedTitlePage) {
		Set<String> allWindowIds = driver.getWindowHandles();
		for (String id : allWindowIds) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(expectedTitlePage)) {
				break;
			}
		}
	}

	protected void closeOtherTabsWithoutParent(WebDriver driver, String parentId) {
		Set<String> allIds = driver.getWindowHandles();
		for (String id : allIds) {
			if (!id.equals(parentId)) {
				driver.switchTo().window(id).close();
			}
		}
		driver.switchTo().window(parentId);
	}

	// locatorType: id=/ css=/ xpath=/ name = /
	private By getByLocator(String locatorType) {
		By by;
		if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("class=") || locatorType.startsWith("CLASS=") || locatorType.startsWith("Class=")) {
			by = By.className(locatorType.substring(6));
		} else if (locatorType.startsWith("name=") || locatorType.startsWith("NAME=") || locatorType.startsWith("Name=")) {
			by = By.name(locatorType.substring(5));

		} else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
			by = By.cssSelector(locatorType.substring(4));

		} else if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("XPath=") || locatorType.startsWith("Xpath=")) {
			by = By.xpath(locatorType.substring(6));

		} else {
			throw new RuntimeException("The locator type is not supported!");
		}
		return by;
	}

	private String getDynamicXpath(String locatorType, String... dynamicValues) {
		if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("XPath=") || locatorType.startsWith("Xpath=")) {
			locatorType = String.format(locatorType, (Object[]) dynamicValues);
		} else {
			throw new RuntimeException("The locator type is only supported for the XPath locator");
		}
		return locatorType;

	}

	public WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	private WebElement getWebElement(WebDriver driver, String locatorType, String... dynamicValues) {
		return driver.findElement(getByLocator(getDynamicXpath(locatorType, dynamicValues)));
	}

	public List<WebElement> getListElements(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}

	private List<WebElement> getListElements(WebDriver driver, String locatorType, String... dynamicValues) {
		return driver.findElements(getByLocator(getDynamicXpath(locatorType, dynamicValues)));
	}

	protected void clickToElement(WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType).click();
	}

	protected void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		getWebElement(driver, locatorType, dynamicValues).click();
	}

	protected void sendkeyToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}
	

	protected void sendkeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, locatorType, dynamicValues);
		element.clear();
		element.sendKeys(textValue);
	}

	public String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}

	protected String getElementText(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, locatorType, dynamicValues).getText();
	}

	protected void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByVisibleText(textItem);
	}

	protected void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, locatorType, dynamicValues));
		select.selectByVisibleText(textItem);
	}

	protected String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}

	protected String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType, String textItem, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, locatorType, dynamicValues));
		return select.getFirstSelectedOption().getText();
	}

	protected boolean isDropdownMultiple(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();
	}

	protected void selectItemDropdown(WebDriver driver, String parentXpath, String allItemXpath, String expectedTextItem) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		getWebElement(driver, parentXpath).click();
		sleepInSecond(1);
		List<WebElement> speedDropdownItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(allItemXpath)));
		for (WebElement webElement : speedDropdownItems) {
			if (webElement.getText().trim().equals(expectedTextItem)) {
				webElement.click();
				break;
			}
		}
	}

	protected void enterAndSelectItemDropdown(WebDriver driver, String textBoxXpath, String allItemXpath, String expectedTextItem) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, textBoxXpath);
		element.clear();
		element.sendKeys(expectedTextItem);
		sleepInSecond(1);
		List<WebElement> speedDropdownItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(allItemXpath)));
		for (WebElement webElement : speedDropdownItems) {
			if (webElement.getText().trim().equals(expectedTextItem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", webElement);
				webElement.click();
				break;
			}
		}
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String getElementAttribute(WebDriver driver, String attributeName, String locatorType) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}

	protected String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}

	protected String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	protected int getElementsSize(WebDriver driver, String locatorType) {
		return getListElements(driver, locatorType).size();
	}

	protected int getElementsSize(WebDriver driver, String locatorType, String... dynamicValues) {
		return getListElements(driver, locatorType, dynamicValues).size();
	}

	protected void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	protected void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, locatorType, dynamicValues);
		if (!element.isSelected()) {
			element.click();
		}
	}


	protected void unCheckToDefaultCheckbox(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}
	
	protected void unCheckToDefaultCheckbox(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, locatorType, dynamicValues);
		if (element.isSelected()) {
			element.click();
		}
	}


	protected boolean isElemenetDisplayed(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isDisplayed();
	}

	protected boolean isElemenetDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, locatorType, dynamicValues).isDisplayed();
	}

	protected boolean isElemenetEnabled(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}

	protected boolean isElemenetSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}

	protected void switchToFrameIframe(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}

	protected void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	protected void hoverMouseToElement(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}

	protected void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		Actions actions = new Actions(driver);
		actions.sendKeys(getWebElement(driver, locatorType), key).build().perform();
	}

	protected void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicValues) {
		Actions actions = new Actions(driver);
		actions.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)), key).build().perform();
	}

	protected void scrollToBottomPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	protected void highlightElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	protected void clickToElementByJS(WebDriver driver, String locatorType) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}

	protected void scrollToElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}

	protected void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
	}

	protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	protected WebElement getShadowDom(WebDriver driver, String locatorType) {
		return (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", getWebElement(driver, locatorType));
	}

	protected String getElementValidationMessage(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
	}

	protected boolean isImageLoaded(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
			getWebElement(driver, locator));
		return status;
	}
	
	protected boolean isImageLoaded(WebDriver driver, String locator, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
			getWebElement(driver, getDynamicXpath(locator, dynamicValues)));
		return status;
	}


	protected void waitForElementVisibility(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}

	protected void waitForElementVisibility(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	protected void waitForElementInvisibility(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}

	protected void waitForElementInvisibility(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	protected void waitForAllElementVisibility(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));

	}

	protected void waitForAllElementVisibility(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));

	}

	protected void waitForAllElementInVisibility(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListElements(driver, locatorType)));
	}

	protected void waitForAllElementInVisibility(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListElements(driver, locatorType, dynamicValues)));
	}

	// protected void waitForAllElementInVisibility(WebDriver driver, String ... locatorType) { : truong hop này truyền vào một locator dc sử dụng ở nhiều vị trí
	// WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
	// for (String string : locatorType) {
	// explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getWebElement(driver, string)));
	// }
	// }

	protected void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}

	protected void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	// bài switch page
	public UserMyProductReviewPageObject openMyProductReviewPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
		clickToElement(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
		return PageGeneratorManager.getUserMyProductReviewPage(driver);
	}

	public UserAddressPageObject openAddressPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.ADDRESS_LINK);
		clickToElement(driver, BasePageUI.ADDRESS_LINK);
		return PageGeneratorManager.getUserAddressPage(driver);
	}

	public UserRewardPointPageObject openRewardPointPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.REWARD_POINT_LINK);
		clickToElement(driver, BasePageUI.REWARD_POINT_LINK);
		return PageGeneratorManager.getUserRewardPointPage(driver);
	}

	public UserCustomerInfoPageObject openCustomerInfoPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.CUSTOMER_INFO_LINK);
		clickToElement(driver, BasePageUI.CUSTOMER_INFO_LINK);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}

	public UserHomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.LOGOUT_LINK_USER_PAGE);
		clickToElement(driver, BasePageUI.LOGOUT_LINK_USER_PAGE);
		return PageGeneratorManager.getUserHomePage(driver);

	}

	// bài dynamic locator
	public BasePage openDynamicPageAtMyAccountByPageName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_LOCATOR_AT_MY_ACCOUNT_PAGE, pageName);
		clickToElement(driver, BasePageUI.DYNAMIC_LOCATOR_AT_MY_ACCOUNT_PAGE, pageName);
		switch (pageName) {
		case "Customer info":
			return PageGeneratorManager.getUserCustomerInfoPage(driver);
		case "Addresses":
			return PageGeneratorManager.getUserAddressPage(driver);
		case "My product reviews":
			return PageGeneratorManager.getUserMyProductReviewPage(driver);
		case "Reward points":
			return PageGeneratorManager.getUserRewardPointPage(driver);
		default:
			throw new RuntimeException("Invalid page name at My Account Page");
		}

	}

	public void openDynamicPageAtMyAccountByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageUI.DYNAMIC_LOCATOR_AT_MY_ACCOUNT_PAGE, pageName);
		clickToElement(driver, BasePageUI.DYNAMIC_LOCATOR_AT_MY_ACCOUNT_PAGE, pageName);
	}

	public AdminLoginPageObject clickToLogoutLinkAtAdminPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.LOGOUT_LINK_ADMIN_PAGE);
		clickToElementByJS(driver, BasePageUI.LOGOUT_LINK_ADMIN_PAGE);
		return PageGeneratorManager.getAdminLoginPage(driver);

	}
	
	public void uploadFilesByFileName(WebDriver driver, String... fileNames) {
		String filePath = GlobalConstants.UPLOAD_FILE_FOLDER;
		String fullName = "";
		for (String fileName : fileNames) {
			fullName += filePath + fileName + "\n";
		}
		fullName = fullName.trim();
		getWebElement(driver, HomePageUI.UPLOAD_FILE).sendKeys(fullName);
//		sendkeyToElement(driver,HomePageUI.UPLOAD_FILE, fullName);

	}


}
