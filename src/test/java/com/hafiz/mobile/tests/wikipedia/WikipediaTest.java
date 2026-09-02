package com.hafiz.mobile.tests.wikipedia;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.hafiz.mobile.base.BaseTest;
import com.hafiz.mobile.driver.AppUnderTest;
import com.hafiz.mobile.pages.wikipedia.WikipediaArticlePage;
import com.hafiz.mobile.pages.wikipedia.WikipediaSearchPage;

/** Real-app coverage against the open-source Wikipedia Android app. */
public class WikipediaTest extends BaseTest {

    @Override
    protected AppUnderTest app() {
        return AppUnderTest.WIKIPEDIA;
    }

    @Test(groups = {"smoke", "wikipedia"})
    public void searchReturnsMatchingResults() {
        WikipediaSearchPage search = new WikipediaSearchPage().dismissOnboarding().search("Appium");

        assertTrue(search.resultCount() > 0, "search returns results");
        assertTrue(search.everyResultContains("appium"), "every result matches the query");
    }

    @Test(groups = {"wikipedia"})
    public void openingAResultShowsTheArticleAndItCanBeSaved() {
        WikipediaArticlePage article = new WikipediaSearchPage()
                .dismissOnboarding()
                .search("Selenium (software)")
                .openResult(0);

        assertTrue(article.isLoaded(), "the article renders");
        assertTrue(article.title().toLowerCase().contains("selenium"), "the expected article opened");

        article.saveToReadingList();
        assertTrue(article.lastSnackbarMessage().toLowerCase().contains("saved"),
                "a confirmation snackbar is shown");
    }
}
