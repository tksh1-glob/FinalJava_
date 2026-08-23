# Diagrama de Diseño (Diagrama de Clases UML)

> GitHub renderiza los diagramas Mermaid automáticamente al ver este archivo
> en github.com. También puedes pegar el bloque de código en
> https://mermaid.live para exportarlo como PNG/SVG.

```mermaid
classDiagram
    class Profesor {
        <<abstract>>
        -int id
        -String nombre
        #double salarioBase
        -static int contadorProfesores
        +getNombre() String
        +getSalarioBase() double
        +static getContadorProfesores() int
        +calcularSalario()* double
        +getTipo()* String
    }

    class ProfesorTiempoCompleto {
        -double aniosExperiencia
        +calcularSalario() double
        +getTipo() String
    }

    class ProfesorMedioTiempo {
        -double horasActivasSemana
        +calcularSalario() double
        +getTipo() String
    }

    class Estudiante {
        -static int secuenciaId
        -String id
        -String nombre
        -int edad
        +getId() String
        +getNombre() String
        +getEdad() int
    }

    class Curso {
        -static int contadorCursos
        -int id
        -String nombre
        -String salon
        -Profesor profesor
        -List~Estudiante~ estudiantes
        +agregarEstudiante(Estudiante) void
        +tieneEstudiante(String) boolean
        +getProfesor() Profesor
        +getEstudiantes() List~Estudiante~
    }

    class Universidad {
        -String nombreUniversidad
        -List~Profesor~ profesores
        -List~Estudiante~ estudiantes
        -List~Curso~ cursos
        +agregarProfesor(Profesor) void
        +agregarEstudiante(Estudiante) void
        +agregarCurso(Curso) void
        +buscarEstudiantePorId(String) Optional~Estudiante~
        +buscarCursosPorEstudianteId(String) List~Curso~
        +cargarDatosDeEjemplo() void
    }

    class ConsolaIO {
        -Scanner scanner
        -Universidad universidad
        +ejecutar() void
    }

    class Main {
        +main(String[]) void$
    }

    Profesor <|-- ProfesorTiempoCompleto : herencia
    Profesor <|-- ProfesorMedioTiempo : herencia
    Curso "1" --> "1" Profesor : tiene un
    Curso "1" o-- "muchos" Estudiante : inscribe
    Universidad "1" o-- "muchos" Profesor
    Universidad "1" o-- "muchos" Estudiante
    Universidad "1" o-- "muchos" Curso
    ConsolaIO --> Universidad : usa
    Main --> ConsolaIO : crea
    Main --> Universidad : crea
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
