/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.demo.controller;

import com.google.gson.Gson;
import com.itson.hired.DAO.AsignacionDAO;
import com.itson.hired.demo.mensajeria.config;
import edu.itson.dominioescolar.Alumno;
import edu.itson.dominioescolar.Asignacion;
import edu.itson.dominioescolar.EntregaAsignacion;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author xeron
 */
//El pirata de culiacan
@RestController
public class AsignacionesController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    private AsignacionDAO asigDao = new AsignacionDAO();

    @GetMapping("/consultarAsig")
    public Asignacion consultarAsignacion(@RequestParam long idAsignacion) throws UnsupportedEncodingException {
        Gson gson = new Gson();
        String json = gson.toJson(idAsignacion);
        rabbitTemplate.convertAndSend(config.EXCHANGE_NAME, "consultarAsignacion", json);
//        System.out.println(rabbitTemplate.receiveAndConvert("respuesta"));
//        String tilin = (String) rabbitTemplate.receiveAndConvert("respuesta");
//        System.out.println(tilin);
        //Asignacion asignacion = (Asignacion) rabbitTemplate.receive("respuesta");
        var eit = new String(rabbitTemplate.receive("respuesta").getBody(), "UTF-8");
        System.out.println(eit);
        Asignacion qiubole = gson.fromJson(eit, Asignacion.class);
        //String respuesta = gson.toJson(asignacion);
        //System.out.println(respuesta);
        return qiubole;
    }

    @PostMapping("/AsignarAsignacion")
    public Asignacion asignarAsignacion(@RequestBody Asignacion asig) {
        try {
            asigDao.insert(asig);
        } catch (SQLException ex) {
            Logger.getLogger(AsignacionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return asig;
    }

    @PostMapping("/EntregarAsignacion")
    public EntregaAsignacion EntregarAsignacion(@RequestBody EntregaAsignacion entreAsig) {
        try {
            asigDao.enviarEntrega(entreAsig);
        } catch (SQLException ex) {
            Logger.getLogger(AsignacionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entreAsig;
    }
    @GetMapping("/consultarAsignaciones")
    public List<Asignacion> consultarAsignaciones(@RequestParam Long idCurso) {
        return asigDao.getAllAsig(idCurso);
    }
    @GetMapping("/consultarAsignacionesPendientes")
    public List<Asignacion> consultarAsignacionesPendientes(@RequestParam Long idCurso, @RequestParam Long idAlumno) {
        return asigDao.getAsigPendientes(idCurso, idAlumno);
    }
}
