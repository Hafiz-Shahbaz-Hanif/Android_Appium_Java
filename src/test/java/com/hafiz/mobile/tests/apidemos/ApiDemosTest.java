package com.hafiz.mobile.tests.apidemos;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.hafiz.mobile.base.BaseTest;
import com.hafiz.mobile.driver.AppUnderTest;
import com.hafiz.mobile.pages.apidemos.AlertDialogsPage;
import com.hafiz.mobile.pages.apidemos.ApiDemosHomePage;
import com.hafiz.mobile.pages.apidemos.ControlsPage;
import com.hafiz.mobile.pages.apidemos.GenericListPage;

/** Native widget + gesture coverage against Appium's ApiDemos sample app. */
public class ApiDemosTest extends BaseTest {

    @Override
    protected AppUnderTest app() {
        return AppUnderTest.API_DEMOS;
    }

    @Test(groups = {"smoke", "apidemos"})
    public void okCancelAlertDialogShowsMessageAndCloses() {
        new ApiDemosHomePage().assertLoaded().tapItem("App");
        new GenericListPage().tapItem("Alert Dialogs");

        AlertDialogsPage dialogs = new AlertDialogsPage().openOkCancelDialog();
        assertTrue(dialogs.dialogMessage().length() > 0, "the dialog shows a message");

        dialogs.confirm();
        assertTrue(dialogs.dialogClosed(), "the dialog closes after OK");
    }

    @Test(groups = {"apidemos"})
    public void controlsScreenAcceptsInputAfterScrollNavigation() {
        new ApiDemosHomePage().assertLoaded().tapItem("Views");
        new GenericListPage().tapItem("Controls");
        new GenericListPage().tapItem("1. Light Theme");

        ControlsPage controls = new ControlsPage().assertLoaded();

        boolean before = controls.isCheckboxChecked();
        controls.toggleCheckbox();
        assertNotEquals(controls.isCheckboxChecked(), before, "the checkbox toggles");

        controls.enterText("Hafiz QA");
        assertEquals(controls.enteredText(), "Hafiz QA");
    }
}
