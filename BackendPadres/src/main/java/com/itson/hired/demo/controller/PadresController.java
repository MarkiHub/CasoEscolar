/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.demo.controller;

import com.itson.hired.demo.DAO.EntregasDAO;
import com.itson.hired.demo.DAO.PadreDAO;
import edu.itson.dominioescolar.DTO.EntregasPadresDTO;
import edu.itson.dominioescolar.Padre;
import edu.itson.dominioescolar.Profesor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author xeron
 */
//El pirata de culiacan
@RestController
@Repository
@AutoConfiguration
@RequestMapping("/api/padres")
public class PadresController {

    private EntregasDAO entrDao = new EntregasDAO();
    private final PadreDAO padreDAO;

    @Autowired
    public PadresController(PadreDAO padreDAO) {
        this.padreDAO = padreDAO;
    }

    @GetMapping("/consultarAsigPendientes")
    public List<EntregasPadresDTO> consultarAsignacionesPendientes(@RequestParam long idPadre) {
        return entrDao.getAsignacionesAprobar(idPadre);
    }

    @PostMapping("/AprobarAsignacion")
    public void aprobarAsignacion(@RequestBody EntregasPadresDTO entrega) {
        entrDao.aprobarAsignacion(entrega);
    }

    @GetMapping("/profesores/{idPadre}")
    public ResponseEntity<List<Profesor>> getProfesoresPorPadre(@PathVariable Long idPadre) {
        List<Profesor> profesores = padreDAO.obtenerProfesoresPorPadre(idPadre);
        return ResponseEntity.ok(profesores);
    }

    @GetMapping("/{idPadre}")
    public ResponseEntity<Padre> getPadrePorId(@PathVariable Long idPadre) {
        Padre padre = padreDAO.obtenerPadrePorId(idPadre);
        return padre != null ? ResponseEntity.ok(padre) : ResponseEntity.notFound().build();
    }
}
