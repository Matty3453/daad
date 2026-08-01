# Sudoku Analyzer 🧩✨

**L'app Android più SPETTACOLARE per analizzare le tecniche del Sudoku!**

<p align="center">
  <img src="https://img.shields.io/badge/Android-7.0+-green.svg" />
  <img src="https://img.shields.io/badge/Material%20Design-3-blue.svg" />
  <img src="https://img.shields.io/badge/Tecniche-22+-purple.svg" />
</p>

---

## 🎨 Features Spettacolari

### ✨ Animazioni WOW
- **Splash Screen animata** con logo che pulsa
- **Entrance animations** per ogni elemento della UI
- **Bounce, scale, fade, slide** - animazioni fluide ovunque
- **Progress bar animata** durante l'analisi
- **Shake effects** per gli errori
- **Color flash** per feedback visivo
- **Number counters animati** nei risultati

### 🎯 22+ Tecniche Avanzate

#### 🌟 Livello Base (Diff: 1-2)
- 🎯 **Naked Single** - Cella con un solo candidato
- 🔍 **Hidden Single** - Numero con una sola posizione

#### 📚 Livello Intermedio (Diff: 3-5)
- 👥 **Naked Pair/Triple/Quad** - Subset di candidati
- 🕵️ **Hidden Pair/Triple/Quad** - Subset nascosti
- 👉 **Pointing Pair/Triple** - Candidati che puntano
- 📦 **Box/Line Reduction** - Eliminazione tra box e linee

#### 🔥 Livello Avanzato (Diff: 6-8)
- 🐟 **X-Wing** - Pattern a pesce 2x2
- 🐟 **Swordfish** - Pattern a pesce 3x3
- 🐟 **Jellyfish** - Pattern a pesce 4x4
- 🔗 **Y-Wing** - Wing pattern con pivot
- 🔗 **XYZ-Wing** - Wing a 3 candidati
- 🔗 **WXYZ-Wing** - Wing a 4 candidati

#### 💀 Livello Esperto (Diff: 9-10)
- 🔗 **XY-Chain** - Catene di coppie
- 🎨 **Simple Coloring** - Colorazione semplice
- 🎨 **Multi-Coloring** - Colorazione multipla
- ⬜ **Unique Rectangle** - Rettangoli unici
- 💪 **Brute Force** - Backtracking quando necessario

### 🎨 UI/UX da Sogno

- **Material Design 3** - Design moderno e coerente
- **Gradiente di sfondo** viola-blu spettacolare
- **Card elevate** con ombre profonde
- **Bordi arrotondati** ovunque (16-24dp)
- **Colori vivaci** per ogni livello di difficoltà
- **Typography perfetta** con gerarchia chiara
- **Ripple effects** sui bottoni
- **Emoji** per identificare velocemente le tecniche

### 📊 Analisi Dettagliata

- **Riepilogo tecnico** con contatore per ogni tecnica
- **Passo-passo** - Ogni mossa spiegata in dettaglio
- **Difficulty rating** da 1 a 10 per ogni tecnica
- **Valutazione finale** con emoji e colore
- **Statistiche animate** - Conta le tecniche con animazioni
- **Progress indicator** durante l'elaborazione

### 🎪 Interattività

- **Griglia 9x9 interattiva** con celle animate
- **Focus animations** quando selezioni una cella
- **Example puzzle** - Carica un Sudoku di esempio
- **Clear con rotazione** - Le celle ruotano quando le pulisci
- **Error feedback** - Shake e flash rosso per errori
- **Toast animations** per i messaggi

---

## 📱 Screenshot Concettuali

