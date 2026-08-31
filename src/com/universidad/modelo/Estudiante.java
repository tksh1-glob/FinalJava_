package com.universidad.modelo;

import com.universidad.util.Validador;

/**
 * Representa a un Estudiante de la universidad.
 *
 * Conceptos de POO aplicados:
 *  - Encapsulamiento: atributos privados con getters/setters que validan
 *    los datos antes de asignarlos.
 *  - Atributo/método estático: una secuencia numérica compartida que se
 *    usa para generar automáticamente un id único por estudiante.
 *  - Sobrecarga de constructores: dos formas distintas de crear un
 *    Estudiante, según si el id se genera automáticamente o se conoce de antemano.
 */
public class Estudiante {

    /** Edad mínima permitida para un estudiante. */
    private static final int EDAD_MINIMA = 15;

    /** Edad máxima permitida para un estudiante. */
    private static final int EDAD_MAXIMA = 100;

    // Atributo estático: es la "semilla" que se va incrementando cada vez
    // que se crea un estudiante nuevo, para generar ids únicos como E1001, E1002...
    private static int secuenciaId = 1000;

    private final String id;
    private String nombre;
    private int edad;

    /**
     * Constructor principal: el id se genera automáticamente a partir del
     * contador estático interno.
     *
     * @param nombre nombre del estudiante (solo letras y espacios)
     * @param edad   edad del estudiante, entre 15 y 100 años
     * @throws IllegalArgumentException si el nombre no cumple el formato
     *         esperado o la edad está fuera de rango
     */
    public Estudiante(String nombre, int edad) {
        validarNombre(nombre);
        validarEdad(edad);
        this.id = generarSiguienteId();
        this.nombre = nombre.trim();
        this.edad = edad;
    }

    /**
     * Constructor sobrecargado: permite especificar un id explícito en vez
     * de generarlo automáticamente. Útil, por ejemplo, si en el futuro se
     * cargan estudiantes ya existentes desde un archivo.
     *
     * @param id     id con formato "E" seguido de números (ej. "E1001")
     * @param nombre nombre del estudiante (solo letras y espacios)
     * @param edad   edad del estudiante, entre 15 y 100 años
     * @throws IllegalArgumentException si alguno de los datos no cumple su formato
     */
    public Estudiante(String id, String nombre, int edad) {
        validarId(id);
        validarNombre(nombre);
        validarEdad(edad);
        this.id = id.trim();
        this.nombre = nombre.trim();
        this.edad = edad;
    }

    /**
     * Método estático privado: encapsula la forma en la que se generan
     * los nuevos ids, para que el resto de la clase no necesite saber
     * cómo se arma el String final.
     */
    private static String generarSiguienteId() {
        secuenciaId++;
        return "E" + secuenciaId;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el nombre del estudiante, validando el formato con expresión
     * regular (solo letras y espacios).
     */
    public void setNombre(String nombre) {
        validarNombre(nombre);
        this.nombre = nombre.trim();
    }

    public int getEdad() {
        return edad;
    }

    /**
     * Cambia la edad del estudiante, validando que esté dentro del rango permitido.
     */
    public void setEdad(int edad) {
        validarEdad(edad);
        this.edad = edad;
    }

    private static void validarNombre(String nombre) {
        if (!Validador.esNombrePersonaValido(nombre)) {
            throw new IllegalArgumentException(
                    "El nombre del estudiante solo puede contener letras y espacios, y no puede estar vacío.");
        }
    }

    private static void validarEdad(int edad) {
        if (edad < EDAD_MINIMA || edad > EDAD_MAXIMA) {
            throw new IllegalArgumentException(
                    "La edad del estudiante debe estar entre " + EDAD_MINIMA + " y " + EDAD_MAXIMA + " años.");
        }
    }

    private static void validarId(String id) {
        if (!Validador.esIdEstudianteValido(id)) {
            throw new IllegalArgumentException("El id del estudiante debe tener el formato 'E' seguido de números (ej. E1001).");
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %-20s | Edad: %d", id, nombre, edad);
    }

    /**
     * Dos estudiantes se consideran iguales si tienen el mismo id,
     * sin importar que el resto de sus datos difiera.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estudiante)) return false;
        Estudiante estudiante = (Estudiante) o;
        return id.equals(estudiante.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
