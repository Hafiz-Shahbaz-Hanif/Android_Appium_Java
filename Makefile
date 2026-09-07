# Convenience targets. The real work is Maven + the wrapper; this just shortens
# the common commands. A full run needs a booted emulator + Appium 2 + UiAutomator2.

MVNW ?= ./mvnw

.PHONY: help apps compile test smoke apidemos wikipedia clean

help:
	@echo "apps      - download the ApiDemos + Wikipedia APKs into apps/"
	@echo "compile   - test-compile only (no device needed)"
	@echo "test      - full TestNG suite (needs an emulator)"
	@echo "smoke     - @smoke group across both apps"
	@echo "apidemos  - ApiDemos suite only"
	@echo "wikipedia - Wikipedia suite only"
	@echo "clean     - mvn clean"

apps:
	bash scripts/fetch-apps.sh

compile:
	$(MVNW) -q test-compile

test:
	$(MVNW) test

smoke:
	$(MVNW) test -Psmoke

apidemos:
	$(MVNW) test -Papidemos

wikipedia:
	$(MVNW) test -Pwikipedia

clean:
	$(MVNW) clean
