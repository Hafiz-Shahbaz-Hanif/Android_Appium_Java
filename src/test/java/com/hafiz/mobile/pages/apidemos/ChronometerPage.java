package com.hafiz.mobile.pages.apidemos;

import java.time.Duration;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;

import com.hafiz.mobile.pages.BasePage;

/** ApiDemos &rarr; Views &rarr; Chronometer. */
public class ChronometerPage extends BasePage {

    @AndroidFindBy(id = "io.appium.android.apis:id/chronometer")
    private WebElement chronometer;

    @AndroidFindBy(id = "io.appium.android.apis:id/start")
    private WebElement startButton;

    @AndroidFindBy(id = "io.appium.android.apis:id/stop")
    private WebElement stopButton;

    @AndroidFindBy(id = "io.appium.android.apis:id/reset")
    private WebElement resetButton;

    @AndroidFindBy(id = "io.appium.android.apis:id/set_format")
    private WebElement setFormatButton;

    public ChronometerPage assertLoaded() {
        visible(chronometer);
        return this;
    }

    public String display() {
        return textOf(chronometer);
    }

    public ChronometerPage start() {
        tap(startButton);
        return this;
    }

    public ChronometerPage stop() {
        tap(stopButton);
        return this;
    }

    public ChronometerPage reset() {
        tap(resetButton);
        return this;
    }

    public ChronometerPage applyCustomFormat() {
        tap(setFormatButton);
        return this;
    }

    /**
     * Wait until the running display shows a value other than {@code from}, proving the
     * clock is ticking. This is a widget whose behaviour <em>is</em> the passage of time,
     * so the dwell is encapsulated here rather than leaking a sleep into a test.
     */
    public ChronometerPage waitForTick(String from) {
        new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> !display().equals(from));
        return this;
    }

    /** Confirm the display holds {@code value} steady for {@code window} (used after stop/reset). */
    public boolean staysAt(String value, Duration window) {
        long deadline = System.currentTimeMillis() + window.toMillis();
        while (System.currentTimeMillis() < deadline) {
            if (!display().equals(value)) {
                return false;
            }
        }
        return true;
    }
}
