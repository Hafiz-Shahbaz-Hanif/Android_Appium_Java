---
name: Flaky test
about: A test that passes on rerun without a code change
title: "[flaky] "
labels: flaky
---

## Which test

<!-- Class#method (name the Examples row if it is a @DataProvider) -->

## Evidence it is flaky

- [ ] Passed on rerun with no code change
- [ ] Fails only in CI / only on a cold emulator / only in a full run
- Rough failure rate: __ / 10 runs

## Failure detail

<!-- The assertion or Appium exception, and the screen it happened on. -->

## Suspected cause

<!-- selector drift (`@AndroidFindBy`), missing `UiScrollable` navigation,
     slow emulator, session/UiAutomator2 setup, Wikipedia APK change. -->

## Notes

Link the CI emulator run and the screenshot. The `failure-triager` agent in
`.claude/` is built for this.
