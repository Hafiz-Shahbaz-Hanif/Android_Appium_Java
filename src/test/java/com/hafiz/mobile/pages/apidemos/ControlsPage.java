package com.hafiz.mobile.pages.apidemos;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;

import com.hafiz.mobile.pages.BasePage;

/** ApiDemos &rarr; Views &rarr; Controls &rarr; 1. Light Theme. */
public class ControlsPage extends BasePage {

    @AndroidFindBy(id = "io.appium.android.apis:id/edit")
    private WebElement editText;

    @AndroidFindBy(id = "io.appium.android.apis:id/check1")
    private WebElement checkbox;

    @AndroidFindBy(id = "io.appium.android.apis:id/radio1")
    private WebElement firstRadio;

    public ControlsPage assertLoaded() {
        visible(checkbox);
        return this;
    }

    public boolean isCheckboxChecked() {
        return Boolean.parseBoolean(visible(checkbox).getAttribute("checked"));
    }

    public ControlsPage toggleCheckbox() {
        tap(checkbox);
        return this;
    }

    public ControlsPage enterText(String text) {
        type(editText, text);
        return this;
    }

    public String enteredText() {
        return textOf(editText);
    }
}
