/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.eventos;

import edu.itson.dominioescolar.Mensaje;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author ildex
 */
@Component
public class MensajeRecibidoEventListener implements ApplicationListener<MensajeRecibidoEvent> {

    @Override
    public void onApplicationEvent(MensajeRecibidoEvent event) {
        Mensaje mensaje = event.getMensaje();
        // Aquí puedes implementar la lógica para procesar el mensaje recibido
        System.out.println("Nuevo mensaje recibido: " + mensaje);
    }
}
