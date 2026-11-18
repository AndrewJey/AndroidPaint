# Compilar y ejecutar AndroidPaint

## Requisitos

- Android Studio actual, con Gradle JDK 17 (JDK 21 también es válido para el wrapper).
- Android SDK 36 y sus herramientas. Usa los wrappers de cada carpeta.
- Dispositivo o emulador Android 6 o posterior, API 23 como mínimo. El destino es Android 16, API 36.

El mínimo pasa de API 19 a API 23 para usar el entorno AndroidX actual. Los originales se conservan en el historial para revisar el soporte antiguo. No se cambiaron los identificadores de las aplicaciones.

## Un comando desde esta carpeta

```powershell
.uild-local.ps1 -Action Check
.uild-local.ps1 -Project PINTA2 -Action Build
.uild-local.ps1 -Project paintSO2 -Action Test
```

El script busca un JDK 17/21 instalado, permite `-JavaHome` y `-AndroidSdk`, configura las variables solo durante su ejecución y las restaura al terminar. Necesita acceso a Internet la primera vez para resolver las dependencias. No modifica las variables globales de Windows.

## Android Studio

Abre una de estas carpetas como proyecto Gradle:

- `PINTA2-master/PINTA2-master`
- `paintSO2-master/paintSO2-master`

Selecciona JDK 17 para Gradle y tu SDK Android en las preferencias del IDE. El archivo `local.properties` es local y está ignorado. También puedes ejecutar en cualquiera de esas raíces:

```powershell
.\gradlew.bat assembleDebug testDebugUnitTest lintDebug
```

La APK de cada aplicación se genera en `app/build/outputs/apk/debug/app-debug.apk`. Desde Android Studio usa Run, o instala en un emulador de prueba con `adb -s <serial> install -r app/build/outputs/apk/debug/app-debug.apk`.

## Uso y almacenamiento

Dibuja sobre el lienzo, elige colores y tamaños, usa el borrador o crea un dibujo nuevo mediante su confirmación. Guardar muestra una confirmación; cancelar no escribe una imagen. El PNG se guarda en `Pictures/PINTA2` o `Pictures/paintSO2` y aparece en la galería.

En Android 10 y posteriores, guardar imágenes propias usa MediaStore sin pedir acceso general a fotografías. En Android 6 a 9 se solicita el permiso de escritura cuando se confirma guardar. El lienzo conserva sus trazos al cambiar de tamaño o girar la actividad. No hay un sistema de proyectos persistentes, autoguardado después de cerrar el proceso ni funciones de edición avanzadas.

## Comprobaciones

Cada aplicación contiene pruebas gráficas para color, borrado y reanudación, limpieza, redimensionado, pulsaciones y selección de color después del borrador. Se compilan las APK y se ejecuta lint sin desactivar sus errores. La revisión también comprueba arranque, interacción y PNG en un emulador Android 16; los resultados detallados se entregan en el informe de la revisión.

Los directorios build, cachés, configuraciones locales y estado de los editores se excluyen de Git. Los archivos generados incluidos en las importaciones antiguas permanecen accesibles en el historial, pero no forman parte del código mantenido.

Referencias de Android: [almacenamiento de imágenes con MediaStore](https://developer.android.com/training/data-storage/shared/media) y [áreas seguras para interfaces de borde a borde](https://developer.android.com/develop/ui/views/layout/edge-to-edge).
