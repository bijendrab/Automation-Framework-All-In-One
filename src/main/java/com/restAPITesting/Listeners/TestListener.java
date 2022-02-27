package com.restAPITesting.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import static com.restAPITesting.config.ConfigurationManager.configuration;
import com.restAPITesting.ExtentReports.ExtentManager;
import com.restAPITesting.utils.logs.Log;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.util.Arrays;
import java.util.List;

public final class TestListener implements ITestListener {
    private static final ExtentReports REPORT = ExtentManager.createExtentReports();

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is succeed.");
        //ExtentReports log operation for passed tests.
        System.out.println("iTestResult Pass: "+ iTestResult);
        //String testData = iTestResult.getParameters()[0].toString();
        List<String> GroupNames = Arrays.asList(iTestResult.getMethod().getGroups());
        ExtentTest test = REPORT.createTest(getTestMethodName(iTestResult))
                .assignCategory(iTestResult.getMethod().getRealClass().getSimpleName())
                .assignCategory(GroupNames.toString())
                .pass(String.format("Test Data: %s%n", "Test Case Passed"));
        //getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is failed.");
        System.out.println("iTestResult Fail: "+ iTestResult);
        //String testData = iTestResult.getParameters()[0].toString();
        //Get driver from BaseTest and assign to local webdriver variable.
        List<String> GroupNames = Arrays.asList(iTestResult.getMethod().getGroups());
        ExtentTest test = REPORT.createTest(getTestMethodName(iTestResult))
                .assignCategory(iTestResult.getMethod().getRealClass().getSimpleName())
                .assignCategory(GroupNames.toString())
                .fail(String.format("Test Data: %s%n", "Test Case Failed"))
                .fail(iTestResult.getThrowable(),
                        MediaEntityBuilder.createScreenCaptureFromPath(
                                String.format("%s%s.png", configuration().baseScreenshotPath(),
                                        iTestResult.getMethod().getMethodName())
                        ).build());

        //getTest().log(Status.FAIL, "Test Failed");
    }

    @Override
    public void onTestSkipped(final ITestResult result) {
        ITestNGMethod method = result.getMethod();
        //String testData = result.getParameters()[0].toString();

        ExtentTest test = REPORT.createTest(method.getMethodName())
                .assignCategory(method.getRealClass().getSimpleName())
                .skip(String.format("Test Data: %s%n", "Test Case Skipped"));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(final ITestContext context) {
        REPORT.flush();
    }
}
