package com.hafiz.mobile.tests.apidemos;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.mobile.base.BaseTest;
import com.hafiz.mobile.driver.AppUnderTest;
import com.hafiz.mobile.pages.Navigator;
import com.hafiz.mobile.pages.apidemos.SpinnerPage;

/** Views &rarr; Spinner - the planet dropdown. */
public class SpinnerTest extends BaseTest {

    @Override
    protected AppUnderTest app() {
        return AppUnderTest.API_DEMOS;
    }

    private SpinnerPage spinner;

    @BeforeMethod(alwaysRun = true)
    public void openSpinner() {
        new Navigator().assertAtHome().go("Views", "Spinner");
        spinner = new SpinnerPage().assertLoaded();
    }

    @DataProvider(name = "planets")
    public Object[][] planets() {
        return new Object[][] {
            {"Mercury"}, {"Venus"}, {"Earth"}, {"Mars"}, {"Jupiter"}, {"Saturn"},
        };
    }

    @Test(groups = {"smoke", "apidemos", "widgets"}, dataProvider = "planets")
    public void choosingAPlanetUpdatesTheSelection(String planet) {
        spinner.choosePlanet(planet);
        assertTrue(spinner.showsSelection(planet), planet + " should be selected");
    }
}
