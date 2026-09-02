package com.hafiz.mobile.tests.apidemos;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hafiz.mobile.base.BaseTest;
import com.hafiz.mobile.driver.AppUnderTest;
import com.hafiz.mobile.pages.Navigator;

/**
 * Data-driven navigation coverage across the ApiDemos screen tree.
 *
 * <p>Each row is a menu path (walked by visible text + {@code UiScrollable}) and the
 * activity that path must land on. The suite proves the framework can reach any
 * screen deterministically from a fresh session, which every widget test relies on.
 */
public class LeafScreenNavigationTest extends BaseTest {

    @Override
    protected AppUnderTest app() {
        return AppUnderTest.API_DEMOS;
    }

    @DataProvider(name = "leafScreens")
    public Object[][] leafScreens() {
        return new Object[][] {
            {new String[] {"Views", "Controls", "1. Light Theme"}, ".view.Controls1"},
            {new String[] {"Views", "Controls", "2. Dark Theme"}, ".view.Controls2"},
            {new String[] {"Views", "Date Widgets", "1. Dialog"}, ".view.DateWidgets1"},
            {new String[] {"Views", "Date Widgets", "2. Inline"}, ".view.DateWidgets2"},
            {new String[] {"Views", "Expandable Lists", "1. Custom Adapter"}, ".view.ExpandableList1"},
            {new String[] {"Views", "Expandable Lists", "2. Cursor (People)"}, ".view.ExpandableList2"},
            {new String[] {"Views", "Expandable Lists", "3. Simple Adapter"}, ".view.ExpandableList3"},
            {new String[] {"Views", "Auto Complete", "1. Screen Top"}, ".view.AutoComplete1"},
            {new String[] {"Views", "Auto Complete", "2. Screen Bottom"}, ".view.AutoComplete2"},
            {new String[] {"Views", "Auto Complete", "3. Scroll"}, ".view.AutoComplete3"},
            {new String[] {"Views", "Auto Complete", "4. List"}, ".view.AutoComplete4"},
            {new String[] {"Views", "Auto Complete", "5. ScrollView"}, ".view.AutoComplete5"},
            {new String[] {"Views", "Buttons", "1. Normal"}, ".view.Buttons1"},
            {new String[] {"Views", "Chronometer"}, ".view.Chronometer1"},
            {new String[] {"Views", "Custom"}, ".view.CustomView1"},
            {new String[] {"Views", "Drag and Drop"}, ".view.DragAndDropDemo"},
            {new String[] {"Views", "Gallery", "1. Photos"}, ".view.Gallery1"},
            {new String[] {"Views", "Gallery", "2. Selection listener"}, ".view.Gallery2"},
            {new String[] {"Views", "Grid", "1. Icon Grid"}, ".view.Grid1"},
            {new String[] {"Views", "Grid", "2. Photos"}, ".view.Grid2"},
            {new String[] {"Views", "Grid", "3. Selection listener"}, ".view.Grid3"},
            {new String[] {"Views", "Hover"}, ".view.Hover"},
            {new String[] {"Views", "ImageButton", "1. Simple"}, ".view.ImageButton1"},
            {new String[] {"Views", "ImageSwitcher", "1. Simple"}, ".view.ImageSwitcher1"},
            {new String[] {"Views", "ImageView", "1. Simple"}, ".view.ImageView1"},
            {new String[] {"Views", "Lists", "1. Array"}, ".view.List1"},
            {new String[] {"Views", "Lists", "2. Cursor (People)"}, ".view.List2"},
            {new String[] {"Views", "Lists", "3. Single choice list"}, ".view.List3"},
            {new String[] {"Views", "Lists", "4. Multiple choice list"}, ".view.List4"},
            {new String[] {"Views", "Lists", "5. Selection mode"}, ".view.List5"},
            {new String[] {"Views", "Popup Menu"}, ".view.PopupMenu1"},
            {new String[] {"Views", "Progress Bar", "1. Incremental"}, ".view.ProgressBar1"},
            {new String[] {"Views", "Progress Bar", "2. Smooth"}, ".view.ProgressBar2"},
            {new String[] {"Views", "Progress Bar", "3. Dialogs"}, ".view.ProgressBar3"},
            {new String[] {"Views", "Progress Bar", "4. In Title Bar"}, ".view.ProgressBar4"},
            {new String[] {"Views", "Radio Group"}, ".view.RadioGroup1"},
            {new String[] {"Views", "Rating Bar"}, ".view.RatingBar1"},
            {new String[] {"Views", "Seek Bar"}, ".view.SeekBar1"},
            {new String[] {"Views", "Spinner"}, ".view.Spinner1"},
            {new String[] {"Views", "Splitting Touches"}, ".view.SplitTouchView"},
            {new String[] {"Views", "Switches"}, ".view.Switches"},
            {new String[] {"Views", "Tabs", "1. Content By Id"}, ".view.Tabs1"},
            {new String[] {"Views", "Tabs", "2. Content By Factory"}, ".view.Tabs2"},
            {new String[] {"Views", "Tabs", "3. Content By Intent"}, ".view.Tabs3"},
            {new String[] {"Views", "Tabs", "5. Scrollable"}, ".view.Tabs5"},
            {new String[] {"Views", "TextSwitcher"}, ".view.TextSwitcher1"},
            {new String[] {"Views", "Visibility", "1. View"}, ".view.Visibility1"},
            {new String[] {"Views", "WebView"}, ".view.WebView1"},
            {new String[] {"App", "Alert Dialogs"}, ".app.AlertDialogSamples"},
            {new String[] {"App", "Dialog"}, ".app.DialogActivity"},
            {new String[] {"App", "Activity", "Custom Title"}, ".app.CustomTitle"},
            {new String[] {"App", "Activity", "Hello World"}, ".app.HelloWorld"},
            {new String[] {"App", "Activity", "Save & Restore State"}, ".app.SaveRestoreState"},
            {new String[] {"App", "Menu", "Inflate from XML"}, ".app.MenuInflateFromXml"},
            {new String[] {"App", "Search", "Invoke Search"}, ".app.SearchInvoke"},
            {new String[] {"OS", "Morse Code"}, ".os.MorseCode"},
            {new String[] {"OS", "Sensors"}, ".os.Sensors"},
            {new String[] {"OS", "SMS Messaging"}, ".os.SmsMessagingDemo"},
            {new String[] {"OS", "Rotation Vector"}, ".os.RotationVectorDemo"},
            {new String[] {"Animation", "Bouncing Balls"}, ".animation.BouncingBalls"},
            {new String[] {"Animation", "Cloning"}, ".animation.AnimationCloning"},
            {new String[] {"Animation", "Loading"}, ".animation.AnimationLoading"},
            {new String[] {"Animation", "Multiple Properties"}, ".animation.MultiPropertyAnimation"},
            {new String[] {"Animation", "Seeking"}, ".animation.AnimationSeeking"},
            {new String[] {"Content", "Assets", "Read Asset"}, ".content.ReadAsset"},
            {new String[] {"Preference", "1. Preferences from XML"}, ".preference.PreferencesFromXml"},
            {new String[] {"Preference", "3. Launching preferences"}, ".preference.PreferencesFromCode"},
            {new String[] {"Preference", "5. Default values"}, ".preference.DefaultValues"},
        };
    }

    @Test(groups = {"apidemos", "navigation"}, dataProvider = "leafScreens")
    public void reachesScreen(String[] path, String expectedActivity) {
        Navigator nav = new Navigator().assertAtHome().go(path);

        assertEquals(nav.currentPackage(), "io.appium.android.apis", "stayed inside ApiDemos");
        assertTrue(nav.currentActivity().contains(trimLeadingDot(expectedActivity)),
                "expected to land on " + expectedActivity + " but was on " + nav.currentActivity());
    }

    private static String trimLeadingDot(String activity) {
        return activity.startsWith(".") ? activity.substring(1) : activity;
    }
}
