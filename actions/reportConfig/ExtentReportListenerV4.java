//package reportConfig;
//
//import java.io.IOException;
//
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.MediaEntityBuilder;
//import com.aventstack.extentreports.Status;
//
//import commons.BaseTest;
//
//public class ExtentReportListenerV4 extends BaseTest implements ITestListener {
//	private static ExtentReports extent = ExtentManager.createInstance();
//	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
//
//	@Override
//	public void onTestStart(ITestResult result) {
//		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
//		test.set(extentTest);
//	}
//
//	@Override
//	public void onTestSuccess(ITestResult result) {
//		// TODO Auto-generated method stub
//		test.get().log(Status.PASS, "Test passed");
//	}
//
//	@Override
//	public void onTestFailure(ITestResult result) {
//		Object testClass = result.getInstance();
//		WebDriver driver = ((BaseTest) testClass).getDriverInstance();
//		String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
//
//		try {
//
//			test.get().fail(result.getThrowable());
//			test.get().log(Status.FAIL, "Test failed", MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void onTestSkipped(ITestResult result) {
//		// TODO Auto-generated method stub
//		test.get().skip(result.getThrowable());
//
//	}
//
//	@Override
//	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void onStart(ITestContext context) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void onFinish(ITestContext context) {
//		// TODO Auto-generated method stub
//		extent.flush();
//
//	}
//
//}
