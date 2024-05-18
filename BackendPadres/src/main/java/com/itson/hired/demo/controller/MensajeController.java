/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.demo.controller;

import com.itson.hired.eventos.MensajeRecibidoEvent;
import com.itson.hired.interfaces.IMensajeDAO;
import edu.itson.dominioescolar.Mensaje;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ildex
 */
@RestController
@RequestMapping("/api/mensajes")
@Repository
@AutoConfiguration
public class MensajeController {

    private final IMensajeDAO mensajeDAO;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public MensajeController(IMensajeDAO mensajeDAO, ApplicationEventPublisher eventPublisher) {
        this.mensajeDAO = mensajeDAO;
        this.eventPublisher = eventPublisher;
    }

    @PostMapping("/enviar")
    public ResponseEntity<Mensaje> enviarMensaje(@RequestBody Mensaje mensaje) {
        mensajeDAO.insert(mensaje);
        eventPublisher.publishEvent(new MensajeRecibidoEvent(this, mensaje));
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/conversacion/{idConversacion}")
    public ResponseEntity<List<Mensaje>> obtenerMensajesPorConversacion(@PathVariable Long idConversacion) {
        List<Mensaje> mensajes = mensajeDAO.getMensajesPorConversacion(idConversacion);
        return ResponseEntity.ok(mensajes);
    }
}
