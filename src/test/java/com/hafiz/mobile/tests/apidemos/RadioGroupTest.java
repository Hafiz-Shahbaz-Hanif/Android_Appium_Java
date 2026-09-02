package com.hafiz.mobile.tests.apidemos;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.mobile.base.BaseTest;
import com.hafiz.mobile.driver.AppUnderTest;
import com.hafiz.mobile.pages.Navigator;
import com.hafiz.mobile.pages.apidemos.RadioGroupPage;

/** Views &rarr; Radio Group. */
public class RadioGroupTest extends BaseTest {

    @Override
    protected AppUnderTest app() {
        return AppUnderTest.API_DEMOS;
    }

    private RadioGroupPage radioGroup;

    @BeforeMethod(alwaysRun = true)
    public void openScreen() {
        new Navigator().assertAtHome().go("Views", "Radio Group");
        radioGroup = new RadioGroupPage().assertLoaded();
    }

    @DataProvider(name = "meals")
    public Object[][] meals() {
        return new Object[][] {
            {"Snack"}, {"Breakfast"}, {"Lunch"}, {"Dinner"}, {"Brunch"},
        };
    }

    @Test(groups = {"smoke", "apidemos", "widgets"}, dataProvider = "meals")
    public void selectingAnOptionUpdatesTheSummary(String meal) {
        radioGroup.select(meal);
        assertTrue(radioGroup.selectionSummary().contains(meal),
                "summary should name the chosen option '" + meal + "'");
    }

    @Test(groups = {"apidemos", "widgets"})
    public void clearResetsTheSelection() {
        radioGroup.select("Lunch");
        assertTrue(radioGroup.selectionSummary().contains("Lunch"));

        radioGroup.clearSelection();
        assertTrue(radioGroup.selectionSummary().toLowerCase().contains("none"),
                "clearing the group empties the summary");
    }
}
