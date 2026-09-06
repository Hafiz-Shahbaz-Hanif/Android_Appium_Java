<!-- See CONTRIBUTING.md and CLAUDE.md for the full conventions. -->

## What & why

<!-- One or two lines. Link the issue if there is one. -->

## Checklist

- [ ] `./mvnw -q test-compile` clean
- [ ] On an emulator: the affected group runs green (the CI emulator job proves this)
- [ ] New coverage is a new screen class + a test class using `@DataProvider` where it varies
- [ ] No `By` / `WebElement` / `Thread.sleep` in a test class
- [ ] Navigation is text + `UiScrollable`, never index/coordinates
- [ ] `groups` set; suite XMLs updated
- [ ] Formatting matches `.editorconfig`

## Notes for the reviewer

<!-- Anything non-obvious: a selector that drifted, a deliberate deviation, follow-ups. -->
