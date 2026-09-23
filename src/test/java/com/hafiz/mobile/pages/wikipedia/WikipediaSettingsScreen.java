package com.hafiz.mobile.pages.wikipedia;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;

import com.hafiz.mobile.pages.BasePage;

/** Wikipedia &rarr; Explore's overflow menu &rarr; Settings. */
public class WikipediaSettingsScreen extends BasePage {

    @AndroidFindBy(accessibility = "More options")
    private WebElement overflowMenu;

    /** Opens Settings from the currently-visible overflow menu. */
    public WikipediaSettingsScreen open() {
        tap(overflowMenu);
        tapByText("Settings");
        return this;
    }

    /** True once a known top-level settings section is on screen. */
    public boolean isLoaded() {
        return wait.until(d -> showsSetting("Reading preferences") || showsSetting("Appearance"));
    }

    public boolean showsSetting(String label) {
        return existsByText(label);
    }

    public WikipediaSettingsScreen openSetting(String label) {
        tapByText(label);
        return this;
    }
}
