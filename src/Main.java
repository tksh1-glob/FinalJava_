package com.universidad;

import com.universidad.io.ConsolaIO;
import com.universidad.gestion.Universidad;

/**
 * Punto de entrada de la aplicación.
 * Conecta la capa de gestión (Universidad) con la capa de IO (ConsolaIO),
 * y carga los datos mínimos de ejemplo requeridos.
 */
public class Main {
    public static void main(String[] args) {
        Universidad universidad = new Universidad("Mi Universidad");
        universidad.cargarDatosDeEjemplo();

        ConsolaIO consola = new ConsolaIO(universidad);
        consola.ejecutar();
    }
}