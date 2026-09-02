package com.hafiz.mobile.tests.apidemos;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hafiz.mobile.base.BaseTest;
import com.hafiz.mobile.driver.AppUnderTest;
import com.hafiz.mobile.pages.Navigator;
import com.hafiz.mobile.pages.apidemos.SeekBarPage;

/** Views &rarr; Seek Bar. */
public class SeekBarTest extends BaseTest {

    @Override
    protected AppUnderTest app() {
        return AppUnderTest.API_DEMOS;
    }

    private SeekBarPage seekBar;

    @BeforeMethod(alwaysRun = true)
    public void openSeekBar() {
        new Navigator().assertAtHome().go("Views", "Seek Bar");
        seekBar = new SeekBarPage().assertLoaded();
    }

    @Test(groups = {"smoke", "apidemos", "widgets"})
    public void draggingRightIncreasesProgress() {
        int before = seekBar.progressPercent();
        seekBar.nudgeRight(5);
        assertTrue(seekBar.progressPercent() > before,
                "progress rose from " + before + " to " + seekBar.progressPercent());
    }

    @Test(groups = {"apidemos", "widgets"})
    public void draggingLeftDecreasesProgress() {
        seekBar.nudgeRight(10);
        int high = seekBar.progressPercent();
        seekBar.nudgeLeft(4);
        assertTrue(seekBar.progressPercent() < high);
    }

    @Test(groups = {"apidemos", "widgets"})
    public void progressLabelReflectsTheBar() {
        seekBar.nudgeRight(3);
        assertTrue(seekBar.progressLabelText().toLowerCase().contains("progress"));
    }
}
