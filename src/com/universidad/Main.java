package com.universidad;

import com.universidad.gestion.Universidad;
import com.universidad.io.ConsolaIO;

/**
 * Clase principal (punto de entrada) del Sistema de Gestión Universitaria.
 *
 * Su única responsabilidad es "conectar" las capas del programa:
 *  1. Crea la Universidad (capa de gestión) y le carga los datos mínimos
 *     de ejemplo requeridos por el enunciado.
 *  2. Crea la ConsolaIO (capa de entrada/salida) pasándole esa Universidad.
 *  3. Arranca el menú interactivo llamando a ejecutar().
 */
public class Main {

    public static void main(String[] args) {
        Universidad universidad = new Universidad("Mi Universidad");
        universidad.cargarDatosDeEjemplo();

        ConsolaIO consola = new ConsolaIO(universidad);
        consola.ejecutar();
    }
}
