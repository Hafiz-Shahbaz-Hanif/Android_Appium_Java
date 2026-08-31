package com.hafiz.mobile.driver;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import com.hafiz.mobile.config.Configuration;

/**
 * Manages a local Appium 2 server for the whole test run.
 *
 * <p>Skipped entirely when {@code appium.server.url} points at an already-running
 * server (a Grid, a cloud provider, or a manually started {@code appium}).
 */
public final class AppiumServer {

    private static AppiumDriverLocalService service;

    private AppiumServer() {
    }

    public static synchronized void start() {
        if (!Configuration.startAppiumServer() || service != null) {
            return;
        }
        service = new AppiumServiceBuilder()
                .withIPAddress(Configuration.appiumHost())
                .usingPort(Configuration.appiumPort())
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(GeneralServerFlag.RELAXED_SECURITY)
                .withTimeout(java.time.Duration.ofSeconds(60))
                .build();
        service.start();
        service.clearOutPutStreams();
    }

    public static synchronized void stop() {
        if (service != null && service.isRunning()) {
            service.stop();
        }
        service = null;
    }

    public static String url() {
        if (!Configuration.startAppiumServer()) {
            return Configuration.appiumServerUrl();
        }
        waitUntilRunning();
        return service.getUrl().toString();
    }

    private static void waitUntilRunning() {
        long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(30);
        while (System.nanoTime() < deadline) {
            if (service != null && service.isRunning()) {
                return;
            }
            sleep();
        }
        throw new IllegalStateException("Appium server did not start within 30s");
    }

    private static void sleep() {
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
