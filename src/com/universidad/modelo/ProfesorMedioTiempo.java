package com.universidad.modelo;

/**
 * Profesor de medio tiempo.
 *
 * Regla de negocio para el salario:
 *   salario = salarioBase * horasActivasSemana
 * Es decir: el salario base se multiplica por las horas activas que el
 * profesor trabaja cada semana.
 *
 * Esta clase hereda de Profesor (herencia) y sobreescribe calcularSalario()
 * y getTipo() con su propia implementación (polimorfismo).
 */
public class ProfesorMedioTiempo extends Profesor {

    /** Máximo razonable de horas activas semanales para un cargo de medio tiempo. */
    private static final double MAX_HORAS_SEMANA = 40;

    private double horasActivasSemana;

    /**
     * @param nombre             nombre del profesor (validado en la clase Profesor)
     * @param salarioBase        salario base (validado en la clase Profesor)
     * @param horasActivasSemana horas activas por semana; debe estar entre 1 y 40
     * @throws IllegalArgumentException si las horas están fuera del rango permitido
     */
    public ProfesorMedioTiempo(String nombre, double salarioBase, double horasActivasSemana) {
        super(nombre, salarioBase);
        validarHorasActivasSemana(horasActivasSemana);
        this.horasActivasSemana = horasActivasSemana;
    }

    public double getHorasActivasSemana() {
        return horasActivasSemana;
    }

    public void setHorasActivasSemana(double horasActivasSemana) {
        validarHorasActivasSemana(horasActivasSemana);
        this.horasActivasSemana = horasActivasSemana;
    }

    private static void validarHorasActivasSemana(double horasActivasSemana) {
        if (horasActivasSemana <= 0 || horasActivasSemana > MAX_HORAS_SEMANA) {
            throw new IllegalArgumentException(
                    "Las horas activas por semana deben estar entre 1 y " + (int) MAX_HORAS_SEMANA + ".");
        }
    }

    /**
     * Implementación polimórfica de la regla de salario para medio tiempo.
     */
    @Override
    public double calcularSalario() {
        return salarioBase * horasActivasSemana;
    }

    @Override
    public String getTipo() {
        return "Medio tiempo";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Horas activas/semana: %.1f", horasActivasSemana);
    }
}
