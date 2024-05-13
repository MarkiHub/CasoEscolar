/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.itson.dominioescolar;

import java.io.Serializable;

/**
 *
 * @author Elkur
 */
public class Calificacion implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private float calificacion;
    private long idAlumno;
    private long idCurso;

    public Calificacion() {
    }

    public Calificacion(long id) {
        this.id = id;
    }

    public long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(long idCurso) {
        this.idCurso = idCurso;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public long getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(long idAlumno) {
        this.idAlumno = idAlumno;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Calificacion other = (Calificacion) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Calificacion{" + "id=" + id + ", calificacion=" + calificacion + ", idAlumno=" + idAlumno + '}';
    }

}
