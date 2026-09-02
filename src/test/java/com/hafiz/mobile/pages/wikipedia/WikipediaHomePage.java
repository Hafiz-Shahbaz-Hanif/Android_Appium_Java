package com.hafiz.mobile.pages.wikipedia;

import java.time.Duration;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;

import com.hafiz.mobile.pages.BasePage;

/** Wikipedia &rarr; the main screen and its bottom navigation bar. */
public class WikipediaHomePage extends BasePage {

    @AndroidFindBy(id = "org.wikipedia:id/fragment_onboarding_skip_button")
    private WebElement skipOnboarding;

    @AndroidFindBy(id = "org.wikipedia:id/nav_tab_explore")
    private WebElement exploreTab;

    @AndroidFindBy(id = "org.wikipedia:id/nav_tab_reading_lists")
    private WebElement savedTab;

    @AndroidFindBy(id = "org.wikipedia:id/nav_tab_search")
    private WebElement searchTab;

    @AndroidFindBy(id = "org.wikipedia:id/nav_tab_edits")
    private WebElement editsTab;

    @AndroidFindBy(id = "org.wikipedia:id/feed_view")
    private WebElement feed;

    public WikipediaHomePage dismissOnboarding() {
        if (isDisplayed(skipOnboarding, Duration.ofSeconds(8))) {
            tap(skipOnboarding);
        }
        return this;
    }

    public WikipediaHomePage openExplore() {
        tap(exploreTab);
        return this;
    }

    public WikipediaHomePage openSaved() {
        tap(savedTab);
        return this;
    }

    public WikipediaSearchPage openSearch() {
        tap(searchTab);
        return new WikipediaSearchPage();
    }

    public WikipediaHomePage openEdits() {
        tap(editsTab);
        return this;
    }

    public boolean feedIsVisible() {
        return isDisplayed(feed, Duration.ofSeconds(10));
    }

    /** True when a screen node contains this text (used to assert a tab landed). */
    public boolean showsText(String text) {
        return existsByText(text);
    }
}
