#!/usr/bin/env bash
# Downloads the two APKs the suite drives into ./apps.
# Both are free and openly distributed.
set -euo pipefail

cd "$(dirname "$0")/.."
mkdir -p apps

API_DEMOS_URL="https://github.com/appium/android-apidemos/releases/latest/download/ApiDemos-debug.apk"
WIKIPEDIA_URL="https://github.com/wikimedia/apps-android-wikipedia/releases/latest/download/app-alpha-universal-release.apk"

echo "Downloading ApiDemos-debug.apk ..."
curl -fsSL -o apps/ApiDemos-debug.apk "$API_DEMOS_URL"

echo "Downloading wikipedia.apk ..."
curl -fsSL -o apps/wikipedia.apk "$WIKIPEDIA_URL" || {
  echo "Wikipedia alpha APK URL changed - grab the latest release APK from"
  echo "  https://github.com/wikimedia/apps-android-wikipedia/releases"
  echo "and save it as apps/wikipedia.apk"
}

echo "Done. APKs in ./apps:"
ls -lh apps
