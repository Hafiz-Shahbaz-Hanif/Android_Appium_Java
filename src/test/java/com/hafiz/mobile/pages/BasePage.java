package com.hafiz.mobile.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import com.hafiz.mobile.config.Configuration;
import com.hafiz.mobile.driver.DriverFactory;

/**
 * Base class for every Page (Screen) Object.
 *
 * <p>Initialises {@code @AndroidFindBy} fields via {@link AppiumFieldDecorator} and
 * provides the shared wait plus the handful of element helpers the screens use.
 */
public abstract class BasePage {

    protected final AndroidDriver driver;
    protected final WebDriverWait wait;

    protected BasePage() {
        this.driver = DriverFactory.get();
        this.wait = new WebDriverWait(driver, Configuration.explicitWait());
        PageFactory.initElements(new AppiumFieldDecorator(driver, Configuration.explicitWait()), this);
    }

    protected WebElement visible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement clickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void tap(WebElement element) {
        clickable(element).click();
    }

    protected void type(WebElement element, String text) {
        WebElement field = visible(element);
        field.clear();
        field.sendKeys(text);
    }

    protected String textOf(WebElement element) {
        return visible(element).getText().trim();
    }

    protected boolean isDisplayed(WebElement element, Duration timeout) {
        try {
            new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    protected List<WebElement> waitForAll(List<WebElement> elements) {
        return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    /** Tap the first node whose exact visible text is {@code text}. */
    protected void tapByText(String text) {
        WebElement node = wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text + "\")")));
        node.click();
    }

    /** True when a node with exactly this visible text is on screen. */
    protected boolean existsByText(String text) {
        return !driver.findElements(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text + "\")")).isEmpty();
    }

    /** Read an attribute, tolerating the Android quirks around {@code text} vs {@code content-desc}. */
    protected String attr(WebElement element, String name) {
        String value = visible(element).getAttribute(name);
        return value == null ? "" : value.trim();
    }

    protected boolean isChecked(WebElement element) {
        return Boolean.parseBoolean(attr(element, "checked"));
    }

    /** Nudge a focused, seekable control with the D-pad. */
    protected void pressDpad(AndroidKey key, int times) {
        for (int i = 0; i < times; i++) {
            driver.pressKey(new KeyEvent(key));
        }
    }
}
