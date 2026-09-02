package com.hafiz.mobile.pages.wikipedia;

import java.time.Duration;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;

import com.hafiz.mobile.pages.BasePage;

/** Wikipedia &rarr; an open article. */
public class WikipediaArticlePage extends BasePage {

    @AndroidFindBy(accessibility = "Save")
    private WebElement saveButton;

    @AndroidFindBy(id = "org.wikipedia:id/page_toolbar_button_find_in_page")
    private WebElement findInPageButton;

    @AndroidFindBy(id = "org.wikipedia:id/view_page_title_text")
    private WebElement titleText;

    @AndroidFindBy(id = "org.wikipedia:id/snackbar_text")
    private WebElement snackbar;

    @AndroidFindBy(id = "org.wikipedia:id/find_in_page_match")
    private WebElement findMatchCounter;

    @AndroidFindBy(id = "org.wikipedia:id/find_in_page_input")
    private WebElement findInput;

    @AndroidFindBy(accessibility = "Navigate up")
    private WebElement navigateUp;

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

    public WikipediaArticlePage findInPage(String term) {
        tap(findInPageButton);
        type(findInput, term);
        return this;
    }

    public String findMatchCount() {
        return textOf(findMatchCounter);
    }

    public WikipediaSearchPage goBack() {
        tap(navigateUp);
        return new WikipediaSearchPage();
    }
}
