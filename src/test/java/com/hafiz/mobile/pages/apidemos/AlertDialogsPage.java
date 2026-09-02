package com.hafiz.mobile.pages.apidemos;

import java.time.Duration;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;

import com.hafiz.mobile.pages.BasePage;

/** ApiDemos &rarr; App &rarr; Alert Dialogs ({@code AlertDialogSamples}). */
public class AlertDialogsPage extends BasePage {

    @AndroidFindBy(id = "io.appium.android.apis:id/two_buttons")
    private WebElement okCancelWithMessage;

    @AndroidFindBy(id = "io.appium.android.apis:id/select_button")
    private WebElement listDialogButton;

    @AndroidFindBy(id = "io.appium.android.apis:id/single_choice_button")
    private WebElement singleChoiceButton;

    @AndroidFindBy(id = "io.appium.android.apis:id/text_entry_button")
    private WebElement textEntryButton;

    @AndroidFindBy(id = "android:id/alertTitle")
    private WebElement dialogTitle;

    @AndroidFindBy(id = "android:id/message")
    private WebElement dialogMessage;

    @AndroidFindBy(id = "android:id/button1")
    private WebElement okButton;

    @AndroidFindBy(id = "android:id/button2")
    private WebElement cancelButton;

    public AlertDialogsPage assertLoaded() {
        visible(okCancelWithMessage);
        return this;
    }

    public AlertDialogsPage openOkCancelDialog() {
        tap(okCancelWithMessage);
        return this;
    }

    public AlertDialogsPage openListDialog() {
        tap(listDialogButton);
        return this;
    }

    public AlertDialogsPage openSingleChoiceDialog() {
        tap(singleChoiceButton);
        return this;
    }

    public AlertDialogsPage openTextEntryDialog() {
        tap(textEntryButton);
        return this;
    }

    public String dialogTitle() {
        return textOf(dialogTitle);
    }

    public String dialogMessage() {
        return textOf(dialogMessage);
    }

    public boolean showsItem(String item) {
        return existsByText(item);
    }

    public AlertDialogsPage chooseItem(String item) {
        tapByText(item);
        return this;
    }

    public AlertDialogsPage confirm() {
        tap(okButton);
        return this;
    }

    public AlertDialogsPage cancel() {
        tap(cancelButton);
        return this;
    }

    public boolean dialogClosed() {
        return !isDisplayed(dialogTitle, Duration.ofSeconds(3));
    }

    public boolean dialogOpen() {
        return isDisplayed(dialogTitle, Duration.ofSeconds(5));
    }
}
