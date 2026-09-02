package com.hafiz.mobile.pages.apidemos;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;

import com.hafiz.mobile.pages.BasePage;

/** ApiDemos &rarr; Views &rarr; Radio Group. */
public class RadioGroupPage extends BasePage {

    @AndroidFindBy(id = "io.appium.android.apis:id/menu")
    private WebElement clearButton;

    @AndroidFindBy(id = "io.appium.android.apis:id/choice")
    private WebElement choiceLabel;

    public RadioGroupPage assertLoaded() {
        wait.until(d -> existsByText("Snack"));
        return this;
    }

    public RadioGroupPage select(String option) {
        tapByText(option);
        return this;
    }

    /** The "You have selected: X" summary at the bottom. */
    public String selectionSummary() {
        return textOf(choiceLabel);
    }

    public RadioGroupPage clearSelection() {
        tap(clearButton);
        return this;
    }
}
