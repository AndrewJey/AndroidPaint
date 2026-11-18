[CmdletBinding()]
param(
    [ValidateSet('All', 'PINTA2', 'paintSO2')][string]$Project = 'All',
    [ValidateSet('Build', 'Test', 'Check')][string]$Action = 'Build',
    [string]$JavaHome,
    [string]$AndroidSdk
)
$ErrorActionPreference = 'Stop'
$taskTools = Join-Path (Split-Path (Split-Path $PSScriptRoot -Parent) -Parent) '_tools'
$taskCandidates = @($JavaHome, $env:JAVA_HOME)
$taskCandidates += @(Get-ChildItem -LiteralPath $taskTools -Directory -Filter 'jdk-17*' -ErrorAction SilentlyContinue | Select-Object -ExpandProperty FullName)
$taskCandidates += @(Get-ChildItem -LiteralPath "$env:ProgramFiles\Microsoft" -Directory -Filter 'jdk-21*' -ErrorAction SilentlyContinue | Select-Object -ExpandProperty FullName)
$taskJava = $null
foreach ($taskCandidate in $taskCandidates) {
    if (-not $taskCandidate -or -not (Test-Path -LiteralPath "$taskCandidate\bin\java.exe")) { continue }
    $taskVersion = (& "$taskCandidate\bin\java.exe" --version | Out-String)
    if ($taskVersion -match '(?:openjdk|java)(?: version)? "?(17|21)\.') { $taskJava = $taskCandidate; break }
}
if (-not $taskJava) { throw 'Indica un JDK 17 o 21 válido mediante -JavaHome.' }
if (-not $AndroidSdk) { $AndroidSdk = $env:ANDROID_HOME }
if (-not $AndroidSdk -or -not (Test-Path -LiteralPath $AndroidSdk)) { $AndroidSdk = 'C:\Android\sdk' }
if (-not (Test-Path -LiteralPath "$AndroidSdk\platforms\android-36\android.jar")) { throw 'Instala Android SDK 36 o indica su raíz mediante -AndroidSdk.' }
$taskProjects = if ($Project -eq 'All') { @('PINTA2', 'paintSO2') } else { @($Project) }
$taskCommands = switch ($Action) { Build { @('assembleDebug') }; Test { @('testDebugUnitTest') }; Check { @('assembleDebug', 'testDebugUnitTest', 'lintDebug') } }
$taskSavedJava = $env:JAVA_HOME
$taskSavedSdk = $env:ANDROID_HOME
$taskSavedPath = $env:PATH
try {
    $env:JAVA_HOME = $taskJava
    $env:ANDROID_HOME = $AndroidSdk
    $env:PATH = "$taskJava\bin;$taskSavedPath"
    foreach ($taskName in $taskProjects) {
        $taskFolder = Join-Path $PSScriptRoot ("{0}-master\{0}-master" -f $taskName)
        Push-Location -LiteralPath $taskFolder
        try {
            & .\gradlew.bat @taskCommands --no-daemon --console=plain --max-workers=2
            if ($LASTEXITCODE -ne 0) { throw "Falló $taskName ($Action), código $LASTEXITCODE." }
        } finally { Pop-Location }
    }
} finally {
    $env:JAVA_HOME = $taskSavedJava
    $env:ANDROID_HOME = $taskSavedSdk
    $env:PATH = $taskSavedPath
}
