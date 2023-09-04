package commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
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
import pageObjects.nopCommerce.user.UserWishlistPageObject;
import pageObjects.wordpress.AdminDashboardPO;
import pageObjects.wordpress.UserHomePO;
import pageUIs.jQuery.uploadFile.HomePageUI;
import pageUIs.nopCommerce.user.BasePageNopCommerceUI;

public class BasePage {
	private final long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private final long shortTimeout = GlobalConstants.SHORT_TIMEOUT;

	public static BasePage getBasePageObject() {
		return new BasePage();
	}

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(1);
	}

	protected String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
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
		// if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("XPath=") || locatorType.startsWith("Xpath=")) {
		// locatorType = String.format(locatorType, dynamicValues);
		// } else {
		// throw new RuntimeException("The locator type is only supported for the XPath locator");
		// }
		locatorType = String.format(locatorType, (Object[]) dynamicValues);
		return locatorType;

	}

	public WebElement getWebElement(WebDriver driver, String locatorType) {
		if(driver.toString().contains("internet explorer")) {
			sleepInSecond(3);
		}
		return driver.findElement(getByLocator(locatorType));
	}

	private WebElement getWebElement(WebDriver driver, String locatorType, String... dynamicValues) {
		return driver.findElement(getByLocator(getDynamicXpath(locatorType, dynamicValues)));
	}

	public List<WebElement> getListElements(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}

	protected List<WebElement> getListElements(WebDriver driver, String locatorType, String... dynamicValues) {
		return driver.findElements(getByLocator(getDynamicXpath(locatorType, dynamicValues)));
	}

	protected void clickToElement(WebDriver driver, String locatorType) {
		if(driver.toString().contains("internet explorer")) {
			sleepInSecond(3);
			clickToElementByJS(driver, locatorType);
		}else {
			getWebElement(driver, locatorType).click();
		}

	}

	protected void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		this.getWebElement(driver, locatorType, dynamicValues).click();
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

	protected String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}

	protected String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType, String... dynamicValues) {
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

	protected String getElementAttribute(WebDriver driver, String attributeName, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
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

	protected boolean isElementDisplayed(WebDriver driver, String locatorType) {
		try {
			// displayed - có trong dom: true
			// undisplayed - có trong dom: trả về false
			return getWebElement(driver, locatorType).isDisplayed();
		} catch (NoSuchElementException e) {
			// undisplayed: k có trong dom --> Quá lâu
			return false;
		}
	}

	protected boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, locatorType, dynamicValues).isDisplayed();
	}

	protected boolean isElementUndisplayed(WebDriver driver, String locatorType) {
		overrideImplicitTimeout(driver, shortTimeout);
		List<WebElement> elements = getListElements(driver, locatorType);
		overrideImplicitTimeout(driver, longTimeout);

		if (elements.size() == 0)
			return true;
		else if (elements.size() > 0 && !elements.get(0).isDisplayed())
			return true;
		return false;
	}

	protected boolean isElementUndisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		overrideImplicitTimeout(driver, shortTimeout);
		List<WebElement> elements = getListElements(driver, locatorType, dynamicValues);
		overrideImplicitTimeout(driver, longTimeout);

		if (elements.size() == 0)
			return true;
		else if (elements.size() > 0 && !elements.get(0).isDisplayed())
			return true;
		return false;
	}

	protected boolean isElementEnabled(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}

	protected boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}

	protected boolean isElementSelected(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isSelected();
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

	protected void hoverMouseToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}

	protected void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		Actions actions = new Actions(driver);
		actions.sendKeys(getWebElement(driver, locatorType), key).build().perform();
	}

	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicValues) {
		Actions actions = new Actions(driver);
		actions.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)), key).build().perform();
	}

	public void scrollToBottomPage(WebDriver driver) {
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

	protected void highlightElement(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
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
	
	protected void scrollToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
	}


	protected String getElementValueByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		xpathLocator = xpathLocator.substring(6);
		return (String) jsExecutor.executeScript("$(document.evaluate(" + xpathLocator + ", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;).val");
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

	protected void waitForElementInvisibility(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	protected void waitForElementInvisibility(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));

	}

	/*
	 * wait for element undisplayed in dom and not in dom
	 */
	protected void waitForElementUnDisplayed(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideImplicitTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		overrideImplicitTimeout(driver, longTimeout);

	}

	protected void waitForElementUnDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideImplicitTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
		overrideImplicitTimeout(driver, longTimeout);
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

	protected void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}

	private void overrideImplicitTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	protected void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	protected void waitForElementStaleness(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.stalenessOf(getWebElement(driver, locatorType)));
	}

	public void uploadFilesByFileName(WebDriver driver, String... fileNames) {
		String filePath = GlobalConstants.UPLOAD_FILE_FOLDER;
		String fullName = "";
		for (String fileName : fileNames) {
			fullName += filePath + fileName + "\n";
		}
		fullName = fullName.trim();
		getWebElement(driver, HomePageUI.UPLOAD_FILE).sendKeys(fullName);

	}

	public boolean isDataStringSortAsc(WebDriver driver, String locator) {
		List<WebElement> elementList = getListElements(driver, locator);
		ArrayList<String> dataList = new ArrayList<String>();		
		for (WebElement element : elementList) {
			dataList.add(element.getText());
		}
		List<String> sortList = new ArrayList<String>(dataList);
		Collections.sort(sortList);
		return sortList.equals(dataList);
	}
	
	public boolean isDataStringSortAscLamDa(WebDriver driver, String locator) {
		List<WebElement> elementList = getListElements(driver, locator);
		List<String> dataList = elementList.stream().map(n -> n.getText()).collect(Collectors.toList());
		List<String> sortList = new ArrayList<String>(dataList);
		Collections.sort(sortList);
		return sortList.equals(dataList);
	}

	public boolean isDataStringSortDesc(WebDriver driver, String locator) {
		List<String> dataList = new ArrayList<String>();
		
		List<WebElement> elementList = getListElements(driver, locator);
		
		for (WebElement element : elementList) {
			dataList.add(element.getText());
		}
		List<String> sortList = new ArrayList<String>(dataList);
		
		Collections.sort(sortList);
		Collections.reverse(sortList);
		return sortList.equals(dataList);
	}

	public boolean isDataStringSortDescLamDa(WebDriver driver, String locator) {
		List<WebElement> elementList = getListElements(driver, locator);
		List<String> dataList = elementList.stream().map(n -> n.getText()).collect(Collectors.toList());
		List<String> sortList = new ArrayList<String>(dataList);
		Collections.sort(sortList);
		Collections.reverse(sortList);
		return sortList.equals(dataList);
	}

	// bài switch page
	public UserMyProductReviewPageObject openMyProductReviewPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.MY_PRODUCT_REVIEW_LINK);
		clickToElement(driver, BasePageNopCommerceUI.MY_PRODUCT_REVIEW_LINK);
		return PageGeneratorManager.getUserMyProductReviewPage(driver);
	}

	public UserAddressPageObject openAddressPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.ADDRESS_LINK);
		clickToElement(driver, BasePageNopCommerceUI.ADDRESS_LINK);
		return PageGeneratorManager.getUserAddressPage(driver);
	}

	public UserRewardPointPageObject openRewardPointPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.REWARD_POINT_LINK);
		clickToElement(driver, BasePageNopCommerceUI.REWARD_POINT_LINK);
		return PageGeneratorManager.getUserRewardPointPage(driver);
	}

	public UserCustomerInfoPageObject openCustomerInfoPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.CUSTOMER_INFO_LINK);
		clickToElement(driver, BasePageNopCommerceUI.CUSTOMER_INFO_LINK);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}

	public UserHomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.LOGOUT_LINK_USER_PAGE);
		clickToElement(driver, BasePageNopCommerceUI.LOGOUT_LINK_USER_PAGE);
		return PageGeneratorManager.getUserHomePage(driver);
	}
	
	public UserWishlistPageObject clickToWishlistLinkAtUserPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.WISHLIST_LINK_USER_PAGE);
		clickToElement(driver, BasePageNopCommerceUI.WISHLIST_LINK_USER_PAGE);
		return PageGeneratorManager.getWishListPage(driver);
	}


	// @Step("Navigate to the 'My Account' Page")
	public UserCustomerInfoPageObject openMyAccountPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.MY_ACCOUNT_LINK_USER_PAGE);
		clickToElement(driver, BasePageNopCommerceUI.MY_ACCOUNT_LINK_USER_PAGE);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}

	// bài dynamic locator tối ưu cho bài switch page
	public BasePage openDynamicPageAtMyAccountByPageName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_LOCATOR_AT_MY_ACCOUNT_PAGE, pageName);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_LOCATOR_AT_MY_ACCOUNT_PAGE, pageName);
		switch (pageName) {
		case "Customer info":
			return PageGeneratorManager.getUserCustomerInfoPage(driver);
		case "Addresses":
			return PageGeneratorManager.getUserAddressPage(driver);
		case "My product reviews":
			return PageGeneratorManager.getUserMyProductReviewPage(driver);
		case "Reward points":
			return PageGeneratorManager.getUserRewardPointPage(driver);
		case "Change password":
			return PageGeneratorManager.getUserChangePasswordPage(driver);
		default:
			throw new RuntimeException("Invalid page name at My Account Page");
		}

	}

	public void openDynamicPageAtMyAccountByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_LOCATOR_AT_MY_ACCOUNT_PAGE, pageName);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_LOCATOR_AT_MY_ACCOUNT_PAGE, pageName);
	}

	public AdminLoginPageObject clickToLogoutLinkAtAdminPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.LOGOUT_LINK_ADMIN_PAGE);
		clickToElementByJS(driver, BasePageNopCommerceUI.LOGOUT_LINK_ADMIN_PAGE);
		return PageGeneratorManager.getAdminLoginPage(driver);

	}


	/**
	 * Open dynamic page on sidebar at admin page by Page Name
	 * 
	 * @param driver
	 * @param pageName
	 */
	public void openDynamicPageAtAdminPageOnSideBarByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_LINK_ON_SIDEBAR_AT_ADMIN_PAGE_BY_NAME, pageName);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_LINK_ON_SIDEBAR_AT_ADMIN_PAGE_BY_NAME, pageName);
	}

	public boolean isActiveLinkAtAdminPageOnSideBarByName(WebDriver driver, String pageName) {
		waitForElementVisibility(driver, BasePageNopCommerceUI.ACTIVE_LINK_ON_SIDEBAR_AT_ADMIN_PAGE_BY_NAME, pageName);
		return isElementDisplayed(driver, BasePageNopCommerceUI.ACTIVE_LINK_ON_SIDEBAR_AT_ADMIN_PAGE_BY_NAME, pageName);
	}

	public boolean isAdminPageReady(WebDriver driver) {
		waitForElementInvisibility(driver, BasePageNopCommerceUI.AJAX_LOADING_AT_ADMIN_PAGE);
		return areJQueryAndJSLoadedSuccess(driver);
	}

	public void clickToDynamicHeaderOnSidebarByName(WebDriver driver, String headerName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_HEADER_LINK_BY_NAME, headerName);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_HEADER_LINK_BY_NAME, headerName);
	}

	public boolean isMenuItemOpenedByHeaderName(WebDriver driver, String headerName) {
		waitForElementVisibility(driver, BasePageNopCommerceUI.OPENED_MENU_ITEM_BY_HEADER_NAME, headerName);
		return isElementDisplayed(driver, BasePageNopCommerceUI.OPENED_MENU_ITEM_BY_HEADER_NAME, headerName);
	}

	public int getItemQuantityOnResultTableByTableId(WebDriver driver, String tableId) {
		waitForAllElementVisibility(driver, BasePageNopCommerceUI.PRODUCT_ITEM_QUANTITY_ON_RESULT_TABLE_BY_TABLE_ID, tableId);
		return getElementsSize(driver, BasePageNopCommerceUI.PRODUCT_ITEM_QUANTITY_ON_RESULT_TABLE_BY_TABLE_ID, tableId);
	}

	public boolean isSearchDataDisplayedByTableIdAndHeader(WebDriver driver, String dataSearch, String tableId, String header) {
		int indexColumn = 1;
		if (isElementUndisplayed(driver, BasePageNopCommerceUI.INDEX_COLUMN_BY_HEADER, header)) {
			indexColumn = 1;
		} else {
			indexColumn = getElementsSize(driver, BasePageNopCommerceUI.INDEX_COLUMN_BY_HEADER, header) + 1;

		}
		waitForAllElementVisibility(driver, BasePageNopCommerceUI.ALL_ROW_DATA_BY_TABLE_ID_AND_COLUMN_INDEX, tableId, String.valueOf(indexColumn));
		List<WebElement> dataRow = getListElements(driver, BasePageNopCommerceUI.ALL_ROW_DATA_BY_TABLE_ID_AND_COLUMN_INDEX, tableId, String.valueOf(indexColumn));
		for (WebElement data : dataRow) {
			if (data.getText().equals(dataSearch)) {
				return true;
			}
		}
		return false;
	}

	public boolean isNoDataInTableDisplayedByTableId(WebDriver driver, String tableId) {
		waitForElementVisibility(driver, BasePageNopCommerceUI.NO_DATA_MESSAGE_ON_TABLE_RESULT_BY_TABLE_ID, tableId);
		return isElementDisplayed(driver, BasePageNopCommerceUI.NO_DATA_MESSAGE_ON_TABLE_RESULT_BY_TABLE_ID, tableId);
	}

	public void clickToCheckboxByLabelAtAdminPage(WebDriver driver, String label) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_CHECKBOX_BY_LABEL_AT_ADMIN_PAGE, label);
		checkToDefaultCheckboxRadio(driver, BasePageNopCommerceUI.DYNAMIC_CHECKBOX_BY_LABEL_AT_ADMIN_PAGE, label);
	}

	public void unCheckToboxByLabelAtAdminPage(WebDriver driver, String label) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_CHECKBOX_BY_LABEL_AT_ADMIN_PAGE, label);
		unCheckToDefaultCheckbox(driver, BasePageNopCommerceUI.DYNAMIC_CHECKBOX_BY_LABEL_AT_ADMIN_PAGE, label);
	}

	public void checkToCheckboxByLabelAtAdminPage(WebDriver driver, String label) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_CHECKBOX_BY_LABEL_AT_ADMIN_PAGE, label);
		checkToDefaultCheckboxRadio(driver, BasePageNopCommerceUI.DYNAMIC_CHECKBOX_BY_LABEL_AT_ADMIN_PAGE, label);
	}

	public boolean isSelectedCheckboxByLabelAtAdminPage(WebDriver driver, String label) {
		waitForElementVisibility(driver, BasePageNopCommerceUI.DYNAMIC_CHECKBOX_BY_LABEL_AT_ADMIN_PAGE, label);
		return isElementSelected(driver, BasePageNopCommerceUI.DYNAMIC_CHECKBOX_BY_LABEL_AT_ADMIN_PAGE, label);
	}

	public String getValueTextboxByIdAtAdminPage(WebDriver driver, String id) {
		waitForElementVisibility(driver, BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, id);
		return getElementAttribute(driver, "value", BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, id);
	}

	public String getValueTextareaByIdAtAdminPage(WebDriver driver, String id) {
		waitForElementVisibility(driver, BasePageNopCommerceUI.DYNAMIC_TEXTAREA_BY_ID, id);
		return getElementAttribute(driver, "value", BasePageNopCommerceUI.DYNAMIC_TEXTAREA_BY_ID, id);
	}

	public void openSearchFieldSetAtAdminPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.SEARCH_TITLE_ROW);
		if (!getElementAttribute(driver, "class", BasePageNopCommerceUI.SEARCH_TITLE_ROW).contains("opened")) {
			clickToElement(driver, BasePageNopCommerceUI.SEARCH_TITLE_ROW);
		}
	}

	public void openDynamicCardByIdAtAdminPage(WebDriver driver, String id) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_CARD_TITLE_BY_ID, id);
		if (getElementAttribute(driver, "class", BasePageNopCommerceUI.DYNAMIC_CARD_TITLE_BY_ID, id).contains("collapsed-card")) {
			clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_CARD_TITLE_BY_ID, id);
		}

	}

	public void closeAllOptionOnDropDownAtAdminPageByLabel(WebDriver driver, String labelName) {
		waitForAllElementVisibility(driver, BasePageNopCommerceUI.ALL_CLOSE_OPTION_ON_DROP_DOWN_AT_ADMIN_PAGE_BY_LABEL, labelName);
		List<WebElement> elements = getListElements(driver, BasePageNopCommerceUI.ALL_CLOSE_OPTION_ON_DROP_DOWN_AT_ADMIN_PAGE_BY_LABEL, labelName);
		for (WebElement option : elements) {
			option.click();
		}
	}

	public boolean isOptionDisplayedAtDropdownByLabel(WebDriver driver, String labelName, String option) {
		waitForElementVisibility(driver, BasePageNopCommerceUI.ALL_OPTION_ON_DROP_DOWN_AT_ADMIN_PAGE_BY_LABEL, labelName, option);
		return isElementDisplayed(driver, BasePageNopCommerceUI.ALL_OPTION_ON_DROP_DOWN_AT_ADMIN_PAGE_BY_LABEL, labelName, option);
	}

	public boolean isSuccessMessageDisplayedOnTheTopByMsg(WebDriver driver, String message) {
		waitForElementVisibility(driver, BasePageNopCommerceUI.SUCCESS_MESSAGE_ON_THE_TOP_AT_ADMIN_PAGE, message);
		return isElementDisplayed(driver, BasePageNopCommerceUI.SUCCESS_MESSAGE_ON_THE_TOP_AT_ADMIN_PAGE, message);
	}

	/**
	 * Enter to dynamic textbox by ID
	 * 
	 * @param driver
	 * @param textID the ID of textbox
	 * @param value  text value
	 */
	public void inputToTextboxById(WebDriver driver, String textID, String value) {
		waitForElementVisibility(driver, BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, textID);
		sendkeyToElement(driver, BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, value, textID);
	}

	/**
	 * Enter to dynamic TextArea by ID
	 * 
	 * @param driver
	 * @param textID
	 * @param value
	 */
	public void inputToTextareaById(WebDriver driver, String textID, String value) {
		waitForElementVisibility(driver, BasePageNopCommerceUI.DYNAMIC_TEXTAREA_BY_ID, textID);
		sendkeyToElement(driver, BasePageNopCommerceUI.DYNAMIC_TEXTAREA_BY_ID, value, textID);
	}

	/**
	 * Click to dynamic button By text
	 * 
	 * @author Geni
	 * @param driver
	 * @param text
	 */
	public void clickToButtonByText(WebDriver driver, String text) {
		scrollToElement(driver, BasePageNopCommerceUI.DYNAMIC_BUTTON_BY_TEXT, text);
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_BUTTON_BY_TEXT, text);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_BUTTON_BY_TEXT, text);
	}

	/**
	 * Click to dynamic button by ID
	 * 
	 * @param driver
	 * @param id
	 */
	public void clickToButtonById(WebDriver driver, String id) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_BUTTON_BY_ID, id);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_BUTTON_BY_ID, id);

	}

	/**
	 * Click to Radio button by label name
	 * 
	 * @param driver
	 * @param labelName
	 */
	public void clickToRadioButtonByLabelName(WebDriver driver, String labelName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_RADIO_BY_LABEL, labelName);
		checkToDefaultCheckboxRadio(driver, BasePageNopCommerceUI.DYNAMIC_RADIO_BY_LABEL, labelName);
	}

	/**
	 * Click to Radito button by Aria Label
	 * 
	 * @param driver
	 * @param ariaLabel
	 */
	public void clickToRadioButtonByAriaLabel(WebDriver driver, String ariaLabel) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_RADIO_BY_ARIA_LABEL, ariaLabel);
		checkToDefaultCheckboxRadio(driver, BasePageNopCommerceUI.DYNAMIC_RADIO_BY_ARIA_LABEL, ariaLabel);
	}

	/**
	 * Select to default Dropdown by name attribute
	 * 
	 * @param driver
	 * @param string
	 * @param value
	 */
	public void selectDefaultDropDownByName(WebDriver driver, String nameAttribute, String textValue) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_DROPDOWN_BY_NAME, nameAttribute);
		selectItemInDefaultDropdown(driver, BasePageNopCommerceUI.DYNAMIC_DROPDOWN_BY_NAME, textValue, nameAttribute);
	}

	/**
	 * Select to checkbox by label name
	 * 
	 * @param driver
	 * @param string
	 */
	public void clickToCheckboxByLabel(WebDriver driver, String labelName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_CHECKBOX_BY_LABEL, labelName);
		checkToDefaultCheckboxRadio(driver, BasePageNopCommerceUI.DYNAMIC_CHECKBOX_BY_LABEL, labelName);
	}

	/**
	 * Get textbox value by ID
	 * 
	 * @param driver
	 * @param id
	 * @return
	 */
	public String getTextboxValueByID(WebDriver driver, String id) {
		waitForElementVisibility(driver, BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, id);
		return getElementAttribute(driver, "value", BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, id);
	}

	/**
	 * Check selectedCheckbox
	 * 
	 * @param driver
	 * @param labelName
	 * @return
	 */
	public boolean isSelectedRadioByLabel(WebDriver driver, String labelName) {
		waitForElementVisibility(driver, BasePageNopCommerceUI.DYNAMIC_RADIO_BY_LABEL, labelName);
		return isElementSelected(driver, BasePageNopCommerceUI.DYNAMIC_RADIO_BY_LABEL, labelName);
	}

	/**
	 * Get the selected item of default dropdown
	 * 
	 * @param driver
	 * @param dynamicName
	 */
	public String getSelectItemDropdownByName(WebDriver driver, String dynamicName) {
		waitForElementVisibility(driver, BasePageNopCommerceUI.DYNAMIC_DROPDOWN_BY_NAME, dynamicName);
		return getSelectedItemDefaultDropdown(driver, BasePageNopCommerceUI.DYNAMIC_DROPDOWN_BY_NAME, dynamicName);
	}

	/**
	 * Check success message display on bar notification by message
	 * 
	 * @param driver
	 * @param message
	 * @return
	 */
	public boolean isMessageDispalyedOnBarNotificationByMessage(WebDriver driver, String message) {
		waitForElementVisibility(driver, BasePageNopCommerceUI.DYNAMIC_MESSAGE_AT_BAR_NOTIFICATION_BY_MESSAGE, message);
		return isElementDisplayed(driver, BasePageNopCommerceUI.DYNAMIC_MESSAGE_AT_BAR_NOTIFICATION_BY_MESSAGE, message);

	}

	public void closeTheBarNotification(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.CLOSE_ICON_BAR_NOTIFICATION);
		clickToElement(driver, BasePageNopCommerceUI.CLOSE_ICON_BAR_NOTIFICATION);
		sleepInSecond(1);
	}

	public void hoverDynamicProductCategoryOnTopMenuByName(WebDriver driver, String productName) {
		waitForElementVisibility(driver, BasePageNopCommerceUI.DYNAMIC_PRODUCT_CATEGORY_LINK_BY_NAME, productName);
		hoverMouseToElement(driver, BasePageNopCommerceUI.DYNAMIC_PRODUCT_CATEGORY_LINK_BY_NAME, productName);
	}

	/**
	 * Open Dynamic Link OnFooter Block By page name
	 * 
	 * @param driver
	 * @param name
	 */
	public void openDynamicLinkOnFooterBlockByPageName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageNopCommerceUI.DYNAMIC_LINK_ON_FOOTER_BY_PAGE_NAME, pageName);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_LINK_ON_FOOTER_BY_PAGE_NAME, pageName);
	}

	/**
	 * Press enter to Textbox by Id
	 * 
	 * @param driver
	 * @param id
	 */
	public void pressEnterToTextboxById(WebDriver driver, String id) {
		waitForElementVisibility(driver, BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, id);
		pressKeyToElement(driver, BasePageNopCommerceUI.DYNAMIC_TEXTBOX_BY_ID, Keys.ENTER, id);
	}

	public void waitForCurrentPageStaleness(WebDriver driver) {
		waitForElementStaleness(driver, BasePageNopCommerceUI.CURRENT_PAGE);
	}

	// wordpress site
	public UserHomePO openEndUserSite(WebDriver driver, String endUserUrl) {
		openPageUrl(driver, endUserUrl);
		return pageObjects.wordpress.PageGeneratorManager.getUserHomePage(driver);
	}
	
	public AdminDashboardPO openAdminSite(WebDriver driver, String adminUrl) {
		openPageUrl(driver, adminUrl);
		return pageObjects.wordpress.PageGeneratorManager.getAdminDashbpardPage(driver);
	}


}
