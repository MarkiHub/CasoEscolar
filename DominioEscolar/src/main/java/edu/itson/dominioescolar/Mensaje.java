/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.itson.dominioescolar;

import java.sql.Date;

/**
 *
 * @author ildex
 */
public class Mensaje {

    private Long id;
    private Conversacion conversacion;
    private Long idSender;
    private String text;
    private Date fechaEnviado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Conversacion getConversacion() {
        return conversacion;
    }

    public void setConversacion(Conversacion conversacion) {
        this.conversacion = conversacion;
    }

    public Long getIdSender() {
        return idSender;
    }

    public void setIdSender(Long idSender) {
        this.idSender = idSender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getFechaEnviado() {
        return fechaEnviado;
    }

    public void setFechaEnviado(Date fechaEnviado) {
        this.fechaEnviado = fechaEnviado;
    }

    @Override
    public String toString() {
        return "Mensaje{" + "id=" + id + ", conversacion=" + conversacion + ", idSender=" + idSender + ", text=" + text + ", fechaEnviado=" + fechaEnviado + '}';
    }

}
