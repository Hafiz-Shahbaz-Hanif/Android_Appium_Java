package com.hafiz.mobile.pages.wikipedia;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;

import com.hafiz.mobile.pages.BasePage;

/** Wikipedia &rarr; home + search. */
public class WikipediaSearchPage extends BasePage {

    @AndroidFindBy(id = "org.wikipedia:id/fragment_onboarding_skip_button")
    private WebElement skipOnboarding;

    @AndroidFindBy(accessibility = "Search Wikipedia")
    private WebElement searchContainer;

    @AndroidFindBy(id = "org.wikipedia:id/search_src_text")
    private WebElement searchInput;

    @AndroidFindBy(id = "org.wikipedia:id/page_list_item_title")
    private List<WebElement> resultTitles;

    @AndroidFindBy(id = "org.wikipedia:id/search_empty_text")
    private WebElement noResultsMessage;

    @AndroidFindBy(id = "org.wikipedia:id/search_close_btn")
    private WebElement clearButton;

    public WikipediaSearchPage dismissOnboarding() {
        if (isDisplayed(skipOnboarding, Duration.ofSeconds(8))) {
            tap(skipOnboarding);
        }
        return this;
    }

    public WikipediaSearchPage search(String term) {
        if (!isDisplayed(searchInput, Duration.ofSeconds(2))) {
            tap(searchContainer);
        }
        type(searchInput, term);
        return this;
    }

    public int resultCount() {
        try {
            return waitForAll(resultTitles).size();
        } catch (RuntimeException noResults) {
            return 0;
        }
    }

    public boolean everyResultContains(String term) {
        return waitForAll(resultTitles).stream()
                .map(WebElement::getText)
                .allMatch(t -> t.toLowerCase().contains(term.toLowerCase()));
    }

    public boolean anyResultEquals(String title) {
        return waitForAll(resultTitles).stream()
                .map(WebElement::getText)
                .anyMatch(t -> t.equalsIgnoreCase(title));
    }

    public boolean showsNoResults() {
        return isDisplayed(noResultsMessage, Duration.ofSeconds(6));
    }

    public WikipediaSearchPage clearQuery() {
        tap(clearButton);
        return this;
    }

    public WikipediaArticlePage openResult(int index) {
        waitForAll(resultTitles).get(index).click();
        return new WikipediaArticlePage();
    }

    public WikipediaArticlePage openResultTitled(String title) {
        waitForAll(resultTitles).stream()
                .filter(e -> e.getText().equalsIgnoreCase(title))
                .findFirst()
                .orElseThrow(() -> new AssertionError("no result titled '" + title + "'"))
                .click();
        return new WikipediaArticlePage();
    }
}
