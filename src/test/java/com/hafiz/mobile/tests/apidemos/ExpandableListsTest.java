package com.hafiz.mobile.tests.apidemos;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.mobile.base.BaseTest;
import com.hafiz.mobile.driver.AppUnderTest;
import com.hafiz.mobile.pages.Navigator;
import com.hafiz.mobile.pages.apidemos.ExpandableListPage;

/** Views &rarr; Expandable Lists &rarr; 1. Custom Adapter. */
public class ExpandableListsTest extends BaseTest {

    @Override
    protected AppUnderTest app() {
        return AppUnderTest.API_DEMOS;
    }

    private ExpandableListPage list;

    @BeforeMethod(alwaysRun = true)
    public void openList() {
        new Navigator().assertAtHome().go("Views", "Expandable Lists", "1. Custom Adapter");
        list = new ExpandableListPage().assertLoaded();
    }

    @DataProvider(name = "groups")
    public Object[][] groups() {
        return new Object[][] {
            {"People Names", "Arnold"},
            {"Dog Names", "Ann"},
            {"Cat Names", "Jesse"},
            {"Fish Names", "Harrison"},
        };
    }

    @Test(groups = {"smoke", "apidemos", "widgets"}, dataProvider = "groups")
    public void expandingAGroupRevealsItsChildren(String group, String firstChild) {
        list.expandGroup(group);
        assertTrue(list.showsChild(firstChild),
                group + " should reveal '" + firstChild + "' when expanded");
    }

    @Test(groups = {"apidemos", "widgets"})
    public void collapsingAGroupHidesItsChildren() {
        list.expandGroup("People Names");
        assertTrue(list.showsChild("Arnold"));

        list.collapseGroup("People Names");
        assertTrue(list.groupIsPresent("Dog Names"), "the list still renders after collapse");
    }
}
