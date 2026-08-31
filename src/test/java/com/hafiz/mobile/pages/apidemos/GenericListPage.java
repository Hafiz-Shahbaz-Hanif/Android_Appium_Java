package com.hafiz.mobile.pages.apidemos;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;

import com.hafiz.mobile.pages.BasePage;

/**
 * A reusable page for any of ApiDemos' intermediate list screens
 * (App, Views, Content, ...). Scrolls an item into view and taps it.
 */
public class GenericListPage extends BasePage {

    public GenericListPage tapItem(String label) {
        WebElement item = driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))"
                        + ".scrollIntoView(new UiSelector().text(\"" + label + "\"))"));
        clickable(item).click();
        return this;
    }
}
