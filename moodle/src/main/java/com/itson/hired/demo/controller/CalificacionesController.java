/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.demo.controller;

import com.google.gson.Gson;
import com.itson.hired.DAO.ConversacionDAO;
import com.itson.hired.DAO.EntregasDAO;
import com.itson.hired.DAO.MensajeDAO;
import com.itson.hired.DAO.PadreDAO;
import com.itson.hired.demo.mensajeria.config;
import com.itson.hired.interfaces.IMensajeDAO;
import com.itson.hired.negocio.RendimientoNegocio;
import edu.itson.dominioescolar.Asignacion;
import edu.itson.dominioescolar.Calificacion;
import edu.itson.dominioescolar.DTO.EntregaDTO;
import java.util.List;
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
@RestController
public class CalificacionesController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    EntregasDAO entrDAO = new EntregasDAO();
    PadreDAO padreDAO = new PadreDAO();
    ConversacionDAO convDAO = new ConversacionDAO();
    IMensajeDAO mensajeDAO = new MensajeDAO();
    RendimientoNegocio rendNeg = new RendimientoNegocio(padreDAO, convDAO, mensajeDAO);
    
    @PostMapping("/subircalif")
    public Calificacion enviarCalificacion(@RequestParam long idCurso, @RequestParam long idAlumno) {
        Calificacion calificacion = entrDAO.generarPromedio(idAlumno, idCurso);
        Gson gson = new Gson();
        String json = gson.toJson(calificacion);
        rabbitTemplate.convertAndSend(config.EXCHANGE_NAME, "guardarCalificaciones", json);
        return calificacion;
    }

    @GetMapping("/obtenerEntregas")
    public List<EntregaDTO> obtenerEntregas(@RequestParam long idAsignacion) {
        return entrDAO.getEntregasPendientes(idAsignacion);
    }

    @PostMapping("/asignarCalificacion")
    public EntregaDTO asignarCalificacion(@RequestBody EntregaDTO entregaDTO) {
        entrDAO.asignarCalificacion(entregaDTO);
        rendNeg.revisarRendimiento(entregaDTO.getIdAlumno(),entregaDTO.getIdAsignacion(),entrDAO);
        return entregaDTO;
    }
    
    @GetMapping("/obtenerPromedio")
    public Calificacion obtenerPromedio(@RequestParam long idCurso, @RequestParam long idAlumno){
        return entrDAO.generarPromedio(idAlumno, idCurso);
    }
}
