---
name: extent-triage
description: Turn an emulator run (surefire + ExtentReports + screenshots + Appium log) into a ranked failure summary for this Appium framework, grouped by root cause.
---

# Triage a run

## 1. Gather

- `target/surefire-reports/*.txt` / `*.xml` — one per class, stack traces + durations
- `target/screenshots/<method>-<ts>.png` — device screen at failure (from `TestListener`)
- `target/extent-report/index.html` — the human view
- Appium server log — locally `target/appium.log` if redirected; on CI the
  `android-emulator-runner` step output

## 2. Failure table

For every `<testcase>` in the surefire XML with a `<failure>` / `<error>`:

| field | source |
|---|---|
| test | `classname` + `name` |
| group | the `@Test(groups=...)` |
| exception | first line of `<failure message=...>` |
| where | the framework stack frame (`com.hafiz.mobile...`) |
| screenshot | `target/screenshots/<name>-*.png` |
| duration | `time` attribute (flag long ones - a slow emulator) |

## 3. Group by cause

Cluster failures with the same exception/message or the same screen class:
selector drift (one `@AndroidFindBy`), missing `UiScrollable` navigation,
session/server issues (`SessionNotCreatedException` across many - emulator or
UiAutomator2), APK missing, Wikipedia UI change.

## 4. Rank

1. Real app bugs (deterministic wrong value)
2. Framework defects hitting many tests (one screen / helper / the session setup)
3. Single flaky test (passed on rerun)
4. Environment (emulator slow / not booted, APK download failed)

## Output

Ranked clusters → tests affected → cause → fix owner, plus the one command to
reproduce the top item (`./mvnw test -Dtest=Class#method`). Hand fixes to
`failure-triager`.
