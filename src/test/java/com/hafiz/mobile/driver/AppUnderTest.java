package com.hafiz.mobile.driver;

/**
 * The apps this suite drives. Both are free and openly distributed:
 *
 * <ul>
 *   <li>{@code API_DEMOS} - Appium's official sample app
 *       (<a href="https://github.com/appium/android-apidemos">appium/android-apidemos</a>),
 *       the canonical target for native-gesture and widget automation.</li>
 *   <li>{@code WIKIPEDIA} - the open-source Wikipedia Android app
 *       (<a href="https://github.com/wikimedia/apps-android-wikipedia">wikimedia/apps-android-wikipedia</a>),
 *       a real production app with search and article flows.</li>
 * </ul>
 *
 * Place the APKs in {@code apps/} (see {@code scripts/fetch-apps.*}); if an APK is
 * missing the driver falls back to launching the app by package/activity.
 */
public enum AppUnderTest {

    API_DEMOS(
            "ApiDemos-debug.apk",
            "io.appium.android.apis",
            "io.appium.android.apis.ApiDemos"),

    WIKIPEDIA(
            "wikipedia.apk",
            "org.wikipedia",
            "org.wikipedia.main.MainActivity");

    private final String apkFileName;
    private final String appPackage;
    private final String appActivity;

    AppUnderTest(String apkFileName, String appPackage, String appActivity) {
        this.apkFileName = apkFileName;
        this.appPackage = appPackage;
        this.appActivity = appActivity;
    }

    public String apkFileName() {
        return apkFileName;
    }

    public String appPackage() {
        return appPackage;
    }

    public String appActivity() {
        return appActivity;
    }
}
