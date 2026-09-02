package com.hafiz.mobile.pages.apidemos;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;

import com.hafiz.mobile.pages.BasePage;

/** ApiDemos &rarr; Views &rarr; Spinner. */
public class SpinnerPage extends BasePage {

    @AndroidFindBy(id = "io.appium.android.apis:id/spinner1")
    private WebElement planetSpinner;

    @AndroidFindBy(id = "android:id/text1")
    private WebElement selectedValue;

    public SpinnerPage assertLoaded() {
        visible(planetSpinner);
        return this;
    }

    public SpinnerPage choosePlanet(String planet) {
        tap(planetSpinner);
        tapByText(planet);
        return this;
    }

    public boolean showsSelection(String planet) {
        return wait.until(d -> existsByText(planet));
    }
}
