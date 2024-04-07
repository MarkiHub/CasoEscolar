/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.itson.dominioescolar;

/**
 *
 * @author Elkur
 */
public class Curso {

    private long id;
    private long idProfe;
    private long idMateria;

    public Curso() {
    }

    public Curso(long id) {
        this.id = id;
    }

    public long getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(long idMateria) {
        this.idMateria = idMateria;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdProfe() {
        return idProfe;
    }

    public void setIdProfe(long idProfe) {
        this.idProfe = idProfe;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Curso other = (Curso) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Curso{" + "id=" + id + ", idProfe=" + idProfe + ", idMateria=" + idMateria + '}';
    }

}
