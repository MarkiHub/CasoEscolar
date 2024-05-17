/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.demo.controller;

import com.itson.hired.DAO.ProfesorDAO;
import edu.itson.dominioescolar.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ildex
 */
@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    private final ProfesorDAO profesorDAO;

    @Autowired
    public ProfesorController(ProfesorDAO profesorDAO) {
        this.profesorDAO = profesorDAO;
    }

    @GetMapping("/{id}")
    public Profesor obtenerProfesorPorId(@PathVariable Long id) {
        return profesorDAO.obtenerProfesorPorId(id);
    }
}
