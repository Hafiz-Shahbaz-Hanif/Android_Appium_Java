package com.hafiz.mobile.tests.apidemos;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hafiz.mobile.base.BaseTest;
import com.hafiz.mobile.driver.AppUnderTest;
import com.hafiz.mobile.pages.Navigator;
import com.hafiz.mobile.pages.apidemos.DateWidgetsPage;

/** Views &rarr; Date Widgets &rarr; 1. Dialog. */
public class DateWidgetsTest extends BaseTest {

    @Override
    protected AppUnderTest app() {
        return AppUnderTest.API_DEMOS;
    }

    private DateWidgetsPage dateWidgets;

    @BeforeMethod(alwaysRun = true)
    public void openScreen() {
        new Navigator().assertAtHome().go("Views", "Date Widgets", "1. Dialog");
        dateWidgets = new DateWidgetsPage().assertLoaded();
    }

    @Test(groups = {"smoke", "apidemos", "widgets"})
    public void theScreenShowsADate() {
        assertFalse(dateWidgets.displayedDate().isBlank(), "a date is displayed on load");
    }

    @Test(groups = {"apidemos", "widgets"})
    public void thePickerOpensAndConfirms() {
        dateWidgets.openPicker();
        assertTrue(dateWidgets.pickerIsOpen(), "tapping the button opens the picker dialog");

        dateWidgets.confirmPicker();
        assertFalse(dateWidgets.pickerIsOpen(), "OK dismisses the dialog");
    }

    @Test(groups = {"apidemos", "widgets"})
    public void cancellingThePickerLeavesTheDateUnchanged() {
        String before = dateWidgets.displayedDate();
        dateWidgets.openPicker().cancelPicker();
        assertEquals(dateWidgets.displayedDate(), before);
    }
}
