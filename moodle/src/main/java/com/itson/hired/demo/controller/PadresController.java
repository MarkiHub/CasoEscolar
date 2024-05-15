/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.demo.controller;

import com.itson.hired.DAO.EntregasDAO;
import edu.itson.dominioescolar.DTO.EntregasPadresDTO;
import java.util.List;
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
public class PadresController {

    private EntregasDAO entrDao = new EntregasDAO();

    @GetMapping("/consultarAsigPendientes")
    public List<EntregasPadresDTO> consultarAsignacionesPendientes(@RequestParam long idPadre) {
        return entrDao.getAsignacionesAprobar(idPadre);
    }
    
    
    @PostMapping("/AprobarAsignacion")
    public void aprobarAsignacion(@RequestBody EntregasPadresDTO entrega){
        entrDao.aprobarAsignacion(entrega);
    }
}
