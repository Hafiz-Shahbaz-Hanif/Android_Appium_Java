package com.hafiz.mobile.pages.apidemos;

import java.time.Duration;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;

import com.hafiz.mobile.pages.BasePage;

/** ApiDemos &rarr; Views &rarr; Popup Menu. */
public class PopupMenuPage extends BasePage {

    @AndroidFindBy(id = "io.appium.android.apis:id/button")
    private WebElement makePopupButton;

    public PopupMenuPage assertLoaded() {
        visible(makePopupButton);
        return this;
    }

    public PopupMenuPage openMenu() {
        tap(makePopupButton);
        return this;
    }

    public boolean menuShows(String item) {
        return isDisplayed(
                driver.findElement(AppiumBy.androidUIAutomator(
                        "new UiSelector().text(\"" + item + "\")")),
                Duration.ofSeconds(5));
    }

    public PopupMenuPage choose(String item) {
        tapByText(item);
        return this;
    }

    /** The Toast raised after choosing an item, e.g. {@code Clicked popup menu item Search}. */
    public boolean toastShown(String fragment) {
        return isDisplayed(
                driver.findElement(AppiumBy.androidUIAutomator(
                        "new UiSelector().textContains(\"" + fragment + "\")")),
                Duration.ofSeconds(5));
    }
}
