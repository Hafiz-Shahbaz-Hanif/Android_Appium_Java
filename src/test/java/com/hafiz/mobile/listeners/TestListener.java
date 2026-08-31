package com.hafiz.mobile.listeners;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.hafiz.mobile.driver.DriverFactory;

/** Mirrors each test into ExtentReports and captures a screenshot on failure. */
public class TestListener implements ITestListener {

    private final ExtentReports extent = ExtentManager.getInstance();
    private final ThreadLocal<ExtentTest> current = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        current.set(extent.createTest(
                result.getTestClass().getRealClass().getSimpleName() + " :: " + result.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        current.get().log(Status.PASS, "Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        current.get().log(Status.FAIL, result.getThrowable());
        String path = capture(result.getName());
        if (path != null) {
            current.get().addScreenCaptureFromPath(path);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        current.get().log(Status.SKIP, "Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    private String capture(String name) {
        try {
            File src = ((TakesScreenshot) DriverFactory.get()).getScreenshotAs(OutputType.FILE);
            Path dir = Paths.get("target", "screenshots");
            Files.createDirectories(dir);
            Path dest = dir.resolve(name + "-" + System.currentTimeMillis() + ".png");
            Files.copy(src.toPath(), dest);
            return dest.toAbsolutePath().toString();
        } catch (Exception e) {
            return null;
        }
    }
}
