package com.hafiz.mobile.pages.wikipedia;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;

import com.hafiz.mobile.pages.BasePage;

/** Wikipedia &rarr; Saved (the default reading list, reached via the bottom nav). */
public class WikipediaSavedScreen extends BasePage {

    @AndroidFindBy(id = "org.wikipedia:id/page_list_item_title")
    private List<WebElement> savedTitles;

    @AndroidFindBy(id = "org.wikipedia:id/fragment_empty_container")
    private WebElement emptyState;

    /** Number of saved articles, or 0 if the list has not loaded / is empty. */
    public int savedCount() {
        try {
            return waitForAll(savedTitles).size();
        } catch (RuntimeException noItemsYet) {
            return 0;
        }
    }

    public boolean contains(String title) {
        return waitForAll(savedTitles).stream()
                .anyMatch(e -> e.getText().equalsIgnoreCase(title));
    }

    public boolean isEmpty() {
        return isDisplayed(emptyState, Duration.ofSeconds(5)) || savedCount() == 0;
    }
}
