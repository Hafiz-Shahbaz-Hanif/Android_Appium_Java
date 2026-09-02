package com.hafiz.mobile.pages.apidemos;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.pagefactory.AndroidFindBy;

import com.hafiz.mobile.pages.BasePage;

/** ApiDemos &rarr; Views &rarr; Seek Bar. */
public class SeekBarPage extends BasePage {

    @AndroidFindBy(id = "io.appium.android.apis:id/seekBar")
    private WebElement seekBar;

    @AndroidFindBy(id = "io.appium.android.apis:id/progress")
    private WebElement progressLabel;

    public SeekBarPage assertLoaded() {
        visible(seekBar);
        return this;
    }

    /** Current bar position as a 0-100 percentage read from its accessibility text. */
    public int progressPercent() {
        String text = attr(seekBar, "text");           // e.g. "50"
        if (text.isEmpty()) {
            text = attr(progressLabel, "text").replaceAll("\\D+", "");   // "Progress: 50%"
        }
        return text.isEmpty() ? -1 : Integer.parseInt(text);
    }

    public SeekBarPage nudgeRight(int steps) {
        clickable(seekBar).click();
        pressDpad(AndroidKey.DPAD_RIGHT, steps);
        return this;
    }

    public SeekBarPage nudgeLeft(int steps) {
        clickable(seekBar).click();
        pressDpad(AndroidKey.DPAD_LEFT, steps);
        return this;
    }

    public String progressLabelText() {
        return textOf(progressLabel);
    }
}
