/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Elkur
 */
@Controller
@RequestMapping("/profesor")
public class ProfeController {

    @GetMapping("/AsignarAsignacion")
    public String asignaciones(@RequestParam Long id) {
        return "AsignarAsig";
    }

    @GetMapping("/verCurso")
    public String verCurso(@RequestParam Long id) {
        return "verCurso";
    }

    @GetMapping("/verEntregas")
    public String verEntregas(@RequestParam Long idAsignacion) {
        return "verEntregas";
    }

    @GetMapping("/verAsignaciones")
    public String verAsignaciones(@RequestParam Long idCurso) {
        return "verAsignaciones";
    }

    @GetMapping("/Cursos")
    public String cursos() {
        return "Cursos";
    }

}
