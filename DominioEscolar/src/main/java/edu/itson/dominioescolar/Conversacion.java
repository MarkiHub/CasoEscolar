/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.itson.dominioescolar;

/**
 *
 * @author ildex
 */
public class Conversacion {

    private Long id;
    private Profesor profesor;
    private Padre padre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Padre getPadre() {
        return padre;
    }

    public void setPadre(Padre padre) {
        this.padre = padre;
    }

    @Override
    public String toString() {
        return "Conversacion{" + "id=" + id + ", profesor=" + profesor + ", padre=" + padre + '}';
    }

}
