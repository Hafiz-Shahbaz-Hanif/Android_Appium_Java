package com.hafiz.mobile.pages.apidemos;

import com.hafiz.mobile.pages.BasePage;

/** ApiDemos &rarr; Views &rarr; Expandable Lists &rarr; 1. Custom Adapter. */
public class ExpandableListPage extends BasePage {

    public ExpandableListPage assertLoaded() {
        wait.until(d -> existsByText("People Names"));
        return this;
    }

    public ExpandableListPage expandGroup(String group) {
        tapByText(group);
        return this;
    }

    public ExpandableListPage collapseGroup(String group) {
        tapByText(group);
        return this;
    }

    public boolean showsChild(String child) {
        return wait.until(d -> existsByText(child));
    }

    public boolean groupIsPresent(String group) {
        return existsByText(group);
    }
}
