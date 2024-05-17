/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.demo.controller;

import com.itson.hired.DAO.PadreDAO;
import edu.itson.dominioescolar.Padre;
import jakarta.servlet.ServletRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ildex
 */
@RestController
@RequestMapping("/api/padres")
public class PadreController {

    private final PadreDAO padreDAO;

    @Autowired
    public PadreController(PadreDAO padreDAO) {
        this.padreDAO = padreDAO;
    }

    @GetMapping
    public List<Padre> getPadresPorProfesor(ServletRequest req) {
        Long idProfesor = Long.valueOf(String.valueOf(req.getAttribute("idProfesor")));
        return padreDAO.obtenerPadresPorProfesor(idProfesor);
    }
}
