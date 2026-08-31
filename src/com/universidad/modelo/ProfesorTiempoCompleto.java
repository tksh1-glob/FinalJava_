package com.universidad.modelo;

/**
 * Profesor de tiempo completo.
 *
 * Regla de negocio para el salario:
 *   salario = salarioBase * (aniosExperiencia * 1.10)
 * Es decir: el salario base se multiplica por el 110% de los años de
 * experiencia del profesor.
 *
 * Esta clase hereda de Profesor (herencia) y sobreescribe calcularSalario()
 * y getTipo() con su propia implementación (polimorfismo).
 */
public class ProfesorTiempoCompleto extends Profesor {

    /** Factor fijo del 110% usado en la fórmula del salario. */
    private static final double FACTOR_EXPERIENCIA = 1.10;

    /** Límite superior razonable para los años de experiencia. */
    private static final double MAX_ANIOS_EXPERIENCIA = 60;

    private double aniosExperiencia;

    /**
     * @param nombre           nombre del profesor (validado en la clase Profesor)
     * @param salarioBase      salario base (validado en la clase Profesor)
     * @param aniosExperiencia años de experiencia; debe estar entre 0 y 60
     * @throws IllegalArgumentException si los años de experiencia están
     *         fuera del rango permitido
     */
    public ProfesorTiempoCompleto(String nombre, double salarioBase, double aniosExperiencia) {
        // super(...) llama al constructor de Profesor, que valida nombre y salario.
        super(nombre, salarioBase);
        validarAniosExperiencia(aniosExperiencia);
        this.aniosExperiencia = aniosExperiencia;
    }

    public double getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(double aniosExperiencia) {
        validarAniosExperiencia(aniosExperiencia);
        this.aniosExperiencia = aniosExperiencia;
    }

    private static void validarAniosExperiencia(double aniosExperiencia) {
        if (aniosExperiencia < 0 || aniosExperiencia > MAX_ANIOS_EXPERIENCIA) {
            throw new IllegalArgumentException(
                    "Los años de experiencia deben estar entre 0 y " + (int) MAX_ANIOS_EXPERIENCIA + ".");
        }
    }

    /**
     * Implementación polimórfica de la regla de salario para tiempo completo.
     */
    @Override
    public double calcularSalario() {
        return salarioBase * (aniosExperiencia * FACTOR_EXPERIENCIA);
    }

    @Override
    public String getTipo() {
        return "Tiempo completo";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Experiencia: %.1f años", aniosExperiencia);
    }
}
