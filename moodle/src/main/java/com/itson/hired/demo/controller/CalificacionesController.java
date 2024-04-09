/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.demo.controller;

import com.google.gson.Gson;
import com.itson.hired.demo.mensajeria.config;
import edu.itson.dominioescolar.Calificacion;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author xeron
 */
@RestController
public class CalificacionesController {
 
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    
    @PostMapping("/subircalif")
    public void enviarCalificacion(@RequestBody Calificacion calificacion) {
        Gson gson = new Gson();
        String json = gson.toJson(calificacion);
        rabbitTemplate.convertAndSend(config.EXCHANGE_NAME, "guardarCalificaciones", json);
    }
    
}
