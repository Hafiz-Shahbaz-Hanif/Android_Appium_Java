package com.hafiz.mobile.pages.apidemos;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;

import com.hafiz.mobile.pages.BasePage;

/** ApiDemos &rarr; the top-level list of demo categories. */
public class ApiDemosHomePage extends BasePage {

    @AndroidFindBy(id = "android:id/list")
    private WebElement list;

    public ApiDemosHomePage assertLoaded() {
        visible(list);
        return this;
    }

    public <T> T open(String category, java.util.function.Supplier<T> nextPage) {
        tapItem(category);
        return nextPage.get();
    }

    public void tapItem(String label) {
        WebElement item = driver.findElement(
                io.appium.java_client.AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))"
                                + ".scrollIntoView(new UiSelector().text(\"" + label + "\"))"));
        clickable(item).click();
    }
}
