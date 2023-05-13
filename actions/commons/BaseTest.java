package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	protected final Log log;
	
	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}

	public WebDriver getDriverInstance() {
		return this.driver;
	}
	
	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		switch (browserList) {
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case H_FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("-headless");
			options.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(options);
			break;
		case CHROME:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case H_CHROME:
			WebDriverManager.chromedriver().setup();
			ChromeOptions optionsChrome = new ChromeOptions();
			optionsChrome.addArguments("-headless");
			optionsChrome.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(optionsChrome);
			break;
		case IE:
			WebDriverManager.iedriver().arch32().setup();
			driver = new InternetExplorerDriver();
			break;
		case EDEG:
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case COCCOC:
			WebDriverManager.chromedriver().driverVersion("109.0.5414.25").setup();
			ChromeOptions optionsCocCoc = new ChromeOptions();
			if (GlobalConstants.OS_NAME.contains("windows")) {
				optionsCocCoc.setBinary("C:\\Program Files (x86)\\CocCoc\\Browser\\Application\\browser.exe");
			} else {
				optionsCocCoc.setBinary("...");

			}
			driver = new ChromeDriver(optionsCocCoc);
			break;
		default:
			throw new RuntimeException("Browser name invalid");
		}
		// if(browserName.equals("firefox")) {
		// System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		// driver = new FirefoxDriver();
		// }else if(browserName.equals("chrome")) {
		// System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		// driver = new ChromeDriver();
		// }else if (browserName.equals("edge")) {
		// System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		// driver = new EdgeDriver();
		// }else {
		// throw new RuntimeException("Browser name invalid");
		// }
		// driver.get(getDomainUrl("production"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);

		return driver;
	}

	protected WebDriver getBrowserDriver(String browserName, String url) {
		switch (browserName) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "h_firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("-headless");
			options.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(options);
			break;
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "h_chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions optionsChrome = new ChromeOptions();
			optionsChrome.addArguments("-headless");
			optionsChrome.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(optionsChrome);
			break;
		case "ie":
			WebDriverManager.iedriver().arch32().setup();
			driver = new InternetExplorerDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "coccoc":
			WebDriverManager.chromedriver().driverVersion("109.0.5414.25").setup();
			ChromeOptions optionsCocCoc = new ChromeOptions();
			if (GlobalConstants.OS_NAME.contains("windows")) {
				optionsCocCoc.setBinary("C:\\Program Files (x86)\\CocCoc\\Browser\\Application\\browser.exe");
			} else {
				optionsCocCoc.setBinary("...");

			}
			driver = new ChromeDriver(optionsCocCoc);
			break;
		default:
			throw new RuntimeException("Browser name invalid");
		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);

		return driver;
	}

	protected String getDomainUrl(String env) {
		switch (env) {
		case "production":
			env = GlobalConstants.PORTAL_PAGE_URL;
			break;
		default:
			break;
		}
		return env;
	}

	protected int generateRandomNumber() {
		return new Random().nextInt(99999);
	}

	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			log.info(" -------------------------- PASSED -------------------------- ");

		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

}
