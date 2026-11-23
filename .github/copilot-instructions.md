# Instrucciones para GitHub Copilot — ComposeTest

Este repositorio es una app Android nativa escrita en **Kotlin** usando **Jetpack Compose**,
siguiendo una arquitectura **MVVM**. Sigue estas convenciones al generar o modificar código.

## Stack y configuración

- Lenguaje: Kotlin, build con Gradle Kotlin DSL (`build.gradle.kts`).
- `compileSdk`/`targetSdk` = 37, `minSdk` = 31.
- UI 100% Jetpack Compose (no usar XML layouts ni Views clásicas).
- Navegación con `androidx.navigation:navigation-compose`.
- Gestión de estado: `ViewModel` + `StateFlow` (`MutableStateFlow` interno,
  expuesto como `StateFlow` inmutable vía `asStateFlow()`).
- Dependencias y versiones se gestionan en el catálogo de versiones (`libs.versions.toml`
  / `libs.*` en `app/build.gradle.kts`). Usa `libs.xxx` cuando la librería ya está en el
  catálogo; añade una línea `implementation("grupo:artefacto:version")` directa solo si
  no existe en el catálogo (como ya se hace para MVVM/Navegación/MaterialIcon).

## Estructura de paquetes (`com.nsel.testcompose`)

- `domain.model` — clases de datos puras (`data class`) sin lógica de Android ni de UI.
- `presentation.view.<feature>` — Composables de pantalla, organizados en una carpeta por
  feature (`home`, `login`, `main`, `profile`, `settings`).
- `presentation.viewModel.<feature>` — un `ViewModel` por feature, con su `*State`
  (data class inmutable) y su clase `XxxViewModel : ViewModel()`.
- `presentation.navigation` — rutas y grafo de navegación (`AppRoute.kt`).
- `ui.theme` — `Color.kt`, `Theme.kt`, `Type.kt` (tema Material 3).

Al añadir una nueva feature, respeta este patrón: crea su propio sub-paquete en
`view` y en `viewModel`, no mezcles pantallas de distintas features en el mismo archivo.

## Convenciones de código

- **ViewModel**: estado en una `data class` con valores por defecto, expuesto como
  `StateFlow`. Modifica el estado con `_uiState.update { it.copy(...) }` (o `.value =`
  para casos simples), nunca mutes el estado directamente desde la UI.
- **Lógica derivada**: exponla como propiedades computadas dentro del `State`
  (p. ej. `val isUnameError: Boolean get() = ...`) en lugar de duplicarla en la UI.
- **Composables**: nombres en `PascalCase` que describen la pantalla/elemento
  (`HomeScreen`, `LoginScreen`). Preferir *stateless composables* que reciban el
  estado y los callbacks como parámetros, dejando el `ViewModel` fuera del
  Composable cuando sea posible (facilita testing y previews).
- Mantén los textos de UI en español si ya existen en el archivo (como en los
  mensajes de `MainActivityViewModel`), para ser consistentes con el resto del código.
- No introduzcas nuevas dependencias de arquitectura (DI frameworks, Room, Retrofit, etc.)
  sin que el usuario lo pida explícitamente; el proyecto es intencionalmente simple.

## Validación de cambios

Antes de dar por terminada una tarea que modifique código Kotlin/Gradle, compila el
proyecto con:

```powershell
./gradlew.bat assembleDebug
```

Para pruebas unitarias o instrumentadas (si existen o se agregan):

```powershell
./gradlew.bat test
./gradlew.bat connectedAndroidTest
```

No ejecutes `connectedAndroidTest` a menos que haya un emulador/dispositivo conectado.

## Buenas prácticas generales

- Haz cambios quirúrgicos y acotados al alcance de la petición; no reformatees
  archivos completos ni reorganices paquetes sin que se pida.
- Si agregas una dependencia nueva, añade/edita la entrada correspondiente en
  `gradle/libs.versions.toml` cuando sea posible, en vez de solo hardcodear la
  versión en `build.gradle.kts`.
- Comenta el código solo cuando aclare algo no evidente; evita comentarios obvios.
