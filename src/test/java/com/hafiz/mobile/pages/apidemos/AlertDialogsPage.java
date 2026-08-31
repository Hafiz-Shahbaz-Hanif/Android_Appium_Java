package com.hafiz.mobile.pages.apidemos;

import java.time.Duration;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;

import com.hafiz.mobile.pages.BasePage;

/** ApiDemos &rarr; App &rarr; Alert Dialogs. */
public class AlertDialogsPage extends BasePage {

    @AndroidFindBy(id = "io.appium.android.apis:id/two_buttons")
    private WebElement okCancelWithMessage;

    @AndroidFindBy(id = "android:id/alertTitle")
    private WebElement dialogTitle;

    @AndroidFindBy(id = "android:id/message")
    private WebElement dialogMessage;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement okButton;

    public AlertDialogsPage openOkCancelDialog() {
        tap(okCancelWithMessage);
        return this;
    }

    public String dialogTitle() {
        return textOf(dialogTitle);
    }

    public String dialogMessage() {
        return textOf(dialogMessage);
    }

    public AlertDialogsPage confirm() {
        tap(okButton);
        return this;
    }

    public boolean dialogClosed() {
        return !isDisplayed(dialogTitle, Duration.ofSeconds(3));
    }
}
