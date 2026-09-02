package com.hafiz.mobile.tests.apidemos;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.mobile.base.BaseTest;
import com.hafiz.mobile.driver.AppUnderTest;
import com.hafiz.mobile.pages.Navigator;
import com.hafiz.mobile.pages.apidemos.AlertDialogsPage;

/** App &rarr; Alert Dialogs ({@code AlertDialogSamples}). */
public class AlertDialogsTest extends BaseTest {

    @Override
    protected AppUnderTest app() {
        return AppUnderTest.API_DEMOS;
    }

    private AlertDialogsPage dialogs;

    @BeforeMethod(alwaysRun = true)
    public void openScreen() {
        new Navigator().assertAtHome().go("App", "Alert Dialogs");
        dialogs = new AlertDialogsPage().assertLoaded();
    }

    @Test(groups = {"smoke", "apidemos", "dialogs"})
    public void okCancelDialogShowsAMessageAndClosesOnOk() {
        dialogs.openOkCancelDialog();
        assertTrue(dialogs.dialogMessage().length() > 0);

        dialogs.confirm();
        assertTrue(dialogs.dialogClosed());
    }

    @Test(groups = {"apidemos", "dialogs"})
    public void cancelDismissesTheDialog() {
        dialogs.openOkCancelDialog();
        assertTrue(dialogs.dialogOpen());

        dialogs.cancel();
        assertTrue(dialogs.dialogClosed());
    }

    @DataProvider(name = "listItems")
    public Object[][] listItems() {
        return new Object[][] {{"Command one"}, {"Command two"}, {"Command three"}, {"Command four"}};
    }

    @Test(groups = {"apidemos", "dialogs"}, dataProvider = "listItems")
    public void listDialogOffersEachCommand(String item) {
        dialogs.openListDialog();
        assertTrue(dialogs.showsItem(item), "the list dialog offers '" + item + "'");
        dialogs.chooseItem(item);
        assertTrue(dialogs.dialogClosed(), "choosing an item closes the list dialog");
    }

    @Test(groups = {"apidemos", "dialogs"})
    public void singleChoiceDialogKeepsAColourSelected() {
        dialogs.openSingleChoiceDialog();
        assertTrue(dialogs.showsItem("Blue"));
        dialogs.chooseItem("Green");
        dialogs.confirm();
        assertTrue(dialogs.dialogClosed());
    }

    @Test(groups = {"apidemos", "dialogs"})
    public void textEntryDialogAcceptsInput() {
        dialogs.openTextEntryDialog();
        assertTrue(dialogs.dialogOpen());
        dialogs.confirm();
        assertFalse(dialogs.dialogOpen());
    }
}
