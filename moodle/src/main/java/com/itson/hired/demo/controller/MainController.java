/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.itson.hired.demo.controller;

import edu.itson.dominioescolar.Alumno;
import edu.itson.dominioescolar.DAO.AlumnoDAO;
import edu.itson.dominioescolar.DAO.PadreDAO;
import edu.itson.dominioescolar.DAO.ProfesorDAO;
import edu.itson.dominioescolar.Padre;
import edu.itson.dominioescolar.Profesor;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import jwt.JwtGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author xeron
 */
@RestController
public class MainController {

    @PostMapping("/nigga")
    public void receiveUserInfo(@RequestBody Map<String, Object> userInfo) throws SQLException {
        System.out.println("Datos recibidos de /user-info:");
        System.out.println(userInfo);
        long id = (Long) userInfo.get("name");
        if (id == 1) {
            Profesor profe = mandarProfe(id);
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", profe.getId());
            claims.put("username", profe.getNombreCompleto());
            claims.put("role", "Profesor");
            String jwt = JwtGenerator.generateJwt(claims);
            System.out.println("JWT generado: " + jwt);
        } else if (id == 2) {
            Padre padre = mandarPadre(id);
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", padre.getId());
            claims.put("username", padre.getNombreCompleto());
            claims.put("role", "Padre");
            String jwt = JwtGenerator.generateJwt(claims);
            System.out.println("JWT generado: " + jwt);
        } else if (id == 3) {
            Alumno alumno = mandarAlumno(id);
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", alumno.getId());
            claims.put("username", alumno.getNombreCompleto());
            claims.put("role", "Padre");
            String jwt = JwtGenerator.generateJwt(claims);
            System.out.println("JWT generado: " + jwt);
        }
    }

    public Profesor mandarProfe(Long idProfe) throws SQLException {
        ProfesorDAO pDao = new ProfesorDAO();
        Profesor profesor = pDao.obtenerProfesorPorId(idProfe);
        return profesor;
    }

    public Padre mandarPadre(Long idPadre) throws SQLException {
        PadreDAO pDao = new PadreDAO();
        Padre padre = pDao.obtenerPadrePorId(idPadre);
        return padre;
    }

    public Alumno mandarAlumno(Long idAlumno) throws SQLException {
        AlumnoDAO pDao = new AlumnoDAO();
        Alumno alumno = pDao.obtenerAlumnoPorId(idAlumno);
        return alumno;
    }
}
