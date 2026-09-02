package com.hafiz.mobile.tests.apidemos;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.mobile.base.BaseTest;
import com.hafiz.mobile.driver.AppUnderTest;
import com.hafiz.mobile.pages.Navigator;

/**
 * Every top-level ApiDemos category opens and shows one of its known children.
 * A fast structural smoke over the whole app menu.
 */
public class TopLevelMenuTest extends BaseTest {

    @Override
    protected AppUnderTest app() {
        return AppUnderTest.API_DEMOS;
    }

    @DataProvider(name = "categories")
    public Object[][] categories() {
        return new Object[][] {
            {"Accessibility", "Accessibility Node Provider"},
            {"Animation", "Bouncing Balls"},
            {"App", "Activity"},
            {"Content", "Assets"},
            {"Graphics", "AlphaBitmap"},
            {"Media", "MediaPlayer"},
            {"NFC", "ForegroundDispatch"},
            {"OS", "Morse Code"},
            {"Preference", "3. Launching preferences"},
            {"Text", "Linkify"},
            {"Views", "Controls"},
        };
    }

    @Test(groups = {"apidemos", "navigation"}, dataProvider = "categories")
    public void categoryOpensAndListsChild(String category, String expectedChild) {
        Navigator nav = new Navigator().assertAtHome().open(category);

        assertEquals(nav.currentPackage(), "io.appium.android.apis");
        assertTrue(nav.showsText(expectedChild),
                category + " should list '" + expectedChild + "'");
    }

    @Test(groups = {"smoke", "apidemos", "navigation"})
    public void backFromLeafReturnsToTheMenu() {
        Navigator nav = new Navigator().assertAtHome().go("Views", "Seek Bar");
        assertTrue(nav.isOnActivity(".view.SeekBar1"));

        nav.back();
        assertTrue(nav.showsText("Seek Bar"), "Back returns to the Views list");
    }
}
