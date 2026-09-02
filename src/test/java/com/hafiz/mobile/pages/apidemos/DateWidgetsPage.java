package com.hafiz.mobile.pages.apidemos;

import java.time.Duration;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;

import com.hafiz.mobile.pages.BasePage;

/** ApiDemos &rarr; Views &rarr; Date Widgets &rarr; 1. Dialog. */
public class DateWidgetsPage extends BasePage {

    @AndroidFindBy(id = "io.appium.android.apis:id/dateDisplay")
    private WebElement dateDisplay;

    @AndroidFindBy(id = "io.appium.android.apis:id/pickDate")
    private WebElement pickDateButton;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement okButton;

    @AndroidFindBy(id = "android:id/button2")
    private WebElement cancelButton;

    public DateWidgetsPage assertLoaded() {
        visible(dateDisplay);
        return this;
    }

    public String displayedDate() {
        return textOf(dateDisplay);
    }

    public DateWidgetsPage openPicker() {
        tap(pickDateButton);
        return this;
    }

    public boolean pickerIsOpen() {
        return isDisplayed(okButton, Duration.ofSeconds(5));
    }

    public DateWidgetsPage confirmPicker() {
        tap(okButton);
        return this;
    }

    public DateWidgetsPage cancelPicker() {
        tap(cancelButton);
        return this;
    }
}
