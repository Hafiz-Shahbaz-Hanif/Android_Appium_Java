# Changelog

Format follows [Keep a Changelog](https://keepachangelog.com/en/1.1.0/).

## 2026-09-25

### Added
- `ExtentReports` and `tests-155` badges to the README badge row (this repo had
  no reporting badge at all, unlike the Allure-based repos).

## 2026-09-24

### Fixed
- README's Wikipedia row said 18 cases after the Settings-screen addition;
  the actual per-class sum is 19 (total 155, not "150+"). Recounted every
  `@Test` + `@DataProvider` row across every test class.

## 2026-09-23

### Added
- `WikipediaSettingsScreen` + a test: Settings opens from the Explore overflow
  menu and shows a known section. Compile-verified only, like the rest of this
  suite — the CI emulator job is the real gate.

## 2026-09-22

### Added
- Cancel superseded CI runs on the same branch (`concurrency` group in the workflow).

## 2026-09-19

### Added
- Dependabot for `maven` and `github-actions`.

## 2026-09-16

### Added
- `SECURITY.md`.

## 2026-09-13

### Added
- This changelog.

## 2026-09-11

### Added
- `WikipediaSavedScreen` + a test: an article saved from search shows up in the
  reading list.

## 2026-09-08

### Added
- CI: publish TestNG results as a GitHub check via `mikepenz/action-junit-report`.

## 2026-09-07

### Added
- `Makefile` (`apps`, `compile`, `test`, `smoke`, `apidemos`, `wikipedia`).

## 2026-09-06

### Added
- `.github/ISSUE_TEMPLATE/{bug_report,flaky_test}.md`,
  `.github/pull_request_template.md`.

## 2026-09-05

### Added
- `.editorconfig`.

## 2026-09-03

### Added
- `CONTRIBUTING.md` (Screen Object Model rules, the emulator-gate note, PR checklist).

## 2026-09-02

### Added
- `.claude/` AI-assisted workflow: `failure-triager`, `screen-object-author`
  subagents; `new-screen-coverage`, `extent-triage` skills.
- `Navigator` for text + `UiScrollable` screen-tree traversal.
- ~150 TestNG cases: data-driven navigation across the ApiDemos screen tree;
  Controls/SeekBar/Chronometer/Spinner/RadioGroup/ExpandableList/PopupMenu/
  DateWidgets/AlertDialogs widget suites; expanded Wikipedia search, article,
  home and bottom-navigation suites.
- Per-app suite profiles (`-Papidemos` / `-Pwikipedia`); CI splits the emulator
  run into an ApiDemos gate and a non-blocking Wikipedia job.
- README coverage table and the AI-assisted-workflow section.

## 2026-08-31

### Added
- Initial scaffold: Appium 2 + Java + TestNG + Maven wrapper.
- Layered config, a self-managed Appium server, `ThreadLocal<AndroidDriver>`.
- Screen Object Model for ApiDemos and the Wikipedia app; ExtentReports manager
  and a TestNG listener with failure screenshots.
- ApiDemos native-widget and Wikipedia search/article suites; APK download
  scripts.
- CI: compile check on every push, a full emulator run on `main`.
- Framework overview, prerequisites and run instructions.
