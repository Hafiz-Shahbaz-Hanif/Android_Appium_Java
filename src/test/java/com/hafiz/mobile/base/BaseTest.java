package com.hafiz.mobile.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.hafiz.mobile.driver.AppUnderTest;
import com.hafiz.mobile.driver.AppiumServer;
import com.hafiz.mobile.driver.DriverFactory;
import com.hafiz.mobile.listeners.TestListener;

/**
 * Common lifecycle: a local Appium server for the whole run, and a fresh
 * {@link com.hafiz.mobile.driver.DriverFactory} session per test method for the
 * app the concrete test class declares.
 */
@Listeners(TestListener.class)
public abstract class BaseTest {

    /** The app this test class drives. */
    protected abstract AppUnderTest app();

    @BeforeSuite(alwaysRun = true)
    public void startServer() {
        AppiumServer.start();
    }

    @AfterSuite(alwaysRun = true)
    public void stopServer() {
        AppiumServer.stop();
    }

    @BeforeMethod(alwaysRun = true)
    public void createSession() {
        DriverFactory.create(app());
    }

    @AfterMethod(alwaysRun = true)
    public void endSession() {
        DriverFactory.quit();
    }
}
