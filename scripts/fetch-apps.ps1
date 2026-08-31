# Downloads the two APKs the suite drives into .\apps
$ErrorActionPreference = "Stop"
Set-Location (Join-Path $PSScriptRoot "..")
New-Item -ItemType Directory -Force -Path apps | Out-Null

$apiDemos  = "https://github.com/appium/android-apidemos/releases/latest/download/ApiDemos-debug.apk"
$wikipedia = "https://github.com/wikimedia/apps-android-wikipedia/releases/latest/download/app-alpha-universal-release.apk"

Write-Host "Downloading ApiDemos-debug.apk ..."
Invoke-WebRequest -Uri $apiDemos -OutFile "apps\ApiDemos-debug.apk"

Write-Host "Downloading wikipedia.apk ..."
try {
    Invoke-WebRequest -Uri $wikipedia -OutFile "apps\wikipedia.apk"
} catch {
    Write-Warning "Wikipedia alpha APK URL changed - download the latest release APK from"
    Write-Warning "  https://github.com/wikimedia/apps-android-wikipedia/releases"
    Write-Warning "and save it as apps\wikipedia.apk"
}

Get-ChildItem apps
