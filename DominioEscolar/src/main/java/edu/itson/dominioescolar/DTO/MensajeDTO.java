/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.itson.dominioescolar.DTO;

import java.util.Date;

/**
 *
 * @author ildex
 */
public class MensajeDTO {

    private Long id;
    private String texto;
    private Long conversacionId;
    private Long idSender;
    private Date fechaEnvio;

    public MensajeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Long getConversacionId() {
        return conversacionId;
    }

    public void setConversacionId(Long conversacionId) {
        this.conversacionId = conversacionId;
    }

    public Long getIdSender() {
        return idSender;
    }

    public void setIdSender(Long idSender) {
        this.idSender = idSender;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

}
