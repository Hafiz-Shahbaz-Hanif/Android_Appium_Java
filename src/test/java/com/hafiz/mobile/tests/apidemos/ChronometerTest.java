package com.hafiz.mobile.tests.apidemos;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hafiz.mobile.base.BaseTest;
import com.hafiz.mobile.driver.AppUnderTest;
import com.hafiz.mobile.pages.Navigator;
import com.hafiz.mobile.pages.apidemos.ChronometerPage;

/** Views &rarr; Chronometer - start / stop / reset / format. */
public class ChronometerTest extends BaseTest {

    @Override
    protected AppUnderTest app() {
        return AppUnderTest.API_DEMOS;
    }

    private ChronometerPage chronometer;

    @BeforeMethod(alwaysRun = true)
    public void openChronometer() {
        new Navigator().assertAtHome().go("Views", "Chronometer");
        chronometer = new ChronometerPage().assertLoaded();
    }

    @Test(groups = {"smoke", "apidemos", "widgets"})
    public void startMakesTheClockTick() {
        String initial = chronometer.display();
        chronometer.start().waitForTick(initial);
        assertNotNull(chronometer.display());
    }

    @Test(groups = {"apidemos", "widgets"})
    public void stopFreezesTheDisplay() {
        chronometer.start().waitForTick("00:00");
        chronometer.stop();
        String stopped = chronometer.display();
        assertTrue(chronometer.staysAt(stopped, Duration.ofSeconds(2)),
                "a stopped chronometer does not advance");
    }

    @Test(groups = {"apidemos", "widgets"})
    public void resetReturnsToZero() {
        chronometer.start().waitForTick("00:00");
        chronometer.stop().reset();
        assertEquals(chronometer.display(), "00:00");
    }

    @Test(groups = {"apidemos", "widgets"})
    public void customFormatIsApplied() {
        chronometer.applyCustomFormat();
        assertNotNull(chronometer.display());
    }
}
