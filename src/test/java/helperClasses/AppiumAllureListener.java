package helperClasses;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import commonFunctionLibrary.MobileFunctionLibrary;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Attachment;

public class AppiumAllureListener implements ITestListener {

    private static String getTestMethodName(ITestResult ITestResult) {
    	System.out.println("getTestMethodName");
        return ITestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment
    public byte[] saveFailureScreenShot(AppiumDriver driver, String nameTest ) {
    	System.out.println("saveFailureScreenShot");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
    	System.out.println("saveFailureScreenShot");
        return message;
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("in onStart Method" + iTestContext.getName());
        iTestContext.setAttribute("AppiumDriver", MobileFunctionLibrary.getDriver());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("in onFinish Method" + iTestContext.getName());
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("in onTestStart Method" + getTestMethodName(iTestResult)+" start");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("in onTestSuccess Method" + getTestMethodName(iTestResult)+" succeed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("in onTestFailure Method" + getTestMethodName(iTestResult)+" failed");
        Object testClass = iTestResult.getInstance();
        AppiumDriver driver = MobileFunctionLibrary.getDriver();
        if(driver instanceof AppiumDriver){
            System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
            saveFailureScreenShot(driver,  getTestMethodName(iTestResult));
        }
        saveTextLog(getTestMethodName(iTestResult)+"failed screenshot taken!");
    }

    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("in onTestSkipped Method" + getTestMethodName(iTestResult)+" skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it in defined success ratio" + getTestMethodName(iTestResult)+" failedButWithinSuccessPercentage");
    }



}
