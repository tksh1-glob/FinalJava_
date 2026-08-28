package com.universidad.modelo;

public class Estudiante {


    private static int secuenciaId = 1000;

    private final String id;
    private String nombre;
    private int edad;

    public Estudiante(String nombre, int edad) {
        this.id = generarSiguienteId();
        this.nombre = nombre;
        this.edad = edad;
    }


    public Estudiante(String id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    // Método estático
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return String.format("[%s] %-20s | Edad: %d", id, nombre, edad);
    }

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