/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.eventos;

import edu.itson.dominioescolar.Mensaje;
import org.springframework.context.ApplicationEvent;

/**
 *
 * @author ildex
 */
public class MensajeRecibidoEvent extends ApplicationEvent {

    private final Mensaje mensaje;

    public MensajeRecibidoEvent(Object source, Mensaje mensaje) {
        super(source);
        this.mensaje = mensaje;
    }

    public Mensaje getMensaje() {
        return mensaje;
    }
}
