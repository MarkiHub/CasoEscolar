/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.itson.dominioescolar;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author ildex
 */
public class EntregaAsignacion {
    private Date fechaEntrega;
    private float calificacion;
    private long idAlumno;
    private long idAsignacion;

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
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

    public long getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(long idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    @Override
    public String toString() {
        return "Entrega{" + "fechaEntrega=" + fechaEntrega + ", calificacion=" + calificacion + ", idAlumno=" + idAlumno + ", idAsignacion=" + idAsignacion + '}';
    }
    
}