```
┌─────────────────────────────────────┐
│  🧩 Sudoku Analyzer                 │
│  Scopri le tecniche usate           │
├─────────────────────────────────────┤
│  ┌───────────────────────────────┐  │
│  │  📝 Inserisci il Sudoku       │  │
│  │  ┌─┬─┬─┬─┬─┬─┬─┬─┬─┐          │  │
│  │  │5│3│ │ │7│ │ │ │ │          │  │
│  │  ├─┼─┼─┼─┼─┼─┼─┼─┼─┤          │  │
│  │  │ │ │ │ │ │ │ │ │ │  Animato!│  │
│  │  └─┴─┴─┴─┴─┴─┴─┴─┴─┘          │  │
│  └───────────────────────────────┘  │
│                                     │
│  [🗑️ Pulisci]  [💡 Esempio]        │
│                                     │
│  [✨ ANALIZZA TECNICHE ✨]          │
│                                     │
│  ┌───────────────────────────────┐  │
│  │ 📊 Risultati Analisi  [Medio] │  │
│  │ ──────────────────────────    │  │
│  │                               │  │
│  │  [42]  Tecniche  [6] Diff Max│  │
│  │                               │  │
│  │  🎯 Naked Single: 15×         │  │
│  │  🔍 Hidden Single: 12×        │  │
│  │  🐟 X-Wing: 2×                │  │
│  │  ...                          │  │
│  └───────────────────────────────┘  │
└─────────────────────────────────────┘
```

---

## 🚀 Come Generare l'APK

### Opzione 1: Script Automatico ⚡ (RACCOMANDATO)

```powershell
cd C:\Users\matti\SudokuAnalyzer
.\build-apk.bat
```

### Opzione 2: Android Studio 🎨

1. Apri **Android Studio**
2. `File → Open` → Seleziona `C:\Users\matti\SudokuAnalyzer`
3. Attendi il sync di Gradle (prima volta può richiedere alcuni minuti)
4. `Build → Build Bundle(s) / APK(s) → Build APK(s)`
5. APK generato in: `app\build\outputs\apk\debug\app-debug.apk`

### Opzione 3: Gradle da Terminale 💻

```powershell
cd C:\Users\matti\SudokuAnalyzer

# Debug APK
.\gradlew assembleDebug

# Release APK (unsigned)
.\gradlew assembleRelease
```

**Output:** `app\build\outputs\apk\debug\app-debug.apk`

---

## 🎯 Requisiti

- **Java JDK**: 8 o superiore
- **Android SDK**: API 24+ (Android 7.0+)
- **Gradle**: 8.0+ (incluso nel wrapper)
- **Internet**: Solo per il primo download delle dipendenze

### Verifica Requisiti

```powershell
# Verifica Java
java -version

# Dovrebbe mostrare: java version "1.8" o superiore
```

---

## 📦 Installazione APK

### Su Dispositivo Reale

1. Trasferisci `app-debug.apk` sul telefono
2. **Impostazioni → Sicurezza → Installa da fonti sconosciute** (abilita)
3. Apri il file APK
4. Conferma installazione

### Su Emulatore

```powershell
# Con Android Studio aperto e emulatore attivo
adb install app\build\outputs\apk\debug\app-debug.apk
```

---

## 🏗️ Architettura del Progetto

```
SudokuAnalyzer/
├── app/
│   ├── src/main/
│   │   ├── java/com/sudoku/analyzer/
│   │   │   ├── MainActivity.java           ⭐ Activity principale con ANIMAZIONI
│   │   │   ├── SudokuCell.java            📦 Singola cella con candidati
│   │   │   ├── SudokuGrid.java            🎯 Griglia 9x9 completa
│   │   │   ├── Technique.java             🎨 Classe tecnica con emoji e difficoltà
│   │   │   └── TechniqueAnalyzer.java     🧠 Motore di analisi (22+ tecniche!)
│   │   │
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   └── activity_main.xml      ✨ Layout spettacolare Material 3
│   │   │   ├── values/
│   │   │   │   ├── colors.xml             🎨 Palette colori vivaci
│   │   │   │   ├── themes.xml             🎭 Material Design 3
│   │   │   │   ├── strings.xml
│   │   │   │   └── splash_theme.xml       🚀 Tema splash screen
│   │   │   ├── drawable/
│   │   │   │   ├── gradient_background.xml     🌈 Gradiente viola-blu
│   │   │   │   ├── cell_background.xml         📱 Selector celle animate
│   │   │   │   ├── button_analyze_background.xml 🔥 Bottone con gradiente
│   │   │   │   ├── card_background.xml         📦 Card arrotondate
│   │   │   │   ├── splash_background.xml       🚀 Sfondo splash
│   │   │   │   └── splash_icon.xml             🧩 Icona splash animata
│   │   │   └── anim/
│   │   │       ├── scale_in.xml            📈 Animazione scala
│   │   │       ├── slide_up.xml            ⬆️  Animazione slide
│   │   │       ├── slide_out_right.xml     ➡️  Animazione uscita
│   │   │       └── fade_scale_in.xml       ✨ Combo fade+scale
│   │   │
│   │   └── AndroidManifest.xml
│   │
│   └── build.gradle                        ⚙️ Configurazione app
│
├── build.gradle                            ⚙️ Config progetto
├── settings.gradle
├── gradle/wrapper/
├── build-apk.bat                           🚀 Script build Windows
└── README.md                               📚 Questa guida!
```

