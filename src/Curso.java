package com.universidad.modelo;

import java.util.ArrayList;
import java.util.List;


public class Curso {

    private static int contadorCursos = 0;

    private final int id;
    private String nombre;
    private String salon;
    private Profesor profesor;
    private final List<Estudiante> estudiantes;

    public Curso(String nombre, String salon, Profesor profesor) {
        this.id = ++contadorCursos;
        this.nombre = nombre;
        this.salon = salon;
        this.profesor = profesor;
        this.estudiantes = new ArrayList<>();
    }

    public Curso(String nombre, String salon, Profesor profesor, List<Estudiante> estudiantes) {
        this(nombre, salon, profesor);
        this.estudiantes.addAll(estudiantes);
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
        this.nombre = nombre;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void agregarEstudiante(Estudiante estudiante) {
        if (!estudiantes.contains(estudiante)) {
            estudiantes.add(estudiante);
        }
    }

    public boolean tieneEstudiante(String idEstudiante) {
        return estudiantes.stream().anyMatch(e -> e.getId().equalsIgnoreCase(idEstudiante));
    }

    @Override
    public String toString() {
        return String.format("[#%d] %-15s | Salón: %-10s | Profesor: %s | Estudiantes inscritos: %d",
                id, nombre, salon, profesor != null ? profesor.getNombre() : "N/A", estudiantes.size());
    }
}