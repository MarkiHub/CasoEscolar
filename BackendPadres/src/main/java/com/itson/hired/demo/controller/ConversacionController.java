/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.demo.controller;

import com.itson.hired.interfaces.IConversacionDAO;
import edu.itson.dominioescolar.Conversacion;
import edu.itson.dominioescolar.DTO.ConversacionDTO;
import edu.itson.dominioescolar.Padre;
import edu.itson.dominioescolar.Profesor;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ildex
 */
@RestController
@RequestMapping("/api/conversaciones")
@Repository
@AutoConfiguration
public class ConversacionController {

    private final IConversacionDAO conversacionDAO;

    @Autowired
    public ConversacionController(IConversacionDAO conversacionDAO) {
        this.conversacionDAO = conversacionDAO;
    }

    @PostMapping("/crear")
    public ResponseEntity<Long> verificarOCrearConversacion(@RequestBody ConversacionDTO conversacionDTO) {
        Long idProfesor = conversacionDTO.getIdProfesor();
        Long idPadre = conversacionDTO.getIdPadre();

        Optional<Conversacion> conversacionExistente = conversacionDAO.obtenerConversacionPorProfesorYPadre(idProfesor, idPadre);

        if (conversacionExistente.isPresent()) {
            return ResponseEntity.ok(conversacionExistente.get().getId());
        } else {
            Profesor profesor = new Profesor();
            profesor.setId(idProfesor);

            Padre padre = new Padre();
            padre.setId(idPadre);

            Conversacion nuevaConversacion = new Conversacion();
            nuevaConversacion.setProfesor(profesor);
            nuevaConversacion.setPadre(padre);

            Conversacion conversacionCreada = conversacionDAO.crearConversacion(nuevaConversacion);
            return ResponseEntity.ok(conversacionCreada.getId());
        }
    }
}
