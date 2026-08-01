# ========================================
# Sudoku Analyzer - Setup Compilazione
# ========================================

Write-Host "🧩 Sudoku Analyzer - Setup Compilazione" -ForegroundColor Cyan
Write-Host ""

# Check Java
Write-Host "📋 Controllo Java..." -ForegroundColor Yellow
$javaVersion = java -version 2>&1
if ($LASTEXITCODE -eq 0) {
    Write-Host "✅ Java trovato!" -ForegroundColor Green
    Write-Host $javaVersion[0] -ForegroundColor Gray
} else {
    Write-Host "❌ Java NON trovato! Installa Java JDK 8+" -ForegroundColor Red
    exit 1
}

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "OPZIONI DI COMPILAZIONE:" -ForegroundColor White
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "1. 🎨 Android Studio (CONSIGLIATO)" -ForegroundColor Green
Write-Host "   - Più semplice e completo"
Write-Host "   - Include emulatore per testare"
Write-Host "   - Download: https://developer.android.com/studio"
Write-Host "   - Dimensione: ~1 GB"
Write-Host "   - Tempo: 30 minuti"
Write-Host ""
Write-Host "2. ⚡ Android SDK Command Line Tools" -ForegroundColor Yellow
Write-Host "   - Solo strumenti essenziali"
Write-Host "   - Più veloce ma richiede terminale"
Write-Host "   - Download: https://developer.android.com/studio#command-line-tools-only"
Write-Host "   - Dimensione: ~300 MB"
Write-Host "   - Tempo: 15 minuti"
Write-Host ""
Write-Host "3. 🌐 GitHub Actions (Compilazione Cloud)" -ForegroundColor Magenta
Write-Host "   - Gratis se hai account GitHub"
Write-Host "   - Compila sui server di GitHub"
Write-Host "   - Nessuna installazione locale"
Write-Host "   - Tempo: 5-10 minuti"
Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

$choice = Read-Host "Scegli un'opzione (1/2/3)"

switch ($choice) {
    "1" {
        Write-Host ""
        Write-Host "📦 Android Studio Setup:" -ForegroundColor Green
        Write-Host "1. Vai su: https://developer.android.com/studio"
        Write-Host "2. Scarica Android Studio"
        Write-Host "3. Installa (accetta tutte le impostazioni predefinite)"
        Write-Host "4. Apri Android Studio"
        Write-Host "5. File → Open → Seleziona: C:\Users\matti\SudokuAnalyzer"
        Write-Host "6. Attendi sync Gradle (prima volta: 5-10 min)"
        Write-Host "7. Build → Build Bundle(s) / APK(s) → Build APK(s)"
        Write-Host "8. APK pronto in: app\build\outputs\apk\debug\app-debug.apk"
        Write-Host ""
        $open = Read-Host "Vuoi aprire il sito di download? (s/n)"
        if ($open -eq "s") {
            Start-Process "https://developer.android.com/studio"
        }
    }
    "2" {
        Write-Host ""
        Write-Host "⚡ Command Line Tools Setup:" -ForegroundColor Yellow
        Write-Host "1. Vai su: https://developer.android.com/studio#command-line-tools-only"
        Write-Host "2. Scarica 'Command line tools only' per Windows"
        Write-Host "3. Estrai in: C:\Android\cmdline-tools"
        Write-Host "4. Apri PowerShell e esegui:"
        Write-Host '   $env:ANDROID_HOME = "C:\Android"' -ForegroundColor Cyan
        Write-Host '   cd C:\Users\matti\SudokuAnalyzer' -ForegroundColor Cyan
        Write-Host '   .\gradlew assembleDebug' -ForegroundColor Cyan
        Write-Host ""
        $open = Read-Host "Vuoi aprire il sito di download? (s/n)"
        if ($open -eq "s") {
            Start-Process "https://developer.android.com/studio#command-line-tools-only"
        }
    }
    "3" {
        Write-Host ""
        Write-Host "🌐 GitHub Actions Setup:" -ForegroundColor Magenta
        Write-Host "Sto creando la configurazione GitHub Actions..."
        Write-Host "Ti preparerò i file necessari!"
    }
    default {
        Write-Host "Opzione non valida!" -ForegroundColor Red
        exit 1
    }
}

Write-Host ""
Write-Host "✨ Setup completato!" -ForegroundColor Green
Write-Host ""
Read-Host "Premi INVIO per chiudere"
