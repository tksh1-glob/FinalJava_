

import com.universidad.modelo.Curso;
import com.universidad.modelo.ProfesorTiempoCompleto;
import com.universidad.modelo.ProfesorMedioTiempo;
import com.universidad.modelo.Estudiante;
import com.universidad.modelo.Profesor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Capa de negocio/gestión.
 * Contiene las listas de Profesores, Estudiantes y Cursos, y expone las
 * operaciones requeridas por el menú (crear, buscar, listar, etc).
 *
 * Esta clase NO contiene lógica de lectura/impresión por consola:
 * solo devuelve datos (Strings, listas, booleanos) para que la capa de IO
 * (ConsolaIO) decida cómo presentarlos.
 */
public class Universidad {

    public static final int MINIMO_PROFESORES_POR_TIPO = 2;
    public static final int MINIMO_ESTUDIANTES = 6;
    public static final int MINIMO_CURSOS = 4;

    private final String nombreUniversidad;
    private final List<Profesor> profesores = new ArrayList<>();
    private final List<Estudiante> estudiantes = new ArrayList<>();
    private final List<Curso> cursos = new ArrayList<>();

    public Universidad(String nombreUniversidad) {
        this.nombreUniversidad = nombreUniversidad;
    }

    public String getNombreUniversidad() {
        return nombreUniversidad;
    }

    // ---------- Profesores ----------

    public void agregarProfesor(Profesor profesor) {
        profesores.add(profesor);
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    // ---------- Estudiantes ----------

    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public Optional<Estudiante> buscarEstudiantePorId(String id) {
        return estudiantes.stream()
                .filter(e -> e.getId().equalsIgnoreCase(id))
                .findFirst();
    }

    // ---------- Cursos ----------

    public void agregarCurso(Curso curso) {
        cursos.add(curso);
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public Optional<Curso> buscarCursoPorIndice(int indice) {
        if (indice < 0 || indice >= cursos.size()) {
            return Optional.empty();
        }
        return Optional.of(cursos.get(indice));
    }

    /**
     * Devuelve todos los cursos en los que está inscrito el id de estudiante dado.
     */
    public List<Curso> buscarCursosPorEstudianteId(String idEstudiante) {
        List<Curso> resultado = new ArrayList<>();
        for (Curso c : cursos) {
            if (c.tieneEstudiante(idEstudiante)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    /**
     * Carga los datos mínimos requeridos de ejemplo:
     * 2 profesores tiempo completo + 2 medio tiempo, 6 estudiantes, 4 cursos.
     */
    public void cargarDatosDeEjemplo() {
        // Profesores (polimorfismo: ambos tipos se guardan como referencias Profesor)
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

        // Cursos
        Curso c1 = new Curso("Fundamentos Java", "Salón 101", p1);
        c1.agregarEstudiante(e1);
        c1.agregarEstudiante(e2);
        c1.agregarEstudiante(e3);

        Curso c2 = new Curso("Bases de Datos", "Salón 102", p2);
        c2.agregarEstudiante(e2);
        c2.agregarEstudiante(e4);

        Curso c3 = new Curso("Desarrollo Web", "Salón 201", p3);
        c3.agregarEstudiante(e3);
        c3.agregarEstudiante(e5);
        c3.agregarEstudiante(e6);

        Curso c4 = new Curso("Algoritmos", "Salón 202", p4);
        c4.agregarEstudiante(e1);
        c4.agregarEstudiante(e6);

        agregarCurso(c1);
        agregarCurso(c2);
        agregarCurso(c3);
        agregarCurso(c4);
    }
}