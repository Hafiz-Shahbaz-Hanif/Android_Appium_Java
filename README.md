# Android Appium + Java + TestNG — Mobile Automation Framework

[![CI](https://github.com/Hafiz-Shahbaz-Hanif/Android_Appium_Java/actions/workflows/ci.yml/badge.svg)](https://github.com/Hafiz-Shahbaz-Hanif/Android_Appium_Java/actions/workflows/ci.yml)
![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?logo=openjdk&logoColor=white)
![Appium](https://img.shields.io/badge/Appium-2.x-662D91?logo=appium&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-7.x-DE322F)
![License](https://img.shields.io/badge/license-MIT-blue)

A native **Android** UI automation framework built with **Appium 2**, the **Java client** and
**TestNG**, on the **Screen Object Model** with `@AndroidFindBy` elements, a self-managed
Appium server and **ExtentReports** reporting.

> This is a demonstration framework built against freely distributed apps. It contains no
> proprietary code.

| | |
|---|---|
| **Apps under test** | [ApiDemos](https://github.com/appium/android-apidemos) (Appium's sample app) · [Wikipedia Android](https://github.com/wikimedia/apps-android-wikipedia) (open source) |
| **Automation engine** | UiAutomator2 |
| **Design** | Screen Object Model + PageFactory (`AppiumFieldDecorator`, `@AndroidFindBy`) |
| **Server** | `AppiumDriverLocalService` — started/stopped by the suite |
| **Reporting** | ExtentReports (Spark) + TestNG surefire + failure screenshots |
| **Build** | Maven (with wrapper) |

---

## Highlights

- **Self-managed Appium server.** `AppiumServer` starts a local Appium 2 instance for the
  run via `AppiumServiceBuilder`; set `appium.server.url` to point at a Grid or cloud
  provider instead.
- **Screen Object Model.** Every screen declares `@AndroidFindBy` fields; tests call
  intent-revealing methods and `UiScrollable` navigation is encapsulated in the page.
- **Two real apps, 150+ test cases.** ApiDemos for native widgets, dialogs and
  data-driven scrollable-list navigation across the whole screen tree; the production
  Wikipedia app for search → article → save, find-in-page and bottom-nav journeys.
- **AI-assisted workflow.** [`CLAUDE.md`](CLAUDE.md) plus the subagents and skills in
  [`.claude/`](.claude) drive screen-object drafting, failure triage and diff review
  against the framework conventions.
- **App abstraction.** `AppUnderTest` maps each app to its APK, package and activity; the
  driver installs the APK from `apps/` or falls back to launching an installed package.
- **Reporting + triage.** A TestNG `ITestListener` builds an ExtentReports node per test
  and captures a screenshot on failure.
- **CI in two tiers.** A fast `build` job proves the framework compiles on every push; a
  full `emulator` job runs the suite on an Android emulator
  (`reactivecircus/android-emulator-runner`) on `main` / on demand.

## Prerequisites (local run)

| Tool | Notes |
|---|---|
| JDK 17+ | `java -version` |
| Node.js 18+ and Appium 2 | `npm i -g appium@2` then `appium driver install uiautomator2` |
| Android SDK + platform-tools | `ANDROID_HOME` set; `adb` on `PATH` |
| An emulator or a real device | e.g. `Pixel 5 API 33`, booted before the run |

```bash
# 1. download the APKs
bash scripts/fetch-apps.sh          # or: pwsh scripts/fetch-apps.ps1

# 2. start an emulator (or plug in a device), then
./mvnw test                          # full suite (testng.xml)
./mvnw test -Psmoke                   # @smoke group only
./mvnw test -Papidemos               # ApiDemos suite only
./mvnw test -Pwikipedia              # Wikipedia suite only
./mvnw -q test-compile               # what a machine without an SDK can do
./mvnw test -Dappium.server.url=http://127.0.0.1:4723
```

## Test coverage

| Area | Classes | Cases |
|---|---|---|
| ApiDemos — screen-tree navigation | `TopLevelMenuTest`, `LeafScreenNavigationTest` | 80 |
| ApiDemos — widgets & dialogs | `ControlsWidgetTest`, `SeekBarTest`, `ChronometerTest`, `SpinnerTest`, `RadioGroupTest`, `ExpandableListsTest`, `PopupMenuTest`, `DateWidgetsTest`, `AlertDialogsTest`, `ApiDemosTest` | 56 |
| Wikipedia — search, article, navigation | `WikipediaSearchTest`, `WikipediaArticleTest`, `WikipediaNavigationTest`, `WikipediaTest` | 16 |
| **Total** | | **150+** |

Data-driven cases use TestNG `@DataProvider`; navigation is by visible text + `UiScrollable`,
never by index or coordinates.

## Project structure

```
src/test/java/com/hafiz/mobile/
├── config/Configuration.java         # layered configuration
├── driver/
│   ├── AppiumServer.java             # local Appium 2 lifecycle
│   ├── DriverFactory.java            # ThreadLocal AndroidDriver + UiAutomator2Options
│   └── AppUnderTest.java             # apk / package / activity per app
├── pages/
│   ├── BasePage.java                 # AppiumFieldDecorator init + helpers
│   ├── Navigator.java                # ApiDemos text-based screen-tree navigation
│   ├── apidemos/                     # ApiDemos screen objects
│   └── wikipedia/                    # Wikipedia screen objects
├── listeners/                        # ExtentReports + failure screenshots
├── base/BaseTest.java                # server + session lifecycle
└── tests/
    ├── apidemos/                     # navigation + widget + dialog suites
    └── wikipedia/                    # search + article + navigation suites
apps/                                 # APKs (downloaded, not committed)
scripts/fetch-apps.{sh,ps1}
.claude/                              # agents + skills for the AI-assisted workflow
```

## Reports

- **ExtentReports:** `target/extent-report/index.html`
- **TestNG:** `target/surefire-reports/`
- **Failure screenshots:** `target/screenshots/`

---

## Author

**Hafiz Shahbaz Hanif** — Staff SQA Engineer / Test Automation Architect
[LinkedIn](https://www.linkedin.com/in/hafiz-shahbaz-hanif-70407417a) · [GitHub](https://github.com/Hafiz-Shahbaz-Hanif)

Licensed under the [MIT License](LICENSE).
