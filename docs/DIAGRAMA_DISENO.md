# Diagrama de Diseño (Diagrama de Clases UML)

```mermaid
classDiagram
hide members

Profesor <|-- ProfesorTiempoCompleto
Profesor <|-- ProfesorMedioTiempo
Curso "1" --> "1" Profesor
Curso "1" o-- "*" Estudiante
Universidad "1" o-- "*" Profesor
Universidad "1" o-- "*" Estudiante
Universidad "1" o-- "*" Curso
ConsolaIO --> Universidad
Main --> ConsolaIO
Main --> Universidad
```

## Vista de capas

```
com.universidad
 ├── Main.java              (clase principal / punto de entrada)
 ├── modelo/                (capa de datos: Profesor, ProfesorTiempoCompleto, ProfesorMedioTiempo, Estudiante, Curso)
 ├── gestion/                (capa de negocio: Universidad — toda la lógica/búsquedas)
 └── io/                    (capa de presentación: ConsolaIO — toda la lectura/impresión)
```

- **modelo**: datos puros + reglas de negocio (ej. cálculo de salario). Sin `Scanner`
  ni `System.out` aquí.
- **gestion**: orquesta el modelo (agregar/buscar/listar). Tampoco tiene IO de consola.
- **io**: la única capa que puede leer y escribir en consola.
- **Main**: conecta todo y arranca el programa.
