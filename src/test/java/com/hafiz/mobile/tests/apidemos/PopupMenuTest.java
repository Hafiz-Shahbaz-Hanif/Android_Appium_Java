package com.hafiz.mobile.tests.apidemos;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.mobile.base.BaseTest;
import com.hafiz.mobile.driver.AppUnderTest;
import com.hafiz.mobile.pages.Navigator;
import com.hafiz.mobile.pages.apidemos.PopupMenuPage;

/** Views &rarr; Popup Menu. */
public class PopupMenuTest extends BaseTest {

    @Override
    protected AppUnderTest app() {
        return AppUnderTest.API_DEMOS;
    }

    private PopupMenuPage popup;

    @BeforeMethod(alwaysRun = true)
    public void openScreen() {
        new Navigator().assertAtHome().go("Views", "Popup Menu");
        popup = new PopupMenuPage().assertLoaded();
    }

    @Test(groups = {"smoke", "apidemos", "widgets"})
    public void popupListsItsItems() {
        popup.openMenu();
        assertTrue(popup.menuShows("Search"), "the popup contains a Search item");
    }

    @DataProvider(name = "items")
    public Object[][] items() {
        return new Object[][] {{"Search"}, {"Add"}, {"Edit"}, {"Share"}};
    }

    @Test(groups = {"apidemos", "widgets"}, dataProvider = "items")
    public void choosingAnItemRaisesItsToast(String item) {
        popup.openMenu().choose(item);
        assertTrue(popup.toastShown(item),
                "choosing '" + item + "' should raise a matching toast");
    }
}
