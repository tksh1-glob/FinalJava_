package com.universidad.modelo;

import com.universidad.util.Validador;

/**
 * Clase abstracta que representa a un Profesor de la universidad.
 *
 * Conceptos de POO aplicados:
 *  - Encapsulamiento: todos los atributos son privados, expuestos mediante
 *    métodos getter/setter que además validan los datos antes de asignarlos.
 *  - Herencia: ProfesorTiempoCompleto y ProfesorMedioTiempo extienden esta clase.
 *  - Polimorfismo: calcularSalario() y getTipo() son abstractos; cada subtipo
 *    implementa su propia versión, pero el resto del programa puede tratar
 *    a cualquier profesor simplemente como un Profesor.
 *  - Atributo/método estático: un contador compartido por todas las
 *    instancias, usado también para generar automáticamente el id.
 */
public abstract class Profesor {

    // Atributo estático: se comparte entre TODAS las instancias de Profesor
    // (y de sus subclases), a diferencia de los atributos normales que son
    // independientes para cada objeto.
    private static int contadorProfesores = 0;

    private final int id;
    private String nombre;

    // protected: visible para las subclases, ya que ellas lo necesitan
    // dentro de su propia fórmula de calcularSalario().
    protected double salarioBase;

    /**
     * Constructor protegido: solo las subclases (ProfesorTiempoCompleto,
     * ProfesorMedioTiempo) pueden invocarlo mediante super(...), ya que
     * Profesor es abstracta y no debe poder instanciarse directamente.
     *
     * @param nombre      nombre completo del profesor (solo letras y espacios)
     * @param salarioBase salario base, debe ser mayor a cero
     * @throws IllegalArgumentException si el nombre no cumple el formato
     *         esperado (validado con expresión regular) o si el salario
     *         base no es un valor positivo
     */
    protected Profesor(String nombre, double salarioBase) {
        validarNombre(nombre);
        validarSalarioBase(salarioBase);
        this.id = ++contadorProfesores; // se incrementa el contador estático y se usa como id
        this.nombre = nombre.trim();
        this.salarioBase = salarioBase;
    }

    /**
     * Método estático: devuelve cuántos profesores se han creado en total
     * durante la ejecución del programa, sin exponer el atributo estático
     * directamente (encapsulamiento a nivel de clase).
     */
    public static int getContadorProfesores() {
        return contadorProfesores;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el nombre del profesor, validando primero el formato con
     * expresión regular (solo letras y espacios, sin números ni símbolos).
     */
    public void setNombre(String nombre) {
        validarNombre(nombre);
        this.nombre = nombre.trim();
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    /**
     * Cambia el salario base, validando que sea un número positivo.
     */
    public void setSalarioBase(double salarioBase) {
        validarSalarioBase(salarioBase);
        this.salarioBase = salarioBase;
    }

    /**
     * Valida el nombre usando el patrón de expresión regular definido en
     * la clase Validador (solo letras -incluyendo tildes y ñ- y espacios).
     */
    private static void validarNombre(String nombre) {
        if (!Validador.esNombrePersonaValido(nombre)) {
            throw new IllegalArgumentException(
                    "El nombre del profesor solo puede contener letras y espacios, y no puede estar vacío.");
        }
    }

    private static void validarSalarioBase(double salarioBase) {
        if (salarioBase <= 0) {
            throw new IllegalArgumentException("El salario base debe ser mayor a cero.");
        }
    }

    /**
     * Método abstracto y polimórfico: cada subclase concreta (tiempo
     * completo / medio tiempo) define su propia regla para calcular el
     * salario final del profesor.
     */
    public abstract double calcularSalario();

    /**
     * Método abstracto: devuelve una etiqueta corta con el tipo de
     * profesor. Se usa polimorfismo en vez de preguntar con "instanceof"
     * qué subtipo es cada profesor.
     */
    public abstract String getTipo();

    @Override
    public String toString() {
        return String.format(
                "[#%d] %-20s | Tipo: %-12s | Salario base: %10.2f | Salario calculado: %10.2f",
                id, nombre, getTipo(), salarioBase, calcularSalario());
    }
}
