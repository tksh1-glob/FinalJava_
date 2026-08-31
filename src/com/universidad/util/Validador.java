package com.universidad.util;

import java.util.regex.Pattern;

/**
 * Clase utilitaria encargada de validar los campos de texto del sistema
 * usando expresiones regulares (regex).
 *
 * Se centralizan aquí todos los patrones para que:
 *  - No se repita la misma expresión regular en varias clases del modelo.
 *  - Si algún día cambia una regla (por ejemplo, permitir números en los
 *    nombres), solo se modifique en un único lugar.
 *
 * Es una clase de solo métodos estáticos, por eso el constructor es privado:
 * no tiene sentido crear una "instancia" de un validador.
 */
public final class Validador {

    /**
     * Nombres de personas (profesores y estudiantes):
     * solo letras (incluye tildes y la letra ñ) y espacios entre palabras.
     * No se permiten números ni símbolos, ni espacios dobles, ni que
     * empiece o termine en espacio.
     * Ejemplos válidos: "Ana Gomez", "María José Ñañez"
     */
    private static final Pattern PATRON_NOMBRE_PERSONA =
            Pattern.compile("^[A-Za-zÁÉÍÓÚÑÜáéíóúñü]+(\\s[A-Za-zÁÉÍÓÚÑÜáéíóúñü]+)*$");

    /**
     * Nombres de cursos: letras, números y espacios.
     * Permite números porque hay materias como "Cálculo II" o "Java 101".
     */
    private static final Pattern PATRON_NOMBRE_CURSO =
            Pattern.compile("^[A-Za-zÁÉÍÓÚÑÜáéíóúñü0-9]+(\\s[A-Za-zÁÉÍÓÚÑÜáéíóúñü0-9]+)*$");

    /**
     * Salón de clase: letras y/o números y espacios.
     * Ejemplos válidos: "Salón 101", "Lab 3", "A204"
     */
    private static final Pattern PATRON_SALON =
            Pattern.compile("^[A-Za-zÁÉÍÓÚÑÜáéíóúñü0-9]+(\\s[A-Za-zÁÉÍÓÚÑÜáéíóúñü0-9]+)*$");

    /**
     * Id de estudiante generado por el sistema: la letra "E" seguida de números.
     * Ejemplo válido: "E1001"
     */
    private static final Pattern PATRON_ID_ESTUDIANTE = Pattern.compile("^E\\d+$");

    // Constructor privado: esta clase solo se usa de forma estática.
    private Validador() {
    }

    /**
     * Valida que un texto sea un nombre de persona correcto
     * (solo letras y espacios simples entre palabras).
     */
    public static boolean esNombrePersonaValido(String texto) {
        return texto != null && PATRON_NOMBRE_PERSONA.matcher(texto.trim()).matches();
    }

    /**
     * Valida que un texto sea un nombre de curso correcto
     * (letras, números y espacios simples entre palabras).
     */
    public static boolean esNombreCursoValido(String texto) {
        return texto != null && PATRON_NOMBRE_CURSO.matcher(texto.trim()).matches();
    }

    /**
     * Valida que un texto sea un nombre de salón correcto
     * (letras y/o números, con espacios simples entre palabras).
     */
    public static boolean esSalonValido(String texto) {
        return texto != null && PATRON_SALON.matcher(texto.trim()).matches();
    }

    /**
     * Valida que un texto tenga el formato de id de estudiante generado
     * por el sistema (por ejemplo "E1001").
     */
    public static boolean esIdEstudianteValido(String texto) {
        return texto != null && PATRON_ID_ESTUDIANTE.matcher(texto.trim()).matches();
    }
}
