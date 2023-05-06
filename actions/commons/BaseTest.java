package commons;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	
	
	protected WebDriver getBrowserDriver(String browserName) {
		switch (browserName) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
			driver = new EdgeDriver();
			break;
		default:
			throw new RuntimeException("Browser name invalid");
		}
//		if(browserName.equals("firefox")) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//			driver = new FirefoxDriver();
//		}else if(browserName.equals("chrome")) {
//			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//			driver = new ChromeDriver();			
//		}else if (browserName.equals("edge")) {
//			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
//			driver = new EdgeDriver();
//		}else {
//			throw new RuntimeException("Browser name invalid");
//		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");

		return driver;
	}
}
