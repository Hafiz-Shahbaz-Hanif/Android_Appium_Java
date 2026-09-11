package com.hafiz.mobile.tests.wikipedia;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.hafiz.mobile.base.BaseTest;
import com.hafiz.mobile.driver.AppUnderTest;
import com.hafiz.mobile.pages.wikipedia.WikipediaArticlePage;
import com.hafiz.mobile.pages.wikipedia.WikipediaHomePage;
import com.hafiz.mobile.pages.wikipedia.WikipediaSavedScreen;
import com.hafiz.mobile.pages.wikipedia.WikipediaSearchPage;

/** Wikipedia &rarr; Saved: an article saved from search shows up in the reading list. */
public class WikipediaSavedTest extends BaseTest {

    @Override
    protected AppUnderTest app() {
        return AppUnderTest.WIKIPEDIA;
    }

    @Test(groups = {"smoke", "wikipedia"})
    public void savingAnArticleAddsItToTheSavedList() {
        WikipediaArticlePage article = new WikipediaSearchPage()
                .dismissOnboarding()
                .search("Selenium (software)")
                .openResult(0);
        article.saveToReadingList();

        new WikipediaHomePage().openSaved();
        WikipediaSavedScreen saved = new WikipediaSavedScreen();

        assertTrue(saved.savedCount() > 0, "the saved list is not empty");
        assertTrue(saved.contains("Selenium (software)") || saved.contains("Selenium"),
                "the saved article appears in the list");
    }
}
