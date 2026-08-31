package com.universidad.gestion;

import com.universidad.modelo.Curso;
import com.universidad.modelo.Estudiante;
import com.universidad.modelo.Profesor;
import com.universidad.modelo.ProfesorMedioTiempo;
import com.universidad.modelo.ProfesorTiempoCompleto;

import java.util.ArrayList;
import java.util.List;

/**
 * Capa de negocio/gestión del sistema.
 *
 * Contiene las listas de Profesores, Estudiantes y Cursos, y expone las
 * operaciones que necesita el menú de la aplicación (agregar, buscar, listar).
 *
 * Esta clase NO contiene ninguna lógica de lectura o impresión por consola.
 * Solo trabaja con datos (listas, booleanos, referencias que pueden ser
 * null) y deja que la capa de IO (ConsolaIO) decida cómo mostrarlos al
 * usuario. Esto mantiene separada la lógica de negocio de la presentación.
 */
public class Universidad {

    /** Cantidad mínima de profesores de cada tipo pedida en el enunciado. */
    public static final int MINIMO_PROFESORES_POR_TIPO = 2;

    /** Cantidad mínima de estudiantes pedida en el enunciado. */
    public static final int MINIMO_ESTUDIANTES = 6;

    /** Cantidad mínima de cursos pedida en el enunciado. */
    public static final int MINIMO_CURSOS = 4;

    private final String nombreUniversidad;
    private final List<Profesor> profesores = new ArrayList<Profesor>();
    private final List<Estudiante> estudiantes = new ArrayList<Estudiante>();
    private final List<Curso> cursos = new ArrayList<Curso>();

    public Universidad(String nombreUniversidad) {
        this.nombreUniversidad = nombreUniversidad;
    }

    public String getNombreUniversidad() {
        return nombreUniversidad;
    }

    // ---------------------------------------------------------------
    // Profesores
    // ---------------------------------------------------------------

    public void agregarProfesor(Profesor profesor) {
        profesores.add(profesor);
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    // ---------------------------------------------------------------
    // Estudiantes
    // ---------------------------------------------------------------

    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    /**
     * Busca un estudiante por su id (sin distinguir mayúsculas/minúsculas).
     * Devuelve el estudiante encontrado, o null si no existe ninguno con
     * ese id. Quien llama a este método debe comprobar si el resultado es
     * null antes de usarlo.
     */
    public Estudiante buscarEstudiantePorId(String id) {
        for (Estudiante e : estudiantes) {
            if (e.getId().equalsIgnoreCase(id)) {
                return e;
            }
        }
        return null;
    }

    // ---------------------------------------------------------------
    // Cursos
    // ---------------------------------------------------------------

    public void agregarCurso(Curso curso) {
        cursos.add(curso);
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    /**
     * Busca un curso según su posición dentro de la lista (tal como se
     * muestra numerado en el menú). Devuelve null si el índice está fuera
     * de rango.
     */
    public Curso buscarCursoPorIndice(int indice) {
        if (indice < 0 || indice >= cursos.size()) {
            return null;
        }
        return cursos.get(indice);
    }

    /**
     * Devuelve todos los cursos en los que está inscrito el estudiante
     * con el id dado. Se usa para la opción del menú "Listar los cursos
     * de un estudiante".
     */
    public List<Curso> buscarCursosPorEstudianteId(String idEstudiante) {
        List<Curso> resultado = new ArrayList<Curso>();
        for (Curso c : cursos) {
            if (c.tieneEstudiante(idEstudiante)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    /**
     * Carga los datos mínimos requeridos por el enunciado:
     * 2 profesores de tiempo completo, 2 de medio tiempo, 6 estudiantes
     * y 4 cursos, ya relacionados entre sí.
     */
    public void cargarDatosDeEjemplo() {
        // Profesores: aunque son de dos clases distintas, ambos se guardan
        // como referencias de tipo Profesor (polimorfismo).
        ProfesorTiempoCompleto p1 = new ProfesorTiempoCompleto("Ana Gomez", 2000, 5);
        ProfesorTiempoCompleto p2 = new ProfesorTiempoCompleto("Carlos Ruiz", 2200, 8);
        ProfesorMedioTiempo p3 = new ProfesorMedioTiempo("Laura Diaz", 25, 12);
        ProfesorMedioTiempo p4 = new ProfesorMedioTiempo("Miguel Torres", 30, 10);
        agregarProfesor(p1);
        agregarProfesor(p2);
        agregarProfesor(p3);
        agregarProfesor(p4);

        // Estudiantes
        Estudiante e1 = new Estudiante("Juan Perez", 20);
        Estudiante e2 = new Estudiante("Maria Lopez", 21);
        Estudiante e3 = new Estudiante("Pedro Sanchez", 19);
        Estudiante e4 = new Estudiante("Sofia Ramirez", 22);
        Estudiante e5 = new Estudiante("Diego Martinez", 20);
        Estudiante e6 = new Estudiante("Valentina Castro", 23);
        agregarEstudiante(e1);
        agregarEstudiante(e2);
        agregarEstudiante(e3);
        agregarEstudiante(e4);
        agregarEstudiante(e5);
        agregarEstudiante(e6);

        // Cursos, cada uno con su profesor y algunos estudiantes ya inscritos
        Curso c1 = new Curso("Fundamentos Java", "Salon 101", p1);
        c1.agregarEstudiante(e1);
        c1.agregarEstudiante(e2);
        c1.agregarEstudiante(e3);

        Curso c2 = new Curso("Bases de Datos", "Salon 102", p2);
        c2.agregarEstudiante(e2);
        c2.agregarEstudiante(e4);

        Curso c3 = new Curso("Desarrollo Web", "Salon 201", p3);
        c3.agregarEstudiante(e3);
        c3.agregarEstudiante(e5);
        c3.agregarEstudiante(e6);

        Curso c4 = new Curso("Algoritmos", "Salon 202", p4);
        c4.agregarEstudiante(e1);
        c4.agregarEstudiante(e6);

        agregarCurso(c1);
        agregarCurso(c2);
        agregarCurso(c3);
        agregarCurso(c4);
    }
}
