

<!-- local-development:start -->
# Desarrollo local

Puedes abrir este repositorio en VS Code, Antigravity o Sublime Text. El compilador y los servicios necesarios dependen del componente indicado abajo.

## Android / Gradle

Importa en Android Studio la carpeta que contiene `settings.gradle`. Hay que revisar juntos el wrapper, Android Gradle Plugin, JDK y SDK antes de sincronizar. Guarda la ruta del SDK en `local.properties`, ignorado por Git, y configura localmente las claves Maps/Firebase. Esta revisión no acredita todavía un APK válido.

## Componentes y validación

Inventario y compilaciones locales del 2026-11-15. Una compilación correcta no acredita todavía interacción, compatibilidad de base de datos, instalador ni despliegue.

| Componente | Tipo / destino | Validación |
| --- | --- | --- |
| [PINTA2-master/PINTA2-master/app/build.gradle](<PINTA2-master/PINTA2-master/app/build.gradle>) | gradle | Sin ejecutar |
| [PINTA2-master/PINTA2-master/build.gradle](<PINTA2-master/PINTA2-master/build.gradle>) | gradle | Sin ejecutar |
| [PINTA2-master/PINTA2-master/settings.gradle](<PINTA2-master/PINTA2-master/settings.gradle>) | gradle | Sin ejecutar |
| [paintSO2-master/paintSO2-master/app/build.gradle](<paintSO2-master/paintSO2-master/app/build.gradle>) | gradle | Sin ejecutar |
| [paintSO2-master/paintSO2-master/build.gradle](<paintSO2-master/paintSO2-master/build.gradle>) | gradle | Sin ejecutar |
| [paintSO2-master/paintSO2-master/settings.gradle](<paintSO2-master/paintSO2-master/settings.gradle>) | gradle | Sin ejecutar |

## Archivos ignorados

`.gitignore` excluye credenciales locales, estado del editor y salidas de las herramientas detectadas. No deja de versionar archivos que ya estaban en Git. Los manifiestos, archivos de bloqueo, recursos fuente y scripts de esquema forman parte del código.
<!-- local-development:end -->
