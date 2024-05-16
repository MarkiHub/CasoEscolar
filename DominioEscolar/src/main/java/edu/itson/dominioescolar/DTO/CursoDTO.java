/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.itson.dominioescolar.DTO;

/**
 *
 * @author Elkur
 */
public class CursoDTO {

    private String nombreMateria;
    private long idCurso;

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(long idCurso) {
        this.idCurso = idCurso;
    }

    @Override
    public String toString() {
        return "CursoDTO{" + "nombreMateria=" + nombreMateria + ", idCurso=" + idCurso + '}';
    }

}
