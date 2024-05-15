/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.itson.dominioescolar.DTO;

import java.sql.Timestamp;

/**
 *
 * @author Elkur
 */
public class EntregaDTO {

    private long idEntrega;
    private String nombreAsignacion;
    private Timestamp fechaEntrega;
    private long idAlumno;
    private String nombreAlumno;
    private Integer calificacion;
    private long idAsignacion;

    public EntregaDTO() {
    }

    public long getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(long idEntrega) {
        this.idEntrega = idEntrega;
    }

    public String getNombreAsignacion() {
        return nombreAsignacion;
    }

    public void setNombreAsignacion(String nombreAsignacion) {
        this.nombreAsignacion = nombreAsignacion;
    }

    public Timestamp getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Timestamp fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public long getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(long idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public long getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(long idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    @Override
    public String toString() {
        return "EntregaDTO{" + "idEntrega=" + idEntrega + ", nombreAsignacion=" + nombreAsignacion + ", fechaEntrega=" + fechaEntrega + ", idAlumno=" + idAlumno + ", nombreAlumno=" + nombreAlumno + ", calificacion=" + calificacion + '}';
    }

}
