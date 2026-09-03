# Contributing

Thanks for looking at this project. It is a portfolio framework, but it is built
to real standards and PRs are welcome.

## Ground rules

The conventions in [`CLAUDE.md`](CLAUDE.md) are the contract — read it first. In
short:

- **Screen Object Model, strictly.** Test classes never touch a `By`,
  `WebElement`, `WebDriverWait` or `driver`. They call screen methods and assert
  with TestNG `Assert`.
- **`@AndroidFindBy` for stable controls** — resource-id first, then
  `accessibility`, then `uiAutomator`. `xpath` only as a last resort.
- **Navigate ApiDemos by text with `UiScrollable`**, never by index or
  coordinates.
- **Waits live in `BasePage`.** No `Thread.sleep`.
- **A fresh session per `@Test`**; tests pass in any order.
- **Data-driven** — variations are `@DataProvider` rows, not copied methods.

## Getting set up

```bash
./mvnw -q test-compile            # what a machine without an Android SDK can do
bash scripts/fetch-apps.sh        # download the sample APKs
./mvnw test -Papidemos           # needs a booted emulator + Appium 2 + UiAutomator2
```

The CI **emulator** job is the verification gate; a local machine without an
Android SDK only compiles the framework.

## Adding coverage

1. New screen → a class under `pages/apidemos/` or `pages/wikipedia/` extending
   `BasePage`; controls as `@AndroidFindBy` fields; navigation via `Navigator`.
2. New test → a class under `tests/<area>/` extending `BaseTest`, overriding
   `app()`; use a `@DataProvider` where behaviour varies. Every `@Test` gets
   `groups = {...}`.
3. Register the class in `src/test/resources/testng.xml` (and `smoke.xml` if it
   has a `smoke` test).

## Before you open a PR

```bash
./mvnw -q test-compile
./mvnw test -Papidemos            # on an emulator; note Wikipedia-APK drift
```

- [ ] `test-compile` clean
- [ ] No `By` / `WebElement` / `Thread.sleep` in a test class
- [ ] Navigation is text + `UiScrollable`
- [ ] `groups` set; suite XMLs updated
- [ ] Commit messages are conventional (`feat(pages): …`, `test(apidemos): …`, `docs: …`)

## AI-assisted workflow

`.claude/` contains the subagents and skills used to develop this repo
(`failure-triager`, `screen-object-author`, and the `new-screen-coverage` /
`extent-triage` skills). They encode the same rules as this document.
