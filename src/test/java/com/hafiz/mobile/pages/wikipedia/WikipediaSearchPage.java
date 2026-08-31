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

    public WikipediaSearchPage dismissOnboarding() {
        if (isDisplayed(skipOnboarding, Duration.ofSeconds(8))) {
            tap(skipOnboarding);
        }
        return this;
    }

    public WikipediaSearchPage search(String term) {
        tap(searchContainer);
        type(searchInput, term);
        return this;
    }

    public int resultCount() {
        return waitForAll(resultTitles).size();
    }

    public boolean everyResultContains(String term) {
        return waitForAll(resultTitles).stream()
                .map(WebElement::getText)
                .allMatch(t -> t.toLowerCase().contains(term.toLowerCase()));
    }

    public WikipediaArticlePage openResult(int index) {
        waitForAll(resultTitles).get(index).click();
        return new WikipediaArticlePage();
    }
}
