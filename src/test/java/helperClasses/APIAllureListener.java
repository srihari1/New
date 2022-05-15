package helperClasses;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class APIAllureListener implements ITestListener {

    private static String getTestMethodName(ITestResult ITestResult) {
    	System.out.println("getTestMethodName");
        return ITestResult.getMethod().getConstructorOrMethod().getName();
    }
    //comment

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("in onStart Method" + iTestContext.getName());
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
    }

    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("in onTestSkipped Method" + getTestMethodName(iTestResult)+" skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it in defined success ratio" + getTestMethodName(iTestResult)+" failedButWithinSuccessPercentage");
    }



}
