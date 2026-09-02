package com.hafiz.mobile.pages.apidemos;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;

import com.hafiz.mobile.pages.BasePage;

/** ApiDemos &rarr; Views &rarr; Controls &rarr; 1. Light Theme / 2. Dark Theme. */
public class ControlsPage extends BasePage {

    @AndroidFindBy(id = "io.appium.android.apis:id/edit")
    private WebElement editText;

    @AndroidFindBy(id = "io.appium.android.apis:id/check1")
    private WebElement checkbox;

    @AndroidFindBy(id = "io.appium.android.apis:id/radio1")
    private WebElement firstRadio;

    @AndroidFindBy(id = "io.appium.android.apis:id/radio2")
    private WebElement secondRadio;

    @AndroidFindBy(id = "io.appium.android.apis:id/star")
    private WebElement starToggle;

    @AndroidFindBy(id = "io.appium.android.apis:id/toggle1")
    private WebElement toggleButton;

    @AndroidFindBy(id = "io.appium.android.apis:id/spinner1")
    private WebElement spinner;

    public ControlsPage assertLoaded() {
        visible(checkbox);
        return this;
    }

    public boolean isCheckboxChecked() {
        return isChecked(checkbox);
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

    public ControlsPage selectFirstRadio() {
        tap(firstRadio);
        return this;
    }

    public ControlsPage selectSecondRadio() {
        tap(secondRadio);
        return this;
    }

    public boolean firstRadioSelected() {
        return isChecked(firstRadio);
    }

    public boolean secondRadioSelected() {
        return isChecked(secondRadio);
    }

    public boolean starChecked() {
        return isChecked(starToggle);
    }

    public ControlsPage toggleStar() {
        tap(starToggle);
        return this;
    }

    public String toggleButtonState() {
        return textOf(toggleButton);
    }

    public ControlsPage pressToggleButton() {
        tap(toggleButton);
        return this;
    }

    public ControlsPage chooseSpinnerValue(String value) {
        tap(spinner);
        tapByText(value);
        return this;
    }

    public boolean showsSpinnerValue(String value) {
        return existsByText(value);
    }
}
