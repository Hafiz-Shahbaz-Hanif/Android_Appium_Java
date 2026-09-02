package com.hafiz.mobile.tests.apidemos;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.mobile.base.BaseTest;
import com.hafiz.mobile.driver.AppUnderTest;
import com.hafiz.mobile.pages.Navigator;
import com.hafiz.mobile.pages.apidemos.ControlsPage;

/** Views &rarr; Controls &rarr; 1. Light Theme - checkbox, radio group, star, toggle, text field, spinner. */
public class ControlsWidgetTest extends BaseTest {

    @Override
    protected AppUnderTest app() {
        return AppUnderTest.API_DEMOS;
    }

    private ControlsPage controls;

    @BeforeMethod(alwaysRun = true)
    public void openControls() {
        new Navigator().assertAtHome().go("Views", "Controls", "1. Light Theme");
        controls = new ControlsPage().assertLoaded();
    }

    @Test(groups = {"smoke", "apidemos", "widgets"})
    public void checkboxToggles() {
        boolean before = controls.isCheckboxChecked();
        controls.toggleCheckbox();
        assertNotEquals(controls.isCheckboxChecked(), before);
    }

    @Test(groups = {"apidemos", "widgets"})
    public void radioGroupIsMutuallyExclusive() {
        controls.selectFirstRadio();
        assertTrue(controls.firstRadioSelected());

        controls.selectSecondRadio();
        assertTrue(controls.secondRadioSelected());
        assertFalse(controls.firstRadioSelected(), "selecting the second radio clears the first");
    }

    @Test(groups = {"apidemos", "widgets"})
    public void starButtonToggles() {
        boolean before = controls.starChecked();
        controls.toggleStar();
        assertNotEquals(controls.starChecked(), before);
    }

    @Test(groups = {"apidemos", "widgets"})
    public void toggleButtonFlipsBetweenOnAndOff() {
        String before = controls.toggleButtonState();
        controls.pressToggleButton();
        assertNotEquals(controls.toggleButtonState(), before);
    }

    @DataProvider(name = "phrases")
    public Object[][] phrases() {
        return new Object[][] {
            {"Hafiz QA"},
            {"appium 2"},
            {"1234567890"},
            {"special _-.@ chars"},
            {"MixedCASE input"},
        };
    }

    @Test(groups = {"apidemos", "widgets"}, dataProvider = "phrases")
    public void editTextEchoesWhatWasTyped(String phrase) {
        controls.enterText(phrase);
        assertEquals(controls.enteredText(), phrase);
    }

    @DataProvider(name = "planets")
    public Object[][] planets() {
        return new Object[][] {{"Mercury"}, {"Venus"}, {"Mars"}, {"Jupiter"}, {"Saturn"}};
    }

    @Test(groups = {"apidemos", "widgets"}, dataProvider = "planets")
    public void spinnerSelectionSticks(String planet) {
        controls.chooseSpinnerValue(planet);
        assertTrue(controls.showsSpinnerValue(planet), planet + " should be the shown selection");
    }
}
