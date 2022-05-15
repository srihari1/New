package helperClasses;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import commonFunctionLibrary.WebFunctionLibrary;
import io.qameta.allure.Attachment;

public class WebAllureListener implements ITestListener {

	private static String getTestMethodName(ITestResult ITestResult) {
		System.out.println("getTestMethodName");
		return ITestResult.getMethod().getConstructorOrMethod().getName();
	}

	@Attachment
	public byte[] saveFailureScreenShot(WebDriver driver, String nameTest) {
		System.out.println("saveFailureScreenShot");
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

	}

	@Attachment
	public byte[] saveSuccessScreenShot(WebDriver driver, String nameTest) {
		System.out.println("saveSuccessScreenShot");
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	@Attachment(value = "{0}", type = "text/plain")
	public static String saveScreenshotTextLog(String message) {
		System.out.println("saveScreenShot");
		return message;
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		System.out.println("in onStart Method " + iTestContext.getName());
		iTestContext.setAttribute("WebDriver", WebFunctionLibrary.getDriver());
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		System.out.println("in onFinish Method " + iTestContext.getName());
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		System.out.println("in onTestStart Method " + getTestMethodName(iTestResult) + " start");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		WebDriver driver = WebFunctionLibrary.getDriver();
		if (driver instanceof WebDriver) {
			System.out.println("Screenshot captured for test case: " + getTestMethodName(iTestResult));
			saveSuccessScreenShot(driver, getTestMethodName(iTestResult));
		}
		saveScreenshotTextLog(getTestMethodName(iTestResult) + " Success screenshot taken!");
		System.out.println("in onTestSuccess Method " + getTestMethodName(iTestResult) + " succeed");
	}

//	@Override
//	public void onTestFailure(ITestResult iTestResult) {
//		System.out.println("in onTestFailure Method " + getTestMethodName(iTestResult) + " failed");
//		Object testClass = iTestResult.getInstance();
//		WebDriver driver = WebFunctionLibrary.getDriver();
//		if (driver instanceof WebDriver) {
//			System.out.println("Screenshot captured for test case: " + getTestMethodName(iTestResult));
//			saveFailureScreenShot(driver, getTestMethodName(iTestResult));
//		}
//		saveScreenshotTextLog(getTestMethodName(iTestResult) + " failed screenshot taken!");
//	}

	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("in onTestSkipped Method " + getTestMethodName(iTestResult) + " skipped");
	}

//	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
//		System.out.println("Test failed but it in defined success ratio " + getTestMethodName(iTestResult)
//				+ " failedButWithinSuccessPercentage");
//	}

}