---

## 🎨 Palette Colori

| Colore | Hex | Uso |
|--------|-----|-----|
| **Purple Primary** | `#6200EE` | Elementi principali |
| **Purple Light** | `#BB86FC` | Accenti |
| **Teal Accent** | `#03DAC6` | Progress bars |
| **Pink Accent** | `#FF4081` | Highlighting |
| **Green Success** | `#4CAF50` | Feedback positivo |
| **Red Error** | `#F44336` | Errori |
| **Gradient Start** | `#667eea` | Sfondo inizio |
| **Gradient End** | `#764ba2` | Sfondo fine |

### Difficoltà Colori

- 🟢 **Facile** (1-2): Verde `#4CAF50`
- 🟡 **Medio** (3-5): Arancione `#FF9800`
- 🔴 **Difficile** (6-7): Rosso `#FF5722`
- 🟣 **Esperto** (8-9): Viola `#9C27B0`
- ⚫ **Estremo** (10): Rosso scuro `#D32F2F`

---

## 💡 Esempi di Utilizzo

### Caso 1: Sudoku Facile
```
Input: Puzzle con molti numeri
Output: 
  - 25× Naked Singles
  - 10× Hidden Singles
  📊 Difficoltà: 🌱 Facile (2/10)
```

### Caso 2: Sudoku Medio
```
Input: Puzzle standard
Output:
  - 15× Naked Singles
  - 12× Hidden Singles
  - 5× Naked Pairs
  - 2× X-Wing
  📊 Difficoltà: 📈 Medio (6/10)
```

### Caso 3: Sudoku Esperto
```
Input: Puzzle molto difficile
Output:
  - 8× Naked Singles
  - 6× Hidden Singles
  - 3× X-Wing
  - 2× Swordfish
  - 1× Y-Wing
  - Brute Force Required
  📊 Difficoltà: 💀 Estremo (10/10)
```

---

## 🐛 Troubleshooting

### ❌ Problema: "SDK not found"
**Soluzione:** Installa Android Studio che include Android SDK

### ❌ Problema: "Gradle sync failed"
**Soluzione:** 
1. Controlla connessione internet
2. Riprova: `.\gradlew clean build`
3. Cancella cache: `.\gradlew --stop` poi ricompila

### ❌ Problema: "Java version incompatible"
**Soluzione:**
```powershell
# Installa JDK 8 o superiore
java -version  # Verifica versione
```

### ❌ Problema: "Build troppo lento"
**Soluzione:**
- Prima build richiede download dipendenze (2-5 min)
- Build successive: 30-60 secondi

### ❌ Problema: "APK non si installa"
**Soluzione:**
1. Abilita "Fonti sconosciute" nelle impostazioni
2. Disinstalla versione precedente se presente
3. Verifica che il dispositivo sia Android 7.0+

---

## 🚀 Prossimi Sviluppi

- [ ] Salvataggio/caricamento puzzle
- [ ] Modalità risoluzione guidata step-by-step
- [ ] Generatore di Sudoku con difficoltà selezionabile
- [ ] Statistiche personali
- [ ] Dark mode
- [ ] Multilingua (EN, IT, ES, FR)
- [ ] Tutorial interattivo per ogni tecnica
- [ ] Condivisione sui social
- [ ] Timer per risolvere

---

## 📄 Licenza

Progetto creato per scopo educativo. Sentiti libero di modificare e migliorare!

---

## 🎉 Credits

**Sviluppato con:**
- ❤️ Passione per le animazioni
- 🎨 Material Design 3
- 🧠 22+ tecniche Sudoku
- ⚡ Tante animazioni WOW!

**Powered by:** Android SDK, Material Components, Java

---

<p align="center">
  Made with ✨ and 🧩
  <br>
  <strong>Sudoku Analyzer - L'app PERFETTA per gli appassionati di Sudoku!</strong>
</p>
