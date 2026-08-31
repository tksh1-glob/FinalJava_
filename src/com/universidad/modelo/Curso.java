package com.universidad.modelo;

import com.universidad.util.Validador;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una Clase/Curso de la universidad.
 *
 * Se llama "Curso" (y no "Clase") para evitar la confusión con la clase
 * predefinida de Java java.lang.Class.
 *
 * Contiene un salón, un Profesor asignado (guardado como referencia
 * polimórfica de tipo Profesor) y una lista de Estudiantes inscritos.
 */
public class Curso {

    // Atributo estático: cuenta cuántos cursos se han creado en total,
    // y también se usa para asignar un id incremental a cada curso nuevo.
    private static int contadorCursos = 0;

    private final int id;
    private String nombre;
    private String salon;
    private Profesor profesor;
    private final List<Estudiante> estudiantes;

    /**
     * Constructor principal: crea un curso sin estudiantes inscritos todavía.
     *
     * @param nombre   nombre del curso (letras, números y espacios)
     * @param salon    identificador del salón (letras y/o números)
     * @param profesor profesor asignado al curso; no puede ser null
     * @throws IllegalArgumentException si el nombre o el salón no cumplen
     *         su formato, o si el profesor es null
     */
    public Curso(String nombre, String salon, Profesor profesor) {
        validarNombre(nombre);
        validarSalon(salon);
        validarProfesor(profesor);
        this.id = ++contadorCursos;
        this.nombre = nombre.trim();
        this.salon = salon.trim();
        this.profesor = profesor;
        this.estudiantes = new ArrayList<>();
    }

    /**
     * Constructor sobrecargado: además de los datos básicos, recibe de una
     * vez la lista inicial de estudiantes inscritos.
     */
    public Curso(String nombre, String salon, Profesor profesor, List<Estudiante> estudiantes) {
        this(nombre, salon, profesor); // reutiliza las validaciones del constructor principal
        if (estudiantes != null) {
            this.estudiantes.addAll(estudiantes);
        }
    }

    public static int getContadorCursos() {
        return contadorCursos;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        validarNombre(nombre);
        this.nombre = nombre.trim();
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        validarSalon(salon);
        this.salon = salon.trim();
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        validarProfesor(profesor);
        this.profesor = profesor;
    }

    /**
     * Devuelve la lista de estudiantes inscritos en este curso.
     * Para agregar estudiantes se debe usar agregarEstudiante(), que valida
     * que no se repitan estudiantes dentro del mismo curso.
     */
    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    /**
     * Agrega un estudiante al curso, evitando duplicados
     * (dos estudiantes son iguales si tienen el mismo id).
     */
    public void agregarEstudiante(Estudiante estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("No se puede agregar un estudiante nulo al curso.");
        }
        if (!estudiantes.contains(estudiante)) {
            estudiantes.add(estudiante);
        }
    }

    /**
     * Indica si el estudiante con el id dado está inscrito en este curso.
     * Se usa, por ejemplo, para la opción del menú que busca en qué cursos
     * está inscrito un estudiante.
     */
    public boolean tieneEstudiante(String idEstudiante) {
        for (Estudiante e : estudiantes) {
            if (e.getId().equalsIgnoreCase(idEstudiante)) {
                return true;
            }
        }
        return false;
    }

    private static void validarNombre(String nombre) {
        if (!Validador.esNombreCursoValido(nombre)) {
            throw new IllegalArgumentException(
                    "El nombre del curso solo puede contener letras, números y espacios, y no puede estar vacío.");
        }
    }

    private static void validarSalon(String salon) {
        if (!Validador.esSalonValido(salon)) {
            throw new IllegalArgumentException(
                    "El salón solo puede contener letras y/o números, y no puede estar vacío.");
        }
    }

    private static void validarProfesor(Profesor profesor) {
        if (profesor == null) {
            throw new IllegalArgumentException("El curso debe tener un profesor asignado.");
        }
    }

    @Override
    public String toString() {
        return String.format("[#%d] %-15s | Salón: %-10s | Profesor: %s | Estudiantes inscritos: %d",
                id, nombre, salon, profesor != null ? profesor.getNombre() : "N/A", estudiantes.size());
    }
}
