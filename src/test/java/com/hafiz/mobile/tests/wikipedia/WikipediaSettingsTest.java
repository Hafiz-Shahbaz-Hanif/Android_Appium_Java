package com.hafiz.mobile.tests.wikipedia;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.hafiz.mobile.base.BaseTest;
import com.hafiz.mobile.driver.AppUnderTest;
import com.hafiz.mobile.pages.wikipedia.WikipediaHomePage;
import com.hafiz.mobile.pages.wikipedia.WikipediaSettingsScreen;

/** Wikipedia &rarr; Settings, reached from the Explore overflow menu. */
public class WikipediaSettingsTest extends BaseTest {

    @Override
    protected AppUnderTest app() {
        return AppUnderTest.WIKIPEDIA;
    }

    @Test(groups = {"smoke", "wikipedia"})
    public void settingsScreenOpensWithItsKnownSections() {
        new WikipediaHomePage().dismissOnboarding().openExplore();
        WikipediaSettingsScreen settings = new WikipediaSettingsScreen().open();

        assertTrue(settings.isLoaded(), "the Settings screen shows a known section");
    }
}
