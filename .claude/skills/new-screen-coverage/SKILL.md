---
name: new-screen-coverage
description: Add a new Screen Object and TestNG test class for an ApiDemos or Wikipedia screen to this Appium framework, data-driven where the behaviour varies.
---

# Add coverage for a screen

## 1. Screen Object

- `pages/apidemos/<Name>Page.java` (or `pages/wikipedia/`), extends `BasePage`.
- Controls: `private WebElement field;` with `@AndroidFindBy`. Priority:
  `id` → `accessibility` → `uiAutomator`. Avoid `xpath`.
- `open()` navigates from the ApiDemos home via `Navigator` (text + `UiScrollable`)
  and asserts a landmark element.
- Actions return `this` / next screen; queries return plain values.
- Only `BasePage` helpers for interaction. No `Thread.sleep`, no raw `By`.

## 2. Test class

```java
public class <Name>Test extends BaseTest {

    @Override protected AppUnderTest app() { return AppUnderTest.API_DEMOS; }

    @Test(groups = {"smoke", "apidemos"})
    public void happyPath() { ... }

    @DataProvider(name = "cases")
    public Object[][] cases() { return new Object[][] {{...}, {...}}; }

    @Test(groups = {"apidemos"}, dataProvider = "cases")
    public void variation(String input, String expected) { ... }
}
```

- Extends `BaseTest`, overrides `app()`. Fresh session per method.
- `groups`: app + `smoke` for one happy path; `navigation` for nav-coverage.
- Assert with `org.testng.Assert`. Never touch a `WebElement` here.

## 3. Wire and verify

- `testng.xml` is class-listed - add the new class.
- Add it to `smoke.xml` only if it has a `smoke` test.
- ```bash
  ./mvnw -q test-compile
  ./mvnw test -Dtest=<Name>Test        # needs a booted emulator
  ```

## Checklist

- [ ] `@AndroidFindBy` only in the screen class; no `By` / `WebElement` in the test
- [ ] Variations are `@DataProvider` rows, not duplicated methods
- [ ] Navigation is text + `UiScrollable`, never index/coordinates
- [ ] `groups` set; compiles; (on an emulator) the new class runs green
