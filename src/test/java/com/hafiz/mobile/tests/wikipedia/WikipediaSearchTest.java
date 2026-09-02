package com.hafiz.mobile.tests.wikipedia;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.mobile.base.BaseTest;
import com.hafiz.mobile.driver.AppUnderTest;
import com.hafiz.mobile.pages.wikipedia.WikipediaSearchPage;

/** Data-driven search coverage against the open-source Wikipedia Android app. */
public class WikipediaSearchTest extends BaseTest {

    @Override
    protected AppUnderTest app() {
        return AppUnderTest.WIKIPEDIA;
    }

    @DataProvider(name = "queries")
    public Object[][] queries() {
        return new Object[][] {
            {"Appium", "appium"},
            {"Selenium", "selenium"},
            {"Software testing", "testing"},
            {"Automation", "automation"},
            {"Kotlin", "kotlin"},
            {"Android", "android"},
        };
    }

    @Test(groups = {"smoke", "wikipedia", "search"}, dataProvider = "queries")
    public void searchReturnsRelevantResults(String query, String expectedFragment) {
        WikipediaSearchPage search = new WikipediaSearchPage().dismissOnboarding().search(query);

        assertTrue(search.resultCount() > 0, "'" + query + "' returns results");
        assertTrue(search.everyResultContains(expectedFragment),
                "every result for '" + query + "' mentions '" + expectedFragment + "'");
    }

    @Test(groups = {"wikipedia", "search"})
    public void aNonsenseQueryShowsTheEmptyState() {
        WikipediaSearchPage search = new WikipediaSearchPage()
                .dismissOnboarding()
                .search("qwertzxcvblkjhg not a real page");

        assertTrue(search.resultCount() == 0 && search.showsNoResults(),
                "an unmatchable query shows the no-results message");
    }
}
