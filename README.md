# AndroidPaint: PINTA2 y paintSO2

Dos aplicaciones de dibujo Java/Android mantenidas en este repositorio personal a partir de los proyectos de **eisnerh**:

| Aplicación | Original | Abrir en Android Studio |
|---|---|---|
| PINTA2 | [eisnerh/PINTA2](https://github.com/eisnerh/PINTA2) | [PINTA2-master/PINTA2-master](PINTA2-master/PINTA2-master) |
| paintSO2 / PaintDroid | [eisnerh/paintSO2](https://github.com/eisnerh/paintSO2) | [paintSO2-master/paintSO2-master](paintSO2-master/paintSO2-master) |

Conservan sus identificadores de aplicación y funcionan como proyectos independientes dentro de esta carpeta. El historial de ambos originales está incorporado; sus fuentes ya coincidían con las últimas versiones publicadas por su autor. Las mejoras de este fork preparan la compilación y las funciones básicas para Android actual.

Incluyen AndroidX, herramientas de compilación actuales, exportación PNG con MediaStore, borrado operativo y conservación del dibujo al redimensionar o girar. PINTA2 guarda después de confirmar el diálogo; cancelar conserva la galería sin cambios.

```powershell
.uild-local.ps1 -Action Check
```

Consulta [RUNNING.md](RUNNING.md) para configurar el JDK/SDK, abrir cada aplicación, instalar sus APK y conocer las pruebas y los límites. [UPSTREAMS.md](UPSTREAMS.md) documenta la procedencia. Se conservan las atribuciones del código original; no se añadió una licencia que el autor no haya declarado.
