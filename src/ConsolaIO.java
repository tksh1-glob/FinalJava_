package com.universidad.io;

import com.universidad.gestion.Universidad;
import com.universidad.modelo.Curso;
import com.universidad.modelo.Estudiante;
import com.universidad.modelo.Profesor;

import java.util.List;
import java.util.Scanner;

/**
 * Capa de entrada/salida (presentación).
 * Todo el código de Scanner y System.out vive aquí, NO dentro de las clases
 * del modelo (Profesor, Estudiante, Curso).
 */
public class ConsolaIO {

    private final Scanner scanner;
    private final Universidad universidad;

    public ConsolaIO(Universidad universidad) {
        this.universidad = universidad;
        this.scanner = new Scanner(System.in);
    }

    public void ejecutar() {
        boolean activo = true;
        while (activo) {
            imprimirMenu();
            String opcion = scanner.nextLine().trim();
            switch (opcion) {
                case "a":
                    imprimirTodosLosProfesores();
                    break;
                case "b":
                    imprimirMenuDeCursos();
                    break;
                case "c":
                    crearEstudianteYAgregarloACurso();
                    break;
                case "d":
                    crearCursoConProfesorYEstudiantes();
                    break;
                case "e":
                    listarCursosDeEstudiante();
                    break;
                case "f":
                    activo = false;
                    System.out.println("¡Hasta luego!");
                    break;
                default:
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

    // a) Imprimir todos los profesores
    private void imprimirTodosLosProfesores() {
        System.out.println("\n--- Profesores (" + Profesor.getContadorProfesores() + " en total) ---");
        for (Profesor p : universidad.getProfesores()) {
            System.out.println(p);
        }
    }

    // b) Imprimir todos los cursos + submenú de detalle
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
        universidad.buscarCursoPorIndice(eleccion - 1).ifPresentOrElse(
                this::imprimirDetalleCurso,
                () -> System.out.println("Número de curso inválido.")
        );
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

    // c) Crear un nuevo estudiante y agregarlo a un curso existente
    private void crearEstudianteYAgregarloACurso() {
        List<Curso> cursos = universidad.getCursos();
        if (cursos.isEmpty()) {
            System.out.println("No hay cursos disponibles. Crea un curso primero.");
            return;
        }

        System.out.print("Nombre del estudiante: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Edad del estudiante: ");
        int edad = leerEntero();

        Estudiante estudiante = new Estudiante(nombre, edad);
        universidad.agregarEstudiante(estudiante);

        System.out.println("Selecciona el curso donde inscribir al estudiante:");
        for (int i = 0; i < cursos.size(); i++) {
            System.out.println((i + 1) + ". " + cursos.get(i).getNombre());
        }
        int eleccion = leerEntero();
        universidad.buscarCursoPorIndice(eleccion - 1).ifPresentOrElse(
                curso -> {
                    curso.agregarEstudiante(estudiante);
                    System.out.println("Estudiante " + estudiante.getId() + " creado e inscrito en " + curso.getNombre());
                },
                () -> System.out.println("Selección de curso inválida. El estudiante se creó pero no se inscribió.")
        );
    }

    // d) Crear un nuevo curso usando un profesor y estudiantes existentes
    private void crearCursoConProfesorYEstudiantes() {
        List<Profesor> profesores = universidad.getProfesores();
        if (profesores.isEmpty()) {
            System.out.println("No hay profesores disponibles.");
            return;
        }

        System.out.print("Nombre del nuevo curso: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Salón: ");
        String salon = scanner.nextLine().trim();

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

        Curso curso = new Curso(nombre, salon, profesor);

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
            universidad.buscarEstudiantePorId(entrada).ifPresentOrElse(
                    curso::agregarEstudiante,
                    () -> System.out.println("No se encontró estudiante con id " + entrada)
            );
        }

        universidad.agregarCurso(curso);
        System.out.println("Curso creado: " + curso);
    }

    // e) Listar todos los cursos de un estudiante, buscando por id
    private void listarCursosDeEstudiante() {
        System.out.print("Ingresa el id del estudiante: ");
        String id = scanner.nextLine().trim();

        universidad.buscarEstudiantePorId(id).ifPresentOrElse(
                estudiante -> {
                    List<Curso> encontrados = universidad.buscarCursosPorEstudianteId(id);
                    if (encontrados.isEmpty()) {
                        System.out.println(estudiante.getNombre() + " no está inscrito en ningún curso.");
                    } else {
                        System.out.println("Cursos de " + estudiante.getNombre() + " (" + id + "):");
                        for (Curso c : encontrados) {
                            System.out.println("  - " + c.getNombre() + " (" + c.getSalon() + ")");
                        }
                    }
                },
                () -> System.out.println("No se encontró estudiante con id " + id)
        );
    }

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