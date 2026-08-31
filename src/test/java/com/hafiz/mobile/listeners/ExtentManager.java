package com.hafiz.mobile.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/** Lazily-created singleton {@link ExtentReports} instance. */
public final class ExtentManager {

    private static final String REPORT_PATH = "target/extent-report/index.html";
    private static ExtentReports extent;

    private ExtentManager() {
    }

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter(REPORT_PATH);
            reporter.config().setTheme(Theme.DARK);
            reporter.config().setDocumentTitle("Android Appium - Automation Report");
            reporter.config().setReportName("Native Android Regression");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Framework", "Appium 2 + Java + TestNG (Screen Object Model)");
            extent.setSystemInfo("Automation", "UiAutomator2");
        }
        return extent;
    }
}
