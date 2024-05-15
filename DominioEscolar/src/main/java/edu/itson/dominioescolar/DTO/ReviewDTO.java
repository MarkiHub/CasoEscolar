/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.itson.dominioescolar.DTO;

import edu.itson.dominioescolar.Asignacion;
import java.util.List;

/**
 *
 * @author Elkur
 */
public class ReviewDTO {

    private long idAsignacion;
    private long idAlumno;
    private long idEntregaAsignacion;
    private int calificacion;

    public long getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(long idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public long getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(long idAlumno) {
        this.idAlumno = idAlumno;
    }

    public long getIdEntregaAsignacion() {
        return idEntregaAsignacion;
    }

    public void setIdEntregaAsignacion(long idEntregaAsignacion) {
        this.idEntregaAsignacion = idEntregaAsignacion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" + "idAsignacion=" + idAsignacion + ", idAlumno=" + idAlumno + ", idEntregaAsignacion=" + idEntregaAsignacion + ", calificacion=" + calificacion + '}';
    }

}
