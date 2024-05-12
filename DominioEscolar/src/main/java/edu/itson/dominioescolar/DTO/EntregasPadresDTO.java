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
public class EntregasPadresDTO {

    private long idAsignacion;
    private String nombreAsignacion;
    private Timestamp fechaEntrega;
    private long idEntrega;
    private String nombreAlumno;

    public long getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(long idAsignacion) {
        this.idAsignacion = idAsignacion;
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

    public long getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(long idEntrega) {
        this.idEntrega = idEntrega;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    @Override
    public String toString() {
        return "EntregasPadresDTO{" + "idAsignacion=" + idAsignacion + ", nombreAsignacion=" + nombreAsignacion + ", fechaEntrega=" + fechaEntrega + ", idEntrega=" + idEntrega + ", nombreAlumno=" + nombreAlumno + '}';
    }

}
