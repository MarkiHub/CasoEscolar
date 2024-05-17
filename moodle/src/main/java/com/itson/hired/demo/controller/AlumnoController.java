/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Elkur
 */
@Controller
@RequestMapping("/alumno")
public class AlumnoController {

    @GetMapping("/verCurso")
    public String verCurso(@RequestParam Long id) {
        return "verCursoAlumnom";
    }

}
