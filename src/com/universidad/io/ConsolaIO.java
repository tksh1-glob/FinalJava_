package com.universidad.io;

import com.universidad.gestion.Universidad;
import com.universidad.modelo.Curso;
import com.universidad.modelo.Estudiante;
import com.universidad.modelo.Profesor;
import com.universidad.util.Validador;

import java.util.List;
import java.util.Scanner;

/**
 * Capa de entrada/salida (presentación) del sistema.
 *
 * Todo el código que usa Scanner (leer del teclado) y System.out
 * (imprimir en pantalla) vive en esta clase, y NO dentro de las clases del
 * modelo (Profesor, Estudiante, Curso), tal como pide el enunciado del
 * ejercicio.
 *
 * Además de mostrar el menú, esta clase se encarga de volver a pedir un
 * dato cuando el usuario escribe algo inválido, en vez de dejar que el
 * programa se caiga con una excepción sin controlar.
 */
public class ConsolaIO {

    private final Scanner scanner;
    private final Universidad universidad;

    public ConsolaIO(Universidad universidad) {
        this.universidad = universidad;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Punto de entrada del menú: se queda en un ciclo mostrando las
     * opciones hasta que el usuario elige salir.
     */
    public void ejecutar() {
        boolean activo = true;
        while (activo) {
            imprimirMenu();
            String opcion = scanner.nextLine().trim();

            if (opcion.equals("a")) {
                imprimirTodosLosProfesores();
            } else if (opcion.equals("b")) {
                imprimirMenuDeCursos();
            } else if (opcion.equals("c")) {
                crearEstudianteYAgregarloACurso();
            } else if (opcion.equals("d")) {
                crearCursoConProfesorYEstudiantes();
            } else if (opcion.equals("e")) {
                listarCursosDeEstudiante();
            } else if (opcion.equals("f")) {
                activo = false;
                System.out.println("¡Hasta luego!");
            } else {
                System.out.println("Opción inválida, intenta de nuevo.\n");
            }
        }
        scanner.close();
    }

    private void imprimirMenu() {
        System.out.println("\n===== " + universidad.getNombreUniversidad() + " - Menú Principal =====");
        System.out.println("a. Imprimir todos los profesores");
        System.out.println("b. Imprimir todos los cursos (elegir uno para ver detalle)");
        System.out.println("c. Crear un nuevo estudiante y agregarlo a un curso existente");
        System.out.println("d. Crear un nuevo curso con un profesor y estudiantes existentes");
        System.out.println("e. Listar los cursos de un estudiante (buscar por id)");
        System.out.println("f. Salir");
        System.out.print("Elige una opción: ");
    }

    // ---------------------------------------------------------------
    // a) Imprimir todos los profesores
    // ---------------------------------------------------------------
    private void imprimirTodosLosProfesores() {
        System.out.println("\n--- Profesores (" + Profesor.getContadorProfesores() + " en total) ---");
        for (Profesor p : universidad.getProfesores()) {
            // Polimorfismo: toString() y calcularSalario() se resuelven
            // según el tipo real de cada profesor (tiempo completo o medio tiempo).
            System.out.println(p);
        }
    }

    // ---------------------------------------------------------------
    // b) Imprimir todos los cursos + submenú de detalle
    // ---------------------------------------------------------------
    private void imprimirMenuDeCursos() {
        List<Curso> cursos = universidad.getCursos();
        if (cursos.isEmpty()) {
            System.out.println("Todavía no hay cursos registrados.");
            return;
        }
        System.out.println("\n--- Cursos ---");
        for (int i = 0; i < cursos.size(); i++) {
            System.out.println((i + 1) + ". " + cursos.get(i));
        }
        System.out.print("Ingresa el número del curso para ver el detalle (o 0 para volver): ");
        int eleccion = leerEntero();
        if (eleccion <= 0) {
            return;
        }

        Curso curso = universidad.buscarCursoPorIndice(eleccion - 1);
        if (curso == null) {
            System.out.println("Número de curso inválido.");
        } else {
            imprimirDetalleCurso(curso);
        }
    }

    private void imprimirDetalleCurso(Curso curso) {
        System.out.println("\n--- Detalle del Curso ---");
        System.out.println("Nombre: " + curso.getNombre());
        System.out.println("Salón: " + curso.getSalon());
        System.out.println("Profesor: " + curso.getProfesor());
        System.out.println("Estudiantes:");
        if (curso.getEstudiantes().isEmpty()) {
            System.out.println("  (sin estudiantes inscritos)");
        } else {
            for (Estudiante e : curso.getEstudiantes()) {
                System.out.println("  " + e);
            }
        }
    }

    // ---------------------------------------------------------------
    // c) Crear un nuevo estudiante y agregarlo a un curso existente
    // ---------------------------------------------------------------
    private void crearEstudianteYAgregarloACurso() {
        List<Curso> cursos = universidad.getCursos();
        if (cursos.isEmpty()) {
            System.out.println("No hay cursos disponibles. Crea un curso primero.");
            return;
        }

        // Se construye el Estudiante dentro de un ciclo: si el constructor
        // lanza IllegalArgumentException (nombre con formato inválido o
        // edad fuera de rango), se atrapa el error, se avisa al usuario
        // y se le vuelve a pedir el dato, en vez de romper el programa.
        Estudiante estudiante = null;
        while (estudiante == null) {
            String nombre = leerNombrePersona("Nombre del estudiante: ");
            System.out.print("Edad del estudiante: ");
            int edad = leerEntero();
            try {
                estudiante = new Estudiante(nombre, edad);
            } catch (IllegalArgumentException ex) {
                System.out.println("Dato inválido: " + ex.getMessage() + " Intenta de nuevo.");
            }
        }
        universidad.agregarEstudiante(estudiante);

        System.out.println("Selecciona el curso donde inscribir al estudiante:");
        for (int i = 0; i < cursos.size(); i++) {
            System.out.println((i + 1) + ". " + cursos.get(i).getNombre());
        }
        int eleccion = leerEntero();

        Curso curso = universidad.buscarCursoPorIndice(eleccion - 1);
        if (curso == null) {
            System.out.println("Selección de curso inválida. El estudiante se creó pero no se inscribió.");
        } else {
            curso.agregarEstudiante(estudiante);
            System.out.println("Estudiante " + estudiante.getId() + " creado e inscrito en " + curso.getNombre());
        }
    }

    // ---------------------------------------------------------------
    // d) Crear un nuevo curso usando un profesor y estudiantes existentes
    // ---------------------------------------------------------------
    private void crearCursoConProfesorYEstudiantes() {
        List<Profesor> profesores = universidad.getProfesores();
        if (profesores.isEmpty()) {
            System.out.println("No hay profesores disponibles.");
            return;
        }

        String nombre = leerNombreCurso("Nombre del nuevo curso: ");
        String salon = leerSalon("Salón: ");

        System.out.println("Selecciona un profesor:");
        for (int i = 0; i < profesores.size(); i++) {
            System.out.println((i + 1) + ". " + profesores.get(i).getNombre() + " (" + profesores.get(i).getTipo() + ")");
        }
        int eleccionProfesor = leerEntero();
        if (eleccionProfesor < 1 || eleccionProfesor > profesores.size()) {
            System.out.println("Selección de profesor inválida. Se canceló la creación del curso.");
            return;
        }
        Profesor profesor = profesores.get(eleccionProfesor - 1);

        Curso curso;
        try {
            curso = new Curso(nombre, salon, profesor);
        } catch (IllegalArgumentException ex) {
            // En teoría no debería ocurrir porque nombre y salón ya se
            // pidieron validados, pero se deja el control por seguridad.
            System.out.println("No se pudo crear el curso: " + ex.getMessage());
            return;
        }

        System.out.println("Agrega estudiantes existentes por id (escribe 'listo' para terminar):");
        List<Estudiante> todosLosEstudiantes = universidad.getEstudiantes();
        for (Estudiante e : todosLosEstudiantes) {
            System.out.println("  " + e);
        }
        while (true) {
            System.out.print("Id del estudiante a agregar (o 'listo'): ");
            String entrada = scanner.nextLine().trim();
            if (entrada.equalsIgnoreCase("listo")) {
                break;
            }
            Estudiante encontrado = universidad.buscarEstudiantePorId(entrada);
            if (encontrado == null) {
                System.out.println("No se encontró estudiante con id " + entrada);
            } else {
                curso.agregarEstudiante(encontrado);
            }
        }

        universidad.agregarCurso(curso);
        System.out.println("Curso creado: " + curso);
    }

    // ---------------------------------------------------------------
    // e) Listar todos los cursos de un estudiante, buscando por id
    // ---------------------------------------------------------------
    private void listarCursosDeEstudiante() {
        System.out.print("Ingresa el id del estudiante: ");
        String id = scanner.nextLine().trim();

        Estudiante estudiante = universidad.buscarEstudiantePorId(id);
        if (estudiante == null) {
            System.out.println("No se encontró estudiante con id " + id);
            return;
        }

        List<Curso> encontrados = universidad.buscarCursosPorEstudianteId(id);
        if (encontrados.isEmpty()) {
            System.out.println(estudiante.getNombre() + " no está inscrito en ningún curso.");
        } else {
            System.out.println("Cursos de " + estudiante.getNombre() + " (" + id + "):");
            for (Curso c : encontrados) {
                System.out.println("  - " + c.getNombre() + " (" + c.getSalon() + ")");
            }
        }
    }

    // ---------------------------------------------------------------
    // Métodos auxiliares de lectura con validación (regex y números)
    // ---------------------------------------------------------------

    /**
     * Pide un nombre de persona (profesor o estudiante) y no continúa
     * hasta que el usuario escriba un texto válido según el patrón regex
     * definido en Validador (solo letras y espacios).
     */
    private String leerNombrePersona(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();
            if (Validador.esNombrePersonaValido(texto)) {
                return texto;
            }
            System.out.println("Nombre inválido: solo se permiten letras y espacios (sin números ni símbolos).");
        }
    }

    /**
     * Pide un nombre de curso y no continúa hasta que sea válido según el
     * patrón regex (letras, números y espacios).
     */
    private String leerNombreCurso(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();
            if (Validador.esNombreCursoValido(texto)) {
                return texto;
            }
            System.out.println("Nombre de curso inválido: solo se permiten letras, números y espacios.");
        }
    }

    /**
     * Pide el salón y no continúa hasta que sea válido según el patrón
     * regex (letras y/o números).
     */
    private String leerSalon(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();
            if (Validador.esSalonValido(texto)) {
                return texto;
            }
            System.out.println("Salón inválido: solo se permiten letras y/o números.");
        }
    }

    /**
     * Pide un número entero y no continúa hasta que el usuario escriba
     * algo que se pueda convertir correctamente (evita que un texto no
     * numérico rompa el programa con NumberFormatException).
     */
    private int leerEntero() {
        while (true) {
            String linea = scanner.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.print("Por favor ingresa un número válido: ");
            }
        }
    }
}
