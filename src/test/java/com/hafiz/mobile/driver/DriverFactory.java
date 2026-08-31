package com.hafiz.mobile.driver;

import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import com.hafiz.mobile.config.Configuration;

/**
 * Creates an {@link AndroidDriver} per thread and shares it through a {@link ThreadLocal}.
 *
 * <p>Each test supplies the app it needs via {@link AppUnderTest}; capabilities common to
 * every session live here.
 */
public final class DriverFactory {

    private static final ThreadLocal<AndroidDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static AndroidDriver get() {
        AndroidDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("Driver not initialised for this thread");
        }
        return driver;
    }

    public static void create(AppUnderTest app) {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName(Configuration.automationName())
                .setDeviceName(Configuration.deviceName())
                .setNewCommandTimeout(Configuration.newCommandTimeout())
                .setAutoGrantPermissions(true)
                .setFullReset(false);

        if (!Configuration.platformVersion().isBlank()) {
            options.setPlatformVersion(Configuration.platformVersion());
        }
        applyApp(options, app);

        try {
            DRIVER.set(new AndroidDriver(URI.create(AppiumServer.url()).toURL(), options));
        } catch (MalformedURLException e) {
            throw new IllegalStateException("Invalid Appium server URL", e);
        }
    }

    public static void quit() {
        AndroidDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }

    private static void applyApp(UiAutomator2Options options, AppUnderTest app) {
        Path apk = Paths.get("apps", app.apkFileName());
        if (Files.exists(apk)) {
            options.setApp(apk.toAbsolutePath().toString());
        } else {
            // Fall back to launching an app that is already installed on the device.
            options.setAppPackage(app.appPackage()).setAppActivity(app.appActivity());
        }
    }
}
