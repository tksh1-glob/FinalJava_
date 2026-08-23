# Sistema de Gestión Universitaria — Proyecto Final Java Basics

Aplicación de consola para gestionar Profesores, Estudiantes y Clases de una
universidad, construida como ejercicio final del módulo Java Basics.

## Requisitos cubiertos

| Requisito | Dónde |
|---|---|
| Modificadores de acceso | `private`/`protected`/`public` usados en `modelo/` |
| Encapsulamiento | Atributos privados con getters/setters (`Profesor`, `Estudiante`, `Curso`) |
| Herencia | `ProfesorTiempoCompleto` / `ProfesorMedioTiempo` extienden `Profesor` |
| Polimorfismo | `Profesor.calcularSalario()` y `getTipo()` según el subtipo |
| Constructores | Constructores sobrecargados (`Estudiante`, `Curso`) |
| Atributos/métodos estáticos | Contadores en `Profesor`, `Estudiante`, `Curso` |
| Clase principal | `com.universidad.Main` |
| Paquetes y capas | `modelo` (datos), `gestion` (negocio), `io` (consola) |
| Lectura/impresión fuera del modelo | Todo el `Scanner`/`System.out` vive en `io/ConsolaIO.java` |
| Diagrama de diseño | [`docs/DIAGRAMA_DISENO.md`](docs/DIAGRAMA_DISENO.md) |

## Cómo compilar y correr

```bash
mkdir -p out
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out com.universidad.Main
```

## Reglas de salario

- **Tiempo completo**: `salarioBase * (aniosExperiencia * 1.10)`
- **Medio tiempo**: `salarioBase * horasActivasSemana`

## Flujo de Git sugerido (ver historial de commits de este proyecto)

```bash
git init
git branch -M main
git add .gitignore README.md docs/
git commit -m "docs: agregar README, gitignore y diagrama de diseño"
# ... (ver commits siguientes según se agregan los archivos del código)
git remote add origin https://github.com/<tu-usuario>/<tu-repo>.git
git push -u origin main

git checkout -b feature/mejoras
# hacer un cambio pequeño
git commit -m "feat: mejora de impresión de detalle de curso"
git push -u origin feature/mejoras
```

Recuerda enviar el link del repositorio (público) a Silvana y Juan antes del
31 de agosto de 2026, 11:59 pm.
