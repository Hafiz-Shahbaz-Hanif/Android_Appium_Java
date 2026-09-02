package com.hafiz.mobile.pages;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;

/**
 * Text-based navigation for ApiDemos.
 *
 * <p>ApiDemos is a tree of scrollable lists. Every screen is reached by scrolling
 * a label into view and tapping it - never by index or coordinates. This screen
 * object encapsulates that traversal and the "where am I now" queries the tests
 * assert on.
 */
public class Navigator extends BasePage {

    @io.appium.java_client.pagefactory.AndroidFindBy(id = "android:id/list")
    private WebElement list;

    /** Assert the ApiDemos launch list is showing. */
    public Navigator assertAtHome() {
        visible(list);
        return this;
    }

    /** Scroll {@code label} into view in the current list and tap it. */
    public Navigator open(String label) {
        WebElement item = driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0))"
                        + ".scrollIntoView(new UiSelector().text(\"" + label + "\"))"));
        clickable(item).click();
        return this;
    }

    /** Walk a path of labels from the current screen, e.g. {@code go("Views", "Controls", "1. Light Theme")}. */
    public Navigator go(String... labels) {
        for (String label : labels) {
            open(label);
        }
        return this;
    }

    /** The activity the app is currently showing, e.g. {@code .view.Controls1}. */
    public String currentActivity() {
        return String.valueOf(driver.executeScript("mobile: getCurrentActivity"));
    }

    /** The package the app is currently showing. */
    public String currentPackage() {
        return String.valueOf(driver.executeScript("mobile: getCurrentPackage"));
    }

    /** True when {@link #currentActivity()} ends with {@code suffix} (leading dot optional). */
    public boolean isOnActivity(String suffix) {
        return currentActivity().endsWith(suffix);
    }

    /** True when a node with exactly this visible text is present on screen. */
    public boolean showsText(String text) {
        return !driver.findElements(AppiumBy.androidUIAutomator(
                "new UiSelector().text(\"" + text + "\")")).isEmpty();
    }

    /** Press the device Back button. */
    public Navigator back() {
        driver.navigate().back();
        return this;
    }
}
