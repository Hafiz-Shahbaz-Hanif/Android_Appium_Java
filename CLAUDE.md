# CLAUDE.md — working agreement for AI agents in this repo

This framework is developed with an **agentic-AI workflow**: Claude Code and the
subagents/skills in `.claude/` draft screen objects and tests, triage failures,
and review diffs against the conventions below.

## What this project is

| | |
|---|---|
| Apps under test | [ApiDemos](https://github.com/appium/android-apidemos) (Appium's sample app) · [Wikipedia Android](https://github.com/wikimedia/apps-android-wikipedia) |
| Engine | Appium 2 + `java-client` + UiAutomator2 |
| Runner | TestNG (`src/test/resources/*.xml`) |
| Design | **Screen Object Model** + PageFactory (`AppiumFieldDecorator`, `@AndroidFindBy`) |
| Server | `AppiumDriverLocalService` — started/stopped by the suite |
| Reporting | ExtentReports + surefire + failure screenshots |

> This is a demonstration framework against freely distributed apps. It contains no
> proprietary code. It runs on an emulator; the CI **emulator** job is the
> verification gate (a local machine without an Android SDK only compiles it).

## Golden rules

1. **Screen Object Model, strictly.** Test classes never touch a `By`, a
   `WebElement`, a `WebDriverWait`, or `driver`. They call intent-revealing screen
   methods and assert with TestNG `Assert`.
2. **PageFactory `@AndroidFindBy` for stable controls** — resource-id first
   (`io.appium.android.apis:id/...`), then `accessibility`, then `uiAutomator`.
   `xpath` only as a last resort (it is slow on Android).
3. **Navigate ApiDemos by text with `UiScrollable`** —
   `new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("X"))`.
   Never by list index or coordinates.
4. **Waits live in `BasePage`.** Use `visible`, `clickable`, `tap`, `type`,
   `textOf`, `isDisplayed`, `waitForAll`. No `Thread.sleep`.
5. **A fresh session per `@Test`** (`BaseTest`), one Appium server per run. The
   app to drive is declared by the test class's `app()` override.
6. **Determinism.** Each test starts from the app's launch screen (a new session).
   Tests must pass in any order.
7. **Config via `Configuration`** — `-Dkey` → env var → `config.properties` →
   default. Server URL, device name, platform version, timeouts.
8. **Groups.** Tag every `@Test`: the app (`apidemos` / `wikipedia`), plus
   `smoke` for one happy path per area, `navigation` for the nav-coverage suite.

## Layout

```
src/test/java/com/hafiz/mobile/
├── config/Configuration.java
├── driver/{AppiumServer,DriverFactory,AppUnderTest}.java
├── pages/
│   ├── BasePage.java                  AppiumFieldDecorator init + helpers
│   ├── Navigator.java                 ApiDemos text-based navigation
│   ├── apidemos/                      one class per screen
│   └── wikipedia/
├── listeners/                         ExtentReports + failure screenshots
├── base/BaseTest.java
└── tests/{apidemos,wikipedia}/
apps/                                  APKs (downloaded, not committed)
scripts/fetch-apps.{sh,ps1}
```

## Commands

```bash
# needs a booted emulator/device + Appium 2 + UiAutomator2 driver
bash scripts/fetch-apps.sh
./mvnw test                       # full suite (src/test/resources/testng.xml)
./mvnw test -Psmoke              # @smoke group across both apps
./mvnw test -Papidemos          # ApiDemos suite only
./mvnw test -Pwikipedia         # Wikipedia suite only
./mvnw -q test-compile           # what a machine without an SDK can do
```

Each profile swaps `suite.file` (see `pom.xml`); surefire is driven by a TestNG suite
XML, so filter with a profile, not `-Dgroups`.

## Definition of done

- `./mvnw -q test-compile` clean
- On an emulator: the affected group runs green (the CI emulator job proves this)
- New coverage is a new screen class + a test class using `@DataProvider` where it varies
- No `By` / `WebElement` / `Thread.sleep` in a test class
- `groups` set; the navigation `@DataProvider` still lists real ApiDemos paths
