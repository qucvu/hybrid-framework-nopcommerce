package commons;

import java.io.File;

public class GlobalConstants {
	public static final String PORTAL_PAGE_URL = "https://demo.nopcommerce.com/";
	public static final String ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com/";

	public static final String PROJECT_PATH = System.getProperty("user.dir") + File.separator;
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String JAVA_VERSION = System.getProperty("java.version");
	public static final String UPLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "uploadFiles" + File.separator;
	public static final String DOWNLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "downloadFiles";
	public static final String BROWSER_LOG_FOLDER = PROJECT_PATH + File.separator + "browserLogs";
	public static final String DRAG_DROG_HTML5 = PROJECT_PATH + File.separator + "DragAndDrop";
	public static final String AUTO_IT_SCRIPT = PROJECT_PATH + File.separator + "autoIT";
	public static final String REPORTNG_SCREENSHOT = PROJECT_PATH + File.separator + "ReportNGScreenShots" + File.separator;
	public static final String EXTENT_PATH_V2 = PROJECT_PATH + File.separator + "ExtentReportV2" + File.separator;
	public static final String EXTENT_PATH_V3 = PROJECT_PATH + File.separator + "ExtentReportV3" + File.separator;
	public static final String EXTENT_PATH_V4 = PROJECT_PATH + File.separator + "ExtentReportV4" + File.separator;
	public static final String EXTENT_PATH_V5 = PROJECT_PATH + File.separator + "ExtentReportV5" + File.separator;

	public static final int LONG_TIMEOUT = 30;
	public static final int SHORT_TIMEOUT = 5;
	public static final int RETRY_TEST_FAIL = 15;
	
	
	public static final String DB_DEV_URL = "127.0.0.125:9860";
	public static final String DB_DEV_USER = "127.0.0.125:9860";
	public static final String DB_DEV_PASS = "P@ssWor1d1";
	public static void main(String[] args) {
		System.out.println(UPLOAD_FILE_FOLDER);
	}
}
