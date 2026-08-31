package com.hafiz.mobile.config;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

/**
 * Typed configuration.
 *
 * <p>Resolution order for every key: JVM system property ({@code -Dkey=value})
 * &rarr; environment variable &rarr; {@code config.properties} &rarr; default.
 */
public final class Configuration {

    private static final Properties FILE_PROPS = load();

    private Configuration() {
    }

    private static Properties load() {
        Properties props = new Properties();
        try (InputStream in = Configuration.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (in != null) {
                props.load(in);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Unable to read config.properties", e);
        }
        return props;
    }

    private static String resolve(String key, String defaultValue) {
        String sys = System.getProperty(key);
        if (sys != null && !sys.isBlank()) {
            return sys;
        }
        String env = System.getenv(key.toUpperCase().replace('.', '_'));
        if (env != null && !env.isBlank()) {
            return env;
        }
        return FILE_PROPS.getProperty(key, defaultValue);
    }

    /** Appium server the tests connect to. Blank means "start one locally". */
    public static String appiumServerUrl() {
        return resolve("appium.server.url", "");
    }

    public static boolean startAppiumServer() {
        return appiumServerUrl().isBlank();
    }

    public static String deviceName() {
        return resolve("android.device.name", "Android Emulator");
    }

    public static String platformVersion() {
        return resolve("android.platform.version", "");
    }

    public static String automationName() {
        return resolve("android.automation.name", "UiAutomator2");
    }

    public static String appiumHost() {
        return resolve("appium.host", "127.0.0.1");
    }

    public static int appiumPort() {
        return Integer.parseInt(resolve("appium.port", "4723"));
    }

    public static Duration explicitWait() {
        return Duration.ofSeconds(Long.parseLong(resolve("wait.explicit.seconds", "20")));
    }

    public static Duration newCommandTimeout() {
        return Duration.ofSeconds(Long.parseLong(resolve("wait.newcommand.seconds", "120")));
    }
}
