/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.itson.hired.demo.config.util;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Elkur
 */
public enum RolePages {
    alumno("Alumno", Arrays.asList(
            "/alumno/verCurso"
    )),
    padre("Padre", Arrays.asList(
            "/padre/"
    )),
    profesor("Profesor", Arrays.asList(
            "/profesor/AsignarAsignacion",
            "/profesor/verEntregas",
            "/profesor/verAsignaciones",
            "/profesor/verCurso"
    ));

    private List<String> pathsAccess;
    private String rol;

    private RolePages(String rol, List<String> pathsAccess) {
        this.pathsAccess = pathsAccess;
    }

    public List<String> getPathsAccess() {
        return pathsAccess;
    }

    public String getRol() {
        return rol;
    }

}
