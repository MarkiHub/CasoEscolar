/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.demo.controller;

import com.itson.hired.DAO.CursosDAO;
import edu.itson.dominioescolar.DTO.CursoDTO;
import jakarta.servlet.ServletRequest;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Elkur
 */
@RestController
public class CursosController {
    
    CursosDAO cursDao = new CursosDAO();
    
    @GetMapping("/ConsultarCursos")
    public List<CursoDTO> getCursosImpartidos(ServletRequest req){
       return cursDao.getAllCursos(Long.valueOf(String.valueOf(req.getAttribute("idProfesor"))));
    }
}
