package com.hafiz.mobile.tests.wikipedia;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hafiz.mobile.base.BaseTest;
import com.hafiz.mobile.driver.AppUnderTest;
import com.hafiz.mobile.pages.wikipedia.WikipediaHomePage;
import com.hafiz.mobile.pages.wikipedia.WikipediaSearchPage;

/** Bottom-navigation coverage: Explore, Saved, Search, Edits. */
public class WikipediaNavigationTest extends BaseTest {

    @Override
    protected AppUnderTest app() {
        return AppUnderTest.WIKIPEDIA;
    }

    private WikipediaHomePage home;

    @BeforeMethod(alwaysRun = true)
    public void openHome() {
        home = new WikipediaHomePage().dismissOnboarding();
    }

    @Test(groups = {"smoke", "wikipedia", "navigation"})
    public void exploreTabShowsTheFeed() {
        home.openExplore();
        assertTrue(home.feedIsVisible(), "the Explore feed renders");
    }

    @Test(groups = {"wikipedia", "navigation"})
    public void savedTabOpensReadingLists() {
        home.openSaved();
        assertTrue(home.showsText("Saved") || home.showsText("My reading list"),
                "the Saved / reading-lists screen opens");
    }

    @Test(groups = {"wikipedia", "navigation"})
    public void searchTabOpensTheSearchField() {
        WikipediaSearchPage search = home.openSearch().search("Wikipedia");
        assertTrue(search.resultCount() > 0, "the search field is usable from the tab");
    }

    @Test(groups = {"wikipedia", "navigation"})
    public void editsTabOpens() {
        home.openEdits();
        assertTrue(home.showsText("Edits") || home.showsText("Contributions")
                        || home.showsText("Suggested edits"),
                "the Edits tab opens its landing screen");
    }
}
