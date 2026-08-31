package com.hafiz.mobile.pages.wikipedia;

import java.time.Duration;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;

import com.hafiz.mobile.pages.BasePage;

/** Wikipedia &rarr; an open article. */
public class WikipediaArticlePage extends BasePage {

    @AndroidFindBy(id = "org.wikipedia:id/page_toolbar_button_show_overflow_menu")
    private WebElement overflowMenu;

    @AndroidFindBy(accessibility = "Save")
    private WebElement saveButton;

    @AndroidFindBy(id = "org.wikipedia:id/view_page_title_text")
    private WebElement titleText;

    @AndroidFindBy(id = "org.wikipedia:id/snackbar_text")
    private WebElement snackbar;

    public boolean isLoaded() {
        return isDisplayed(titleText, Duration.ofSeconds(15));
    }

    public String title() {
        return textOf(titleText);
    }

    public WikipediaArticlePage saveToReadingList() {
        tap(saveButton);
        return this;
    }

    public String lastSnackbarMessage() {
        return textOf(snackbar);
    }
}
