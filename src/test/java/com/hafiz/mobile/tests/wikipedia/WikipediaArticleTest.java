package com.hafiz.mobile.tests.wikipedia;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hafiz.mobile.base.BaseTest;
import com.hafiz.mobile.driver.AppUnderTest;
import com.hafiz.mobile.pages.wikipedia.WikipediaArticlePage;
import com.hafiz.mobile.pages.wikipedia.WikipediaSearchPage;

/** Article-level behaviour: open, title, save, find-in-page. */
public class WikipediaArticleTest extends BaseTest {

    @Override
    protected AppUnderTest app() {
        return AppUnderTest.WIKIPEDIA;
    }

    private WikipediaArticlePage article;

    @BeforeMethod(alwaysRun = true)
    public void openArticle() {
        article = new WikipediaSearchPage()
                .dismissOnboarding()
                .search("Selenium (software)")
                .openResult(0);
        assertTrue(article.isLoaded(), "the article renders");
    }

    @Test(groups = {"smoke", "wikipedia", "article"})
    public void theExpectedArticleOpens() {
        assertTrue(article.title().toLowerCase().contains("selenium"));
    }

    @Test(groups = {"wikipedia", "article"})
    public void anArticleCanBeSavedToAReadingList() {
        article.saveToReadingList();
        assertTrue(article.lastSnackbarMessage().toLowerCase().contains("saved"),
                "a confirmation snackbar is shown");
    }

    @Test(groups = {"wikipedia", "article"})
    public void findInPageReportsMatches() {
        article.findInPage("browser");
        assertTrue(article.findMatchCount().matches(".*\\d.*"),
                "the find bar shows a match count");
    }

    @Test(groups = {"wikipedia", "article"})
    public void navigatingBackReturnsToResults() {
        WikipediaSearchPage results = article.goBack();
        assertTrue(results.resultCount() > 0, "the results list is still there");
    }
}
