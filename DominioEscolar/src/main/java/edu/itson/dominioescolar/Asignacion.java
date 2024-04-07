/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.itson.dominioescolar;

/**
 *
 * @author Elkur
 */
public class Asignacion {

    private long id;
    private String nombre;
    private long idCurso;

    public Asignacion() {
    }

    public Asignacion(long id) {
        this.id = id;
    }

    public Asignacion(long id, String nombre, long idCurso) {
        this.id = id;
        this.nombre = nombre;
        this.idCurso = idCurso;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(long idCurso) {
        this.idCurso = idCurso;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Asignacion other = (Asignacion) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Asignacion{" + "id=" + id + ", nombre=" + nombre + ", idCurso=" + idCurso + '}';
    }

}
